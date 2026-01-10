package com.hibernate.repository.dao;

import com.hibernate.entity.Student;

public interface StudentDao {

    boolean save(Student student);
    void close();
}
