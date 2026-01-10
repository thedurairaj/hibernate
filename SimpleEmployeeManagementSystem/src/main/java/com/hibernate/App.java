package com.hibernate;

import com.hibernate.dao.EmployeeDao;
import com.hibernate.entity.Employee;
import com.hibernate.repository.EmployeeDaoImpl;

import java.util.Optional;

public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        EmployeeDao dao = new EmployeeDaoImpl();

        // ENTITY (manual input simulated)
        Employee e1 = new Employee();
        e1.seteId(101);
        e1.seteName("Ravi");
        e1.seteDepartment("IT");
        e1.setSalary(50000.0);

        // CREATE
        Optional<Employee> employee = dao.saveNewEmployee(e1);
        System.out.println("%%%%%%%%%%% "+employee.get().geteId());

        // READ
        dao.getEmployees().forEach(System.out::println);

        // UPDATE (dirty checking)
        e1.setSalary(60000.0);
        dao.updateEmployee(e1);

        // DELETE
        dao.deleteEmployee(e1.geteId());

        // SHUTDOWN
        dao.shutdown();
    }
}
