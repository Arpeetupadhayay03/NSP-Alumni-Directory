package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_NAME = "alumni_db";

    private static final String URL_PREFIX =
            "jdbc:mysql://localhost:3306/" + DB_NAME
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String DB_USER = "root";

    // Password will be read from Windows Environment Variable
    private static final String DB_PASSWORD =
            System.getenv("ALUMNI_DB_PASSWORD");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(
                    "[DBConnection] MySQL JDBC Driver not found: "
                    + e.getMessage()
            );
        }
    }

    // Establish database connection
    public static Connection getConnection() throws SQLException {

        if (DB_PASSWORD == null || DB_PASSWORD.isBlank()) {
            throw new SQLException(
                    "Database password not configured."
            );
        }

        return DriverManager.getConnection(
                URL_PREFIX,
                DB_USER,
                DB_PASSWORD
        );
    }

    // Close database connection
    public static void closeConnection(Connection conn) {

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(
                        "[DBConnection] Error closing connection: "
                        + e.getMessage()
                );
            }
        }
    }

    // Close statements and result sets
    public static void closeResources(AutoCloseable... resources) {

        for (AutoCloseable res : resources) {

            if (res != null) {
                try {
                    res.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

    // Test database connectivity
    public static boolean testConnection() {

        try (Connection conn = getConnection()) {

            return conn != null && !conn.isClosed();

        } catch (SQLException e) {

            System.err.println(
                    "[DBConnection] Health check failed: "
                    + e.getMessage()
            );

            return false;
        }
    }
}