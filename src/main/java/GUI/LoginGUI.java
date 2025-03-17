package GUI;

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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginGUI {
    private static final Logger LOGGER = Logger.getLogger(LoginGUI.class.getName());
    private final JPasswordField passwordField;
    private final JCheckBox checkBox;
    private final String db_path = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
    private JFrame frame;

    public LoginGUI() {
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
        JTextField usernameField = new JTextField();
        usernameLabel.setFont(new Font("Lato", Font.PLAIN, 20));
        usernameLabel.setForeground(labelColor);
        usernameLabel.setBounds(200, 240, 100, 30);
        usernameField.setBounds(200, 280, 300, 40);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordLabel.setForeground(labelColor);
        passwordLabel.setFont(new Font("Lato", Font.PLAIN, 20));
        passwordLabel.setBounds(200, 340, 100, 30);
        passwordField.setBounds(200, 380, 300, 40);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Administrator");
        comboBox.addItem("Employee");
        comboBox.setBounds(200, 450, 300, 40);
        comboBox.setEditable(false);
        comboBox.setFont(new Font("Lato", Font.PLAIN, 20));

        checkBox = new JCheckBox("Show Password");
        checkBox.setFont(new Font("Lato", Font.PLAIN, 15));
        checkBox.setBounds(200, 500, 300, 40);
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

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Lato", Font.PLAIN, 15));
        loginButton.setForeground(labelColor);
        loginButton.setBounds(200, 550, 300, 50);
        loginButton.setBackground(new Color(2, 37, 101));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin(usernameField.getText(), new String(passwordField.getPassword()), (String) comboBox.getSelectedItem());
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(comboBox);
        panel.add(checkBox);
        panel.add(loginButton);
        frame.setVisible(true);
    }

    private void handleLogin(String username, String password, String role) {
        try (Connection conn = DriverManager.getConnection(db_path)) {
            if ("Administrator".equals(role)) {
                if (isValidAdmin(username, password, conn)) {
                    redirectToAdminSystemView();
                } else {
                    showError("Invalid admin credentials");
                }
            } else if ("Employee".equals(role)) {
                if (isValidEmployee(username, password, conn)) {
                    redirectToEmployeeSystemView();
                } else {
                    showError("Invalid employee credentials or unauthorized admin role");
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error during login", ex);
            showError("An error occurred during login");
        }
    }

    private boolean isValidAdmin(String username, String password, Connection conn) throws Exception {
        String sql = "SELECT * FROM AdminLoginCredentials WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    private boolean isValidEmployee(String username, String password, Connection conn) throws Exception {
        String sql = "SELECT * FROM LoginCredentials WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return !isValidAdmin(username, password, conn);
                }
                return false;
            }
        }
    }

    private void redirectToAdminSystemView() throws SQLException {
        JOptionPane.showMessageDialog(null, "Redirecting to Admin System View");
        frame.dispose();
        new AdminSystemViewGUI().setVisible(true);
    }

    private void redirectToEmployeeSystemView() {
        JOptionPane.showMessageDialog(null, "Redirecting to Employee System View");
        frame.dispose();
        new EmployeeSystemViewGUI().setVisible(true);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Login Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginGUI::new);
    }
}