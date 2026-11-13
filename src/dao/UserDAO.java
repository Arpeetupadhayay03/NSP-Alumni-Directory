package dao;

import db.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDAO handles all database operations relating to Users,
 * including authentication, profile retrieval, and password modifications.
 */
public class UserDAO {

    /**
     * Authenticates a user with the provided username and password.
     *
     * @param username user login handle
     * @param password plain text password
     * @return User object if credentials are valid, or null otherwise
     */
    public User authenticate(String username, String password) {
        String sql = "SELECT user_id, username, password, full_name, role, created_at "
                   + "FROM users WHERE username = ? AND password = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("role"),
                        rs.getTimestamp("created_at")
                );
            }
        } catch (SQLException e) {
            System.err.println("[UserDAO] Authentication error: " + e.getMessage());
        } finally {
            DBConnection.closeResources(rs, ps, conn);
        }
        return null;
    }

    /**
     * Changes a user's password after verifying the existing password.
     *
     * @param userId user identifier
     * @param oldPassword current password to verify
     * @param newPassword new password to set
     * @return true if updated successfully, false if verification failed or SQL error occurred
     */
    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        String verifySql = "SELECT user_id FROM users WHERE user_id = ? AND password = ?";
        String updateSql = "UPDATE users SET password = ? WHERE user_id = ?";

        Connection conn = null;
        PreparedStatement psVerify = null;
        PreparedStatement psUpdate = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            psVerify = conn.prepareStatement(verifySql);
            psVerify.setInt(1, userId);
            psVerify.setString(2, oldPassword);
            rs = psVerify.executeQuery();

            if (!rs.next()) {
                return false; // Old password verification failed
            }

            psUpdate = conn.prepareStatement(updateSql);
            psUpdate.setString(1, newPassword);
            psUpdate.setInt(2, userId);

            int rows = psUpdate.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("[UserDAO] Error changing password: " + e.getMessage());
            return false;
        } finally {
            DBConnection.closeResources(rs, psVerify, psUpdate, conn);
        }
    }

    /**
     * Retrieves user by primary key ID.
     */
    public User getUserById(int userId) {
        String sql = "SELECT user_id, username, password, full_name, role, created_at "
                   + "FROM users WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("role"),
                        rs.getTimestamp("created_at")
                );
            }
        } catch (SQLException e) {
            System.err.println("[UserDAO] Error fetching user by ID: " + e.getMessage());
        } finally {
            DBConnection.closeResources(rs, ps, conn);
        }
        return null;
    }

    /**
     * Retrieves all registered users in the database.
     */
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT user_id, username, password, full_name, role, created_at "
                   + "FROM users ORDER BY user_id ASC";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("role"),
                        rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            System.err.println("[UserDAO] Error listing users: " + e.getMessage());
        } finally {
            DBConnection.closeResources(rs, ps, conn);
        }
        return list;
    }
}
