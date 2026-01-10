package com.hibernate.repository;

import com.hibernate.entity.Student;
import com.hibernate.repository.dao.StudentDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class StudentRepository implements StudentDao {

    private SessionFactory sessionFactory;

    public StudentRepository() {

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .getMetadataBuilder()
                .build();
        this.sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();
    }


    @Override
    public boolean save(Student student) {

        Session session = this.sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        try{
            session.persist(student);
            System.out.println("new data inserted successfully");
            transaction.commit();
        }catch (Exception ex){
            transaction.rollback();
            System.out.println(ex.getMessage());
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public void close(){
        this.sessionFactory.close();
    }


}
