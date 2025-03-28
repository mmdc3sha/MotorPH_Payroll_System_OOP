package GUI;

import EmployeeServices.EmployeeSystemViewGUI;
import EmployeeServices.LoginSessionManager;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginGUI {
    private static final Logger LOGGER = Logger.getLogger(LoginGUI.class.getName());
    private static final String ADMIN_TABLE = "AdminLoginCredentials";
    private static final String EMPLOYEE_TABLE = "LoginCredentials";
    private final JPasswordField passwordField;
    private final JCheckBox checkBox;
    private final String db_path = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
    private final JTextField usernameField;
    private JFrame frame;
    private static JTextField empID;


    public LoginGUI() {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error setting FlatMacLightLaf Look and Feel", e);
        }

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("MotorPH Payroll System");
        frame.getContentPane().setLayout(null);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.white);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Objects.requireNonNull(this.getClass().getClassLoader().getResource("motorph_logo.png"))));

        try {
            BufferedImage originalImage = ImageIO.read(new File("src/main/resources/login_design.png"));
            BufferedImage resizedImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(originalImage, 0, 0, 800, 800, null);
            g2d.dispose();

            ImageIcon icon = new ImageIcon(resizedImage);
            JLabel design1 = new JLabel(icon);
            frame.getContentPane().add(design1);
            design1.setBounds(220, 60, 800, 800); // Positioning closer to the left with 800x800 dimensions
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.addKeyListener(new KeyAdapter() {
            private boolean isFullscreen = false;
            private Rectangle normalBounds = frame.getBounds();

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F11) {
                    GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                    if (isFullscreen) {
                        device.setFullScreenWindow(null);
                        frame.setBounds(normalBounds);
                    } else {
                        normalBounds = frame.getBounds();
                        device.setFullScreenWindow(frame);
                    }
                    isFullscreen = !isFullscreen;
                }
            }
        });
        frame.setFocusable(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(1240, 0, 800, 1080);
        panel.setBackground(new Color(36, 142, 213));
        frame.getContentPane().add(panel);

        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
        loginLabel.setForeground(new Color(255, 235, 235));
        loginLabel.setBounds(250, 100, 200, 50);
        panel.add(loginLabel);

        Color labelColor = new Color(255, 255, 255);

        JLabel empIDLabel = new JLabel("ID:");
        empIDLabel.setFont(new Font("Lato", Font.PLAIN, 20));
        empIDLabel.setBounds(200, 290, 200, 40);
        empIDLabel.setForeground(labelColor);
        empID = new JTextField();
        empID.setBackground(Color.WHITE);
        empID.setBounds(200, 330, 300,40);


        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        usernameLabel.setFont(new Font("Lato", Font.PLAIN, 20));
        usernameLabel.setForeground(labelColor);
        usernameLabel.setBounds(200, 380, 100, 30);
        usernameField.setBounds(200, 420, 300, 40);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordLabel.setForeground(labelColor);
        passwordLabel.setFont(new Font("Lato", Font.PLAIN, 20));
        passwordLabel.setBounds(200,470 , 100, 30);
        passwordField.setBounds(200, 510, 300, 40);

        // Creates a Combobox for system modes : Administrator & Employee
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Administrator");
        comboBox.addItem("Employee");
        comboBox.setBounds(200, 560, 300, 40);
        comboBox.setEditable(false);
        comboBox.setFont(new Font("Lato", Font.PLAIN, 20));

        //Creates a Checkbox for "Show Password" option
        checkBox = new JCheckBox("Show Password");
        checkBox.setFont(new Font("Lato", Font.PLAIN, 15));
        checkBox.setBounds(200, 600, 300, 40);
        checkBox.setForeground(labelColor);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });

        // Creates the Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Lato", Font.PLAIN, 15));
        loginButton.setForeground(labelColor);
        loginButton.setBounds(200, 650, 300, 50);
        loginButton.setBackground(new Color(2, 37, 101));

        loginButton.addActionListener(e -> {
            try {
                String employee_ID = empID.getText();
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars); // ✅ Correct conversion
                String role = comboBox.getSelectedItem().toString();

                handleLogin(employee_ID, username, password, role);

                Arrays.fill(passwordChars, ' '); // 🔒 Security: Clear password from memory
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


        //Adds the Swing components to the Panel
        panel.add(empIDLabel);
        panel.add(empID);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(comboBox);
        panel.add(checkBox);
        panel.add(loginButton);
        frame.setVisible(true);
    }

    // Handles the validity of Login Credentials
    private void handleLogin(String empID, String username, String password, String role) {
        try (Connection conn = DriverManager.getConnection(db_path)) {
            boolean isAuthenticated = authenticateUser(empID, username, password, role, conn);

            if (isAuthenticated) {
                // Store logged-in Employee ID
                LoginSessionManager.getInstance().setLoggedInEmpID(Integer.parseInt(empID));

                if ("Administrator".equals(role)) {
                    redirectToAdminSystemView();
                } else if ("Employee".equals(role)) {
                    redirectToEmployeeSystemView();
                }
            } else {
                showError("Invalid credentials");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error during login", ex);
            showError("An error occurred during login");
        }
    }
    // Generic authentication method for both Admins and Employees
    private boolean authenticateUser(String empID, String username, String password, String role, Connection conn) throws Exception {
        String tableName = "Administrator".equals(role) ? ADMIN_TABLE : EMPLOYEE_TABLE;
        String sql = "SELECT password FROM " + tableName + " WHERE emp_id = ? AND username = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, empID);
            pstmt.setString(2, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedHashedPassword = rs.getString("password");
                    return verifyPassword(password, storedHashedPassword);
                }
            }
        }
        return false;
    }

    // Secure password verification using hashing (Replace with a real implementation)
    private boolean verifyPassword(String inputPassword, String storedHashedPassword) {
        // Implement password hashing verification using BCrypt or PBKDF2
        return inputPassword.equals(storedHashedPassword); // Placeholder; Replace with proper password hash comparison
    }

    private void redirectToAdminSystemView() throws SQLException {
        JOptionPane.showMessageDialog(null, "Redirecting to Admin System View");
        frame.dispose();
        new AdminSystemViewGUI().setVisible(true);
    }

    private void redirectToEmployeeSystemView() {
        JOptionPane.showMessageDialog(null, "Redirecting to Employee System View");
        frame.dispose();
        new EmployeeSystemViewGUI();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Login Error", JOptionPane.ERROR_MESSAGE);
    }

    public JTextField getEmpID() {
        return empID;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(LoginGUI::new);

    }
}