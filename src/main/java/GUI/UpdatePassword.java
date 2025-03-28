package GUI;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UpdatePassword {
    private static final String DB_URL = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";

    public static void main(String[] args) {
        hashAndUpdatePasswords("LoginCredentials"); // Update Employee passwords
        hashAndUpdatePasswords("AdminLoginCredentials"); // Update Admin passwords
    }

    private static void hashAndUpdatePasswords(String tableName) {
        String selectQuery = "SELECT emp_id, password FROM " + tableName;
        String updateQuery = "UPDATE " + tableName + " SET password = ? WHERE emp_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
             ResultSet rs = selectStmt.executeQuery()) {

            while (rs.next()) {
                int empId = rs.getInt("emp_id");
                String plainPassword = rs.getString("password");

                // Check if password is already hashed (BCrypt hash starts with "$2a$", "$2b$", or "$2y$")
                if (plainPassword.startsWith("$2a$") || plainPassword.startsWith("$2b$") || plainPassword.startsWith("$2y$")) {
                    System.out.println("Skipping already hashed password for " + tableName + " emp_id: " + empId);
                    continue;
                }

                // Hash the plaintext password
                String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));

                // Update the database with the hashed password
                updateStmt.setString(1, hashedPassword);
                updateStmt.setInt(2, empId);
                updateStmt.executeUpdate();

                System.out.println("Updated password for " + tableName + " emp_id: " + empId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
