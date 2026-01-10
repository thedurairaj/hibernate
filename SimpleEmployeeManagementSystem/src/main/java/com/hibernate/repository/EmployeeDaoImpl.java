package com.hibernate.repository;

import com.hibernate.dao.EmployeeDao;
import com.hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.internal.MetadataImpl;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements EmployeeDao {

    private SessionFactory sessionFactory;

    public EmployeeDaoImpl() {

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .getMetadataBuilder()
                .build();


        this.sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    @Override
    public Optional<Employee> saveNewEmployee(Employee employee) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.persist(employee);
            tx.commit();
            return Optional.of(employee);

        } catch (Exception e) {
            tx.rollback();
            return Optional.empty();

        } finally {
            session.close();
        }
    }




    @Override
    public List<Employee> getEmployees() {
        Session session = this.sessionFactory.openSession();
        List<Employee> employees;

        // load -> Lazy Loading or get() -> early loading if you want by id mean

        try {
            employees = session.createQuery("from Employee", Employee.class)
                    .list();
        } finally {
            session.close();
        }


        return employees;
    }

    @Override
    public Optional<Employee> updateEmployee(Employee employee) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Employee persistent =
                    session.find(Employee.class, employee.geteId());

            if (persistent == null) {
                return Optional.empty();
            }

            persistent.setSalary(employee.getSalary()); // dirty checking

            tx.commit();
            return Optional.of(persistent);

        } catch (Exception e) {
            tx.rollback();
            throw e;

        } finally {
            session.close();
        }
    }


    @Override
    public boolean deleteEmployee(Integer empNo) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Employee emp = session.find(Employee.class, empNo);

            if (emp == null) {
                return false;
            }

            session.remove(emp);
            tx.commit();
            return true;

        } catch (Exception e) {
            tx.rollback();
            throw e;

        } finally {
            session.close();
        }
    }


    @Override
    public void shutdown() {
        this.sessionFactory.close();
    }
}
