package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdminSystemViewGUI {
    private final JFrame frame;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;
    private static final Logger LOGGER = Logger.getLogger(AdminSystemViewGUI.class.getName());
    public AdminSystemViewGUI() {
        frame = new JFrame("MotorPH: Administrator Mode");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setResizable(false);

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

        // Panel for the Left Side
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(203, 203, 203, 255));
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBounds(0, 0, 300, 1080);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.ipady = 20; // This will increase the height of the buttons

        JButton profileBtn = new JButton("Profile");
        JButton employeeRecordsBtn = new JButton("Employee Records");
        JButton payrollBtn = new JButton("Payroll");
        JButton attendanceBtn = new JButton("Attendance");
        JButton inquiryBtn = new JButton("Inquiry");
        JButton leavesBtn = new JButton("Leave Requests");
        JButton exitBtn = new JButton("Exit");

        gbc.gridy = 0;
        menuPanel.add(profileBtn, gbc);
        gbc.gridy = 1;
        menuPanel.add(payrollBtn, gbc);
        gbc.gridy = 2;
        menuPanel.add(attendanceBtn, gbc);
        gbc.gridy = 3;
        menuPanel.add(inquiryBtn, gbc);
        gbc.gridy = 4;
        menuPanel.add(leavesBtn, gbc);
        gbc.gridy = 5;
        menuPanel.add(employeeRecordsBtn, gbc);
        gbc.gridy = 6;
        menuPanel.add(exitBtn, gbc);

        // Create the main panel with CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.setBounds(300, 0, 1620, 1080);

        // Create individual panels for each button
        JPanel profilePanel = new JPanel();
        profilePanel.add(new JLabel("Profile View"));
        JPanel employeeRecordsPanel = new JPanel();
        employeeRecordsPanel.add(new JLabel("Employee Records"));
        JPanel payrollPanel = new JPanel();
        payrollPanel.add(new JLabel("Payroll View"));
        JPanel attendancePanel = new JPanel();
        attendancePanel.add(new JLabel("Attendance View"));
        JPanel inquiryPanel = new JPanel();
        inquiryPanel.add(new JLabel("Leave Requests View"));
        JPanel leavesPanel = new JPanel();
        leavesPanel.add(new JLabel("Report View"));


        // Add individual panels to the main panel
        mainPanel.add(profilePanel, "Profile");
        mainPanel.add(employeeRecordsPanel, "EmployeeRecords");
        mainPanel.add(payrollPanel, "Payroll");
        mainPanel.add(attendancePanel, "Attendance");
        mainPanel.add(inquiryPanel, "Inquiry");
        mainPanel.add(leavesPanel, "Leave Requests");

        // Add action listeners to the buttons
        profileBtn.addActionListener(e -> cardLayout.show(mainPanel, "Profile"));
        employeeRecordsBtn.addActionListener(e -> cardLayout.show(mainPanel, "EmployeeRecords"));
        payrollBtn.addActionListener(e -> cardLayout.show(mainPanel, "Payroll"));
        attendanceBtn.addActionListener(e -> cardLayout.show(mainPanel, "Attendance"));
        inquiryBtn.addActionListener(e -> cardLayout.show(mainPanel, "Inquiry"));
        leavesBtn.addActionListener(e -> cardLayout.show(mainPanel, "Leave Requests"));

        // Logs out of the System
        exitBtn.addActionListener(e ->  {
                AdminSystemViewGUI.this.frame.dispose();
                MainLogin mainLogin = new MainLogin();
                mainLogin.setVisible(true);
        });

        // Add panels to the frame
        frame.getContentPane().add(menuPanel);
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
    public static void main(String[] args) {
        new AdminSystemViewGUI();
    }
}