package com.hibernate.repository;


import com.hibernate.entity.EmployeeEntity;

public interface EmployeeDAO {

    EmployeeEntity save(EmployeeEntity employee);

    EmployeeEntity load(Integer employeeId);

    EmployeeEntity updateEmployeeById(Integer employeeId,String role);

    void deleteEmployeeById(Integer employeeId);
}
