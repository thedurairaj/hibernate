package com.hibernate.entity;

public class Employee {

    private Integer eId;
    private String eName;
    private String eDepartment;
    private Double salary;

    public Employee(Integer eId, String eName, String eDepartment, Double salary) {
        this.eId = eId;
        this.eName = eName;
        this.eDepartment = eDepartment;
        this.salary = salary;
    }

    public Employee() {
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteDepartment() {
        return eDepartment;
    }

    public void seteDepartment(String eDepartment) {
        this.eDepartment = eDepartment;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eId=" + eId +
                ", eName='" + eName + '\'' +
                ", eDepartment='" + eDepartment + '\'' +
                ", salary=" + salary +
                '}';
    }
}
