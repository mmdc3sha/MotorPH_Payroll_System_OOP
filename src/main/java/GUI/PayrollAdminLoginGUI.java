package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PayrollAdminLoginGUI extends LoginGUI{
    public PayrollAdminLoginGUI() {
        super();

        // Modify the title and UI components specific to Payroll Administrators
        setTitle("MotorPH Payroll : Login as Admin");

        // Action listener for the login button specific to Payroll Administrators
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empID = emp_ID_field.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (verifyAdminLogin(empID, username, password)) {
                    JOptionPane.showMessageDialog(null, "Admin login successful!");
                    // Proceed to the admin dashboard or functionality
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid admin credentials. Please try again.");
                }
            }
        });
    }

    @Override
    protected void connectToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/java/MotorPHDatabase.db");
            System.out.println("Connection to SQLite Admin Database has been established.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean verifyAdminLogin(String empID, String username, String password) {
        String query = "SELECT * FROM AdminLoginCredentials WHERE emp_id = ? AND username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, empID);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next(); // Returns true if a matching record is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        PayrollAdminLoginGUI adminLoginGUI = new PayrollAdminLoginGUI();
        adminLoginGUI.setVisible(true);
    }
}
