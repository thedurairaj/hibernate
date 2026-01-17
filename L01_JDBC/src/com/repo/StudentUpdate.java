package com.repo;

import com.config.JDBCConnection;
import com.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentUpdate {

    public Student update(Student student) throws SQLException {

        Connection conn = null;

        try {
            conn = JDBCConnection.connection();
            conn.setAutoCommit(false); // transaction start

            // 1️⃣ update address
            PreparedStatement psAddress =
                    updateAddressPrepareStatement(conn, student);
            psAddress.executeUpdate();

            // 2️⃣ update student
            PreparedStatement psStudent =
                    updateStudentPrepareStatement(conn, student);
            psStudent.executeUpdate();

            conn.commit();
            System.out.println("Student data updated successfully!");
            return student;

        } catch (SQLException e) {
            conn.rollback();
            throw new SQLException(e.getMessage());
        } finally {
            if (conn != null) conn.close();
        }
    }

    // ---------- STUDENT UPDATE ----------
    private PreparedStatement updateStudentPrepareStatement(
            Connection conn, Student student) throws SQLException {

        String sql =
                "UPDATE student SET name=?, std=?, section=? WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, student.getStudentName());
        ps.setString(2, student.getStandard());
        ps.setString(3, student.getSection());
        ps.setInt(4, student.getStudentId());

        return ps;
    }

    // ---------- ADDRESS UPDATE ----------
    private PreparedStatement updateAddressPrepareStatement(
            Connection conn, Student student) throws SQLException {

        String sql =
                "UPDATE address SET street=?, city=?, state=?, zip=?, country=? " +
                        "WHERE address_id=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, student.getStudentAddress().getStreet());
        ps.setString(2, student.getStudentAddress().getCity());
        ps.setString(3, student.getStudentAddress().getState());
        ps.setString(4, student.getStudentAddress().getZip());
        ps.setString(5, student.getStudentAddress().getCountry());
        ps.setInt(6, student.getStudentAddress().getAddressId());

        return ps;
    }
}
