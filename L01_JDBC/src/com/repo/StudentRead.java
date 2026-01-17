package com.repo;

import com.config.JDBCConnection;
import com.model.Address;
import com.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRead {

    public List<Student> getStudents() throws SQLException {
        List<Student> students = new ArrayList<>();

        String sql = """
                SELECT
                    s.id,
                    s.name,
                    s.std,
                    s.section,
                    a.street,
                    a.city,
                    a.state,
                    a.zip,
                    a.country,
                    s.address_id
                FROM student s
                JOIN address a
                ON s.address_id = a.address_id;
                """;

        try (Connection conn = JDBCConnection.connection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("id"));
                student.setStudentName(rs.getString("name"));
                student.setStandard(rs.getString("std"));
                student.setSection(rs.getString("section"));
                student.setStudentAddress(new Address(rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("country"), rs.getInt("address_id")));
                students.add(student);
            }
            return students;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
