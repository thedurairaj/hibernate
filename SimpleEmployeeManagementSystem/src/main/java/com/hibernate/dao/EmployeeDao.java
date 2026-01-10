package com.hibernate.dao;

import com.hibernate.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    Optional<Employee> saveNewEmployee(Employee employee);

    List<Employee> getEmployees();

    Optional<Employee> updateEmployee(Employee employee);

    boolean deleteEmployee(Integer empNo);

    void shutdown();
}
