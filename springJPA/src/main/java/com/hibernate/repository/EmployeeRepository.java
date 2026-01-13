package com.hibernate.repository;

import com.hibernate.entity.EmployeeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EmployeeRepository implements EmployeeDAO {

    private EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("postgres");


    @Override
    public EmployeeEntity save(EmployeeEntity employee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(employee);
            transaction.commit();
            System.out.println("new employee inserted");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        } finally {
            entityManager.close();
        }

        return employee;
    }


    /**
     * find() : early load
     * getReference() : lazy load
     */
    @Override
    public EmployeeEntity load(Integer employeeId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // no need for transaction
        EmployeeEntity employeeEntity = entityManager.find(EmployeeEntity.class, employeeId);
        entityManager.close();
        return employeeEntity;
    }

    @Override
    public EmployeeEntity updateEmployeeById(Integer employeeId, String role) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EmployeeEntity employeeEntity = entityManager.getReference(EmployeeEntity.class, employeeId);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            System.out.println(employeeEntity);
            employeeEntity.setRole(role);
            transaction.commit();
            System.out.println(employeeEntity);
            System.out.println("update success");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return employeeEntity;
    }

    @Override
    public void deleteEmployeeById(Integer employeeId) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EmployeeEntity employeeEntity = entityManager.getReference(EmployeeEntity.class, employeeId);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.remove(employeeEntity);
            transaction.commit();
            System.out.println("delete success");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }

    }
}
