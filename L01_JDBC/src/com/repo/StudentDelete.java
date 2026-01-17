package com.repo;

import com.config.JDBCConnection;
import com.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDelete {

    public boolean delete(Student student) throws SQLException {

        Connection conn = null;

        try {
            conn = JDBCConnection.connection();
            conn.setAutoCommit(false); // transaction start

            // 1️⃣ delete student
            PreparedStatement psStudent =
                    deleteStudentPrepareStatement(conn, student);
            psStudent.executeUpdate();

            // 2️⃣ delete address
            PreparedStatement psAddress =
                    deleteAddressPrepareStatement(conn, student);
            psAddress.executeUpdate();

            conn.commit();
            System.out.println("Student deleted successfully!");
            return true;

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw new SQLException(e.getMessage());
        } finally {
            if (conn != null) conn.close();
        }
    }

    // ---------- DELETE STUDENT ----------
    private PreparedStatement deleteStudentPrepareStatement(
            Connection conn, Student student) throws SQLException {

        String sql = "DELETE FROM student WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, student.getStudentId());

        return ps;
    }

    // ---------- DELETE ADDRESS ----------
    private PreparedStatement deleteAddressPrepareStatement(
            Connection conn, Student student) throws SQLException {

        String sql = "DELETE FROM address WHERE address_id=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, student.getStudentAddress().getAddressId());

        return ps;
    }
}
