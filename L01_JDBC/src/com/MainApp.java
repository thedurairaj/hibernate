package com;

import com.model.Address;
import com.model.Student;
import com.repo.StudentDelete;
import com.repo.StudentInsert;
import com.repo.StudentRead;
import com.repo.StudentUpdate;

import java.sql.SQLException;
import java.util.List;

public class MainApp {

    public static void main(String[] args) throws SQLException {

        // ---------- INSERT ----------
        Student student = new Student();
        Address address = new Address();

        student.setStudentName("Ramesh");
        student.setStandard("10");
        student.setSection("A");

        address.setStreet("MG Road");
        address.setCity("Bengaluru");
        address.setState("Karnataka");
        address.setZip("560001");
        address.setCountry("India");

        student.setStudentAddress(address);

        new StudentInsert().insert(student);

        // ---------- READ & PRINT ----------
        StudentRead readRepo = new StudentRead();
        List<Student> students = readRepo.getStudents();

        System.out.println("\n---- STUDENT LIST ----");
        for (Student s : students) {
            System.out.println(s);
        }

        // ---------- UPDATE ----------
        Student updateStudent = students.get(0); // first student
        updateStudent.setStudentName("Ramesh Kumar");
        updateStudent.getStudentAddress().setCity("Mysuru");

        new StudentUpdate().update(updateStudent);

        // ---------- READ & PRINT AGAIN ----------
        System.out.println("\n---- AFTER UPDATE ----");
        readRepo.getStudents().forEach(System.out::println);

        // ---------- DELETE ----------
        Student deleteStudent = students.get(0);
        new StudentDelete().delete(deleteStudent);

        // ---------- FINAL READ ----------
        System.out.println("\n---- AFTER DELETE ----");
        readRepo.getStudents().forEach(System.out::println);
    }
}
