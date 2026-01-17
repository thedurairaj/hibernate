package com.repo;

import com.config.JDBCConnection;
import com.model.Student;

import java.sql.*;

public class StudentInsert {

    public void insert(Student student) throws SQLException {

        Connection conn = null;
        try {
            conn = JDBCConnection.connection();
            conn.setAutoCommit(false); // transactions

            // 1st
            PreparedStatement psAddress = prepareStatement(conn, student);
            psAddress.executeUpdate();
            ResultSet rs = psAddress.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);

            // 2nd
            PreparedStatement psStudent = stPrepareStatement(conn, student, id);
            psStudent.executeUpdate();
            conn.commit();
            System.out.println("Student data inserted successfully!");
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
            throw new SQLException(e.getMessage());
        } finally {
            assert conn != null;
            conn.close();
        }
    }

    private PreparedStatement stPrepareStatement(Connection conn, Student student, int addressId) throws SQLException {
        String studentSql =
                "INSERT INTO student (name, std, section, address_id) " +
                        "VALUES (?, ?, ?, ?)";
        PreparedStatement psStudent =
                conn.prepareStatement(studentSql);

        psStudent.setString(1, student.getStudentName());
        psStudent.setString(2, student.getStandard());
        psStudent.setString(3, student.getSection());
        psStudent.setInt(4, addressId);
        return psStudent;
    }

    private PreparedStatement prepareStatement(Connection conn, Student student) throws SQLException {
        String sql = "INSERT INTO address(street, city, state, zip, country) VALUES(?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, student.getStudentAddress().getStreet());
        statement.setString(2, student.getStudentAddress().getCity());
        statement.setString(3, student.getStudentAddress().getState());
        statement.setString(4, student.getStudentAddress().getZip());
        statement.setString(5, student.getStudentAddress().getCountry());
        return statement;
    }
}
