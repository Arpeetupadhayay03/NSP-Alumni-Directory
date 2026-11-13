package dao;

import db.DBConnection;
import model.Alumni;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AlumniDAO handles all CRUD (Create, Read, Update, Delete) and Search operations
 * on the alumni relational table using JDBC PreparedStatement.
 */
public class AlumniDAO {

    /**
     * Adds a new alumni record to the database.
     *
     * @param a the Alumni object to insert
     * @return true if added successfully, false otherwise
     */
    public boolean addAlumni(Alumni a) {
        String sql = "INSERT INTO alumni (name, graduation_year, department, organization, job_role, city, email, phone) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getName());
            ps.setInt(2, a.getGraduationYear());
            ps.setString(3, a.getDepartment());
            ps.setString(4, a.getOrganization());
            ps.setString(5, a.getJobRole());
            ps.setString(6, a.getCity());
            ps.setString(7, a.getEmail());
            ps.setString(8, a.getPhone());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    a.setAlumniId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("[AlumniDAO] Error adding alumni: " + e.getMessage());
        } finally {
            DBConnection.closeResources(rs, ps, conn);
        }
        return false;
    }

    /**
     * Retrieves all alumni records ordered by alumni_id.
     */
    public List<Alumni> getAllAlumni() {
        List<Alumni> list = new ArrayList<>();
        String sql = "SELECT alumni_id, name, graduation_year, department, organization, job_role, city, email, phone, created_at "
                   + "FROM alumni ORDER BY alumni_id ASC";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractAlumni(rs));
            }
        } catch (SQLException e) {
            System.err.println("[AlumniDAO] Error fetching all alumni: " + e.getMessage());
        } finally {
            DBConnection.closeResources(rs, ps, conn);
        }
        return list;
    }

    /**
     * Retrieves a single alumni by primary key.
     */
    public Alumni getAlumniById(int alumniId) {
        String sql = "SELECT alumni_id, name, graduation_year, department, organization, job_role, city, email, phone, created_at "
                   + "FROM alumni WHERE alumni_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, alumniId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return extractAlumni(rs);
            }
        } catch (SQLException e) {
            System.err.println("[AlumniDAO] Error fetching alumni by ID: " + e.getMessage());
        } finally {
            DBConnection.closeResources(rs, ps, conn);
        }
        return null;
    }

    /**
     * Updates an existing alumni record in the database.
     */
    public boolean updateAlumni(Alumni a) {
        String sql = "UPDATE alumni SET name = ?, graduation_year = ?, department = ?, "
                   + "organization = ?, job_role = ?, city = ?, email = ?, phone = ? WHERE alumni_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, a.getName());
            ps.setInt(2, a.getGraduationYear());
            ps.setString(3, a.getDepartment());
            ps.setString(4, a.getOrganization());
            ps.setString(5, a.getJobRole());
            ps.setString(6, a.getCity());
            ps.setString(7, a.getEmail());
            ps.setString(8, a.getPhone());
            ps.setInt(9, a.getAlumniId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[AlumniDAO] Error updating alumni: " + e.getMessage());
            return false;
        } finally {
            DBConnection.closeResources(ps, conn);
        }
    }

    /**
     * Deletes an alumni record by primary key.
     */
    public boolean deleteAlumni(int alumniId) {
        String sql = "DELETE FROM alumni WHERE alumni_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, alumniId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[AlumniDAO] Error deleting alumni: " + e.getMessage());
            return false;
        } finally {
            DBConnection.closeResources(ps, conn);
        }
    }

    /**
     * General multi-field search matching Keyword across Name, Organization, City, or ID,
     * with optional Department filter.
     */
    public List<Alumni> searchAlumni(String keyword, String department) {
        List<Alumni> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT alumni_id, name, graduation_year, department, organization, job_role, city, email, phone, created_at "
              + "FROM alumni WHERE 1=1 "
        );

        List<Object> params = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            String trimmed = keyword.trim();
            try {
                int id = Integer.parseInt(trimmed);
                sql.append("AND (alumni_id = ? OR graduation_year = ? OR name LIKE ? OR organization LIKE ? OR city LIKE ?) ");
                params.add(id);
                params.add(id);
                params.add("%" + trimmed + "%");
                params.add("%" + trimmed + "%");
                params.add("%" + trimmed + "%");
            } catch (NumberFormatException e) {
                sql.append("AND (name LIKE ? OR organization LIKE ? OR city LIKE ? OR job_role LIKE ?) ");
                params.add("%" + trimmed + "%");
                params.add("%" + trimmed + "%");
                params.add("%" + trimmed + "%");
                params.add("%" + trimmed + "%");
            }
        }

        if (department != null && !department.trim().isEmpty() && !"All".equalsIgnoreCase(department.trim())) {
            sql.append("AND department = ? ");
            params.add(department.trim());
        }

        sql.append("ORDER BY alumni_id ASC");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractAlumni(rs));
            }
        } catch (SQLException e) {
            System.err.println("[AlumniDAO] Error searching alumni: " + e.getMessage());
        } finally {
            DBConnection.closeResources(rs, ps, conn);
        }
        return list;
    }

    /**
     * Specific search by criteria: name, department, organization, city.
     */
    public List<Alumni> searchByCriteria(String name, String department, String organization, String city) {
        List<Alumni> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT alumni_id, name, graduation_year, department, organization, job_role, city, email, phone, created_at "
              + "FROM alumni WHERE 1=1 "
        );

        List<Object> params = new ArrayList<>();

        if (name != null && !name.trim().isEmpty()) {
            sql.append("AND name LIKE ? ");
            params.add("%" + name.trim() + "%");
        }

        if (department != null && !department.trim().isEmpty() && !"All".equalsIgnoreCase(department.trim())) {
            sql.append("AND department = ? ");
            params.add(department.trim());
        }

        if (organization != null && !organization.trim().isEmpty()) {
            sql.append("AND organization LIKE ? ");
            params.add("%" + organization.trim() + "%");
        }

        if (city != null && !city.trim().isEmpty()) {
            sql.append("AND city LIKE ? ");
            params.add("%" + city.trim() + "%");
        }

        sql.append("ORDER BY alumni_id ASC");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractAlumni(rs));
            }
        } catch (SQLException e) {
            System.err.println("[AlumniDAO] Error searching alumni by criteria: " + e.getMessage());
        } finally {
            DBConnection.closeResources(rs, ps, conn);
        }
        return list;
    }

    /**
     * Total alumni count in directory.
     */
    public int getTotalAlumniCount() {
        String sql = "SELECT COUNT(*) FROM alumni";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[AlumniDAO] Error counting alumni: " + e.getMessage());
        } finally {
            DBConnection.closeResources(rs, ps, conn);
        }
        return 0;
    }

    /**
     * Count of distinct departments represented in directory.
     */
    public int getDistinctDepartmentsCount() {
        String sql = "SELECT COUNT(DISTINCT department) FROM alumni";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[AlumniDAO] Error counting departments: " + e.getMessage());
        } finally {
            DBConnection.closeResources(rs, ps, conn);
        }
        return 0;
    }

    /**
     * Count of distinct cities represented in directory.
     */
    public int getDistinctCitiesCount() {
        String sql = "SELECT COUNT(DISTINCT city) FROM alumni";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[AlumniDAO] Error counting cities: " + e.getMessage());
        } finally {
            DBConnection.closeResources(rs, ps, conn);
        }
        return 0;
    }

    private Alumni extractAlumni(ResultSet rs) throws SQLException {
        return new Alumni(
                rs.getInt("alumni_id"),
                rs.getString("name"),
                rs.getInt("graduation_year"),
                rs.getString("department"),
                rs.getString("organization"),
                rs.getString("job_role"),
                rs.getString("city"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getTimestamp("created_at")
        );
    }
}
