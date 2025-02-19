package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class LoginGUI extends JFrame {
    protected JTextField emp_ID_field;
    protected JTextField usernameField;
    protected JPasswordField passwordField;
    protected Connection connection;
    protected JButton loginButton;
    protected JButton menuButton;
    private static final Logger LOGGER = Logger.getLogger(LoginGUI.class.getName());

    public LoginGUI() {
        setTitle("MotorPH Payroll: Login as Employee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setLayout(null);
        setLocationRelativeTo(null);

        // Set Nimbus Look and Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error setting Nimbus Look and Feel", e);
        }

        // Connect to the Database (abstract method)
        connectToDatabase();

        // JLabel, TextField, PasswordField and Login Button
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel emp_idLabel = new JLabel("ID:");
        emp_ID_field = new JTextField(10);
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JCheckBox checkBox = new JCheckBox("Show Password");
        loginButton = new JButton("Login");
        menuButton = new JButton("Back");

        // Set the Location of the Components
        emp_idLabel.setBounds(800, 260, 300, 50);
        emp_ID_field.setBounds(800, 310, 300, 40);
        usernameLabel.setBounds(800, 350, 300, 50);
        usernameField.setBounds(800, 400, 300, 40);
        passwordLabel.setBounds(800, 435, 300, 50);
        passwordField.setBounds(800, 485, 300, 40);
        checkBox.setBounds(800, 550, 300, 20);
        loginButton.setBounds(800, 600, 300, 50);
        menuButton.setBounds(20, 950, 100, 50);

        // Add the Components to the JFrame
        add(emp_ID_field);
        add(loginButton);
        add(passwordField);
        add(usernameField);
        add(emp_idLabel);
        add(passwordLabel);
        add(usernameLabel);
        add(checkBox);
        add(menuButton);

        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainLogin mainLogin = new MainLogin();
                mainLogin.setVisible(true);
                dispose();
            }
        });

        // Action Listener for the CheckBox >> Show Password
        checkBox.addActionListener(e -> {
                if (checkBox.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Show Password
                } else {
                    passwordField.setEchoChar('*');
                }
        });



        // Action listener for the login button
        // If the login button is pressed, we want to verify the
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empID = emp_ID_field.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (verifyLogin(empID, username, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    // Proceed to the next screen or functionality
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
                }
            }
        });
    }
    //Partial Implementation
    //setups the connection "MotorPHDatabase"
    protected abstract void connectToDatabase();

    protected boolean verifyLogin(String empID, String username, String password) {
        String query = "SELECT * FROM LoginCredentials WHERE emp_id = ? AND username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, empID);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next(); // Returns true if a matching record is found
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error Logging in.", e);
            return false;
        }
    }

    public static void main(String[] args) {
        LoginGUI loginGUI = new LoginGUIConnection();
        loginGUI.setVisible(true);
    }
}