package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeSystemViewGUI {
    private static final Logger LOGGER = Logger.getLogger(EmployeeSystemViewGUI.class.getName());
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public EmployeeSystemViewGUI() {
        JFrame frame = new JFrame();
        Image appIcon = new ImageIcon(getClass().getResource("/motorph_logo.png")).getImage();
        frame.setIconImage(appIcon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("MotorPH Payroll System: Employee View");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(true);
        frame.setLayout(null);

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

        // Panel for the Left Side
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(225, 249, 255, 255));
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBounds(0, 0, 300, 1080);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.ipady = 20; // This will increase the height of the buttons
        ImageIcon motorPHIcon = new ImageIcon("src/main/resources/motorph_logo.png");
        ImageIcon empIcon = new ImageIcon("src/main/resources/user.png");
        Image icon = empIcon.getImage();
        Image resizedempIcon = icon.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        empIcon = new ImageIcon(resizedempIcon);

        // MotorPH Logo
        JLabel motorph_logo = new JLabel(motorPHIcon);
        menuPanel.add(motorph_logo);

        //Admin Icon for Admin Panel
        JLabel empIconLabel = new JLabel(empIcon);
        JLabel adminLabel = new JLabel("Employee");
        adminLabel.setFont(new Font("Lato", Font.BOLD, 23));

        JPanel employeePanel = new JPanel();
        employeePanel.setPreferredSize(new Dimension(300, 60));
        employeePanel.setBackground(new Color(225, 249, 255, 255));
        employeePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        employeePanel.add(empIconLabel);
        employeePanel.add(adminLabel);

        // Add date and time label
        JLabel dateTimeLabel = new JLabel();
        dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        employeePanel.add(dateTimeLabel);

        // Timer to update the date and time label every second
        Timer timer = new Timer(1000, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
            dateTimeLabel.setText(sdf.format(new Date()));
            dateTimeLabel.setFont(new Font("Lato", Font.BOLD, 14));
        });
        timer.start();

        //Buttons Background Color
        Color bluish = new Color(0, 76, 153);

        // Creates new JButtons for the MenuPanel
        Font latoFont = new Font("Lato", Font.BOLD, 14);

        JButton dashboardBtn = new JButton("Dashboard");
        dashboardBtn.setFont(latoFont);
        dashboardBtn.setBackground(bluish);
        dashboardBtn.setForeground(Color.WHITE);

        JButton attendanceBtn = new JButton("Attendance");
        attendanceBtn.setFont(latoFont);
        attendanceBtn.setBackground(bluish);
        attendanceBtn.setForeground(Color.WHITE);

        JButton inquiryBtn = new JButton("Create Inquiry");
        inquiryBtn.setFont(latoFont);
        inquiryBtn.setBackground(bluish);
        inquiryBtn.setForeground(Color.WHITE);

        JButton leavesBtn = new JButton("Leave Requests");
        leavesBtn.setFont(latoFont);
        leavesBtn.setBackground(bluish);
        leavesBtn.setForeground(Color.WHITE);

        // Logout Button
        ImageIcon logoutIcon = new ImageIcon("src/main/resources/logout.png");
        Image resizedLogoutIcon = logoutIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon resizedLogout = new ImageIcon(resizedLogoutIcon);
        JButton exitBtn = new JButton("Logout", resizedLogout);
        exitBtn.setFont(latoFont);
        exitBtn.setBackground(new Color(0, 76, 153));
        exitBtn.setForeground(Color.WHITE);

        // GC.GRIDY Adds the Button in Order
        gbc.gridy = 0;
        menuPanel.add(motorph_logo, gbc);
        gbc.gridy = 1;
        menuPanel.add(employeePanel, gbc);
        gbc.gridy = 2;
        menuPanel.add(dashboardBtn, gbc);
        gbc.gridy = 3;
        menuPanel.add(attendanceBtn, gbc);
        gbc.gridy = 4;
        menuPanel.add(leavesBtn, gbc);
        gbc.gridy = 5;
        menuPanel.add(inquiryBtn, gbc);
        gbc.gridy = 6;
        menuPanel.add(exitBtn, gbc);

        //Create the main panel with CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.setBounds(300, 0, 1620, 1080);

        JPanel attendancePanel = new JPanel();
        attendancePanel.add(new JLabel("Attendance View"));
        attendancePanel.setLayout(new BorderLayout());

        JPanel inquiryPanel = new JPanel();
        inquiryPanel.add(new JLabel("Inquiry View"));
        inquiryPanel.setLayout(new BorderLayout());

        JPanel leavesPanel = new JPanel();
        leavesPanel.add(new JLabel("Leave Requests View"));
        leavesPanel.setLayout(new BorderLayout());

        // Add individual panels to the main panel
        mainPanel.add(attendancePanel, "Attendance");
        mainPanel.add(inquiryPanel, "Inquiry");
        mainPanel.add(leavesPanel, "Leave Requests");

        // Add action listeners to the buttons
        dashboardBtn.addActionListener(e -> {cardLayout.show(mainPanel, "Dashboard");});
        attendanceBtn.addActionListener(e -> cardLayout.show(mainPanel, "Attendance"));
        inquiryBtn.addActionListener(e -> cardLayout.show(mainPanel, "Create Inquiry"));
        leavesBtn.addActionListener(e -> cardLayout.show(mainPanel, "Leave Requests"));

        // Logs out of the System
        exitBtn.addActionListener(e -> {
            frame.dispose();
            // Assuming RoleLogin is another class responsible for login
            RoleLogin roleLogin = new RoleLogin();
            roleLogin.setVisible(true);
        });

        // Frame Components
        frame.getContentPane().add(menuPanel);
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of MainSystem to display the frame
        new EmployeeSystemViewGUI();
    }
}