package com.hibernate;


import com.hibernate.entity.EmployeeEntity;
import com.hibernate.repository.EmployeeDAO;
import com.hibernate.repository.EmployeeRepository;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        EmployeeDAO employeeDAO = new EmployeeRepository();

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(101L);
        employeeEntity.setName("kunal");
        employeeEntity.setAge(21);
        employeeEntity.setRole("junior");
        // save new data
        try {
            //employeeDAO.save(employeeEntity);
        }catch (Exception e){
           // System.out.println(e.getMessage());
        }

        // fatch data
        EmployeeEntity load = employeeDAO.load(101);
        System.out.println(load);

        // update
        employeeDAO.updateEmployeeById(100,"AM");

    }
}
