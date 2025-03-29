package GUI;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterAccountGUI extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(RegisterAccountGUI.class.getName());

    public RegisterAccountGUI(JFrame AdminSystemViewGUI) {
        setTitle("Register Account");
        getContentPane().setBackground(new Color(0, 50, 200));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 400);
        setResizable(false);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/user.png"));
        setIconImage(imageIcon.getImage());
        setLayout(null);

        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error setting Look and Feel", e);
        }

        Font labelFont = new Font("Lato", Font.PLAIN, 16);

        // Labels for the text fields
        JLabel idLabel = new JLabel("Register New Account");
        JLabel usernameLbl = new JLabel("Username:");
        JLabel passwordLbl = new JLabel("Password:");
        JLabel roleLbl = new JLabel("Role:");

        idLabel.setFont(new Font("Lato", Font.BOLD, 25));
        idLabel.setForeground(Color.WHITE);
        usernameLbl.setFont(labelFont);
        usernameLbl.setForeground(Color.WHITE);
        passwordLbl.setFont(labelFont);
        passwordLbl.setForeground(Color.WHITE);
        roleLbl.setFont(labelFont);
        roleLbl.setForeground(Color.WHITE);

        // Text fields
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        passwordField.setEchoChar((char) 0);
        JComboBox<String> roleComboBox = new JComboBox<>();
        roleComboBox.addItem("Administrator");
        roleComboBox.addItem("Employee");
        roleComboBox.setFont(labelFont);
        roleComboBox.setForeground(new Color(2, 37, 101));

        // Register and Cancel buttons
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Lato", Font.PLAIN, 14));
        registerButton.setForeground(new Color(2, 37, 101));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Lato", Font.PLAIN, 14));
        cancelButton.setForeground(new Color(2, 37, 101));

        // Set bounds for labels and fields
        idLabel.setBounds(50, 30, 400, 50);
        usernameLbl.setBounds(50, 100, 100, 30);
        usernameField.setBounds(150, 100, 300, 30);
        passwordLbl.setBounds(50, 150, 100, 30);
        passwordField.setBounds(150, 150, 300, 30);
        roleLbl.setBounds(50, 200, 100, 30);
        roleComboBox.setBounds(150, 200, 200, 30);
        registerButton.setBounds(150, 280, 100, 40);
        cancelButton.setBounds(260, 280, 100, 40);

        // Add components to frame
        add(idLabel);
        add(usernameLbl);
        add(usernameField);
        add(passwordLbl);
        add(passwordField);
        add(roleLbl);
        add(roleComboBox);
        add(registerButton);
        add(cancelButton);

        // Action listeners for buttons
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String role = (String) roleComboBox.getSelectedItem();

            if (username.isEmpty() || password.isEmpty() || role == null) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Hash the password before storing it
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
            String url = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
            String sql;

            if ("Administrator".equals(role)) {
                sql = "INSERT INTO AdminLoginCredentials (emp_id, username, password) VALUES (?, ?, ?)";
            } else {
                sql = "INSERT INTO LoginCredentials (emp_id, username, password) VALUES (?, ?, ?)";
            }

            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(2, username);
                pstmt.setString(3, hashedPassword);  // Store hashed password
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Account registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Error registering account", ex);
                JOptionPane.showMessageDialog(this, "Error registering account", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Close the dialog
            dispose();
        });

        cancelButton.addActionListener(e -> {
            // Close the dialog
            dispose();
        });

        // Set frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new RegisterAccountGUI(new JFrame())
        );
    }
}