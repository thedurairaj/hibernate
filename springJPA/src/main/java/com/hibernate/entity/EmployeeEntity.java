package com.hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @Column(name = "emp_id")
    private Long employeeId;

    @Column(name = "emp_name")
    private String name;

    @Column(name = "emp_age")
    private Integer age;

    @Column(name = "emp_role")
    private String role;

    @Column(name = "create_on")
    @CreationTimestamp
    private LocalDateTime submittedTime;

    @Column(name = "last_updated_on")
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        if (age < 0) {
            throw new NoSuchElementException("Invalid age");
        }
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getSubmittedTime() {
        return submittedTime;
    }

    public void setSubmittedTime(LocalDateTime submittedTime) {
        this.submittedTime = submittedTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", role='" + role + '\'' +
                ", submittedTime=" + submittedTime +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
