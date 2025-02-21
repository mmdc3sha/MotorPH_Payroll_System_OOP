package GUI;

import AdminView.InterfaceAdminProfile;
import AdminView.InterfaceEmployeeRecords;

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

        //GC.GRIDY Adds the Button in Order
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
        profilePanel.setLayout(null);

        JPanel employeeRecordsPanel = new JPanel();
        employeeRecordsPanel.add(new JLabel("Employee Records"));
        employeeRecordsPanel.setLayout(null);

        JPanel payrollPanel = new JPanel();
        payrollPanel.add(new JLabel("Payroll View"));
        payrollPanel.setLayout(null);
            //2.PAYROLL PANEL
            //Payslip No.
            JLabel payslipNumLabel = new JLabel("Payslip No.:");
            JTextField payslipNumField = new JTextField();


            //Employee ID:
            JLabel emp_ID_label = new JLabel("Employee ID:");
            JTextField emp_ID_field = new JTextField("Enter employee ID");
            emp_ID_label.setBounds(10,30,200,30);
            emp_ID_field.setBounds(100, 30, 100, 30);

            //Employee Name
            JLabel emp_NameLabel = new JLabel("Employee Name:");
            JTextField emp_firstNameTxt = new JTextField("Enter First Name:");
            JLabel emp_surnameLabel = new JLabel("Employee Surname:");
            JTextField emp_surnameTxt = new JTextField("Enter Last Name:");

            //Period Start Date
            JLabel period_start_date = new JLabel("Period Start Date:");
            JTextField period_start_dateTxt = new JTextField("Period Start Date:");

            //Period End Date
            JLabel period_end_date = new JLabel("Period End Date:");
            JTextField period_end_dateTxt = new JTextField("Period End Date:");

            //Employee Position
            JComboBox<String> department = new JComboBox<>();
            //Employee Department
            JLabel employee_department_label = new JLabel();
            JComboBox<String> emp_department_box = new JComboBox<>();
            emp_department_box.addItem("HR Department");
            emp_department_box.addItem("IT Department");
            emp_department_box.addItem("Account Rank and File");
            emp_department_box.addItem("Sales & Marketing");
            emp_department_box.addItem("Customer Service & Relations");
            emp_department_box.addItem("Supply Chains and Logistic");
            emp_department_box.addItem("Finance Department");
        JPanel attendancePanel = new JPanel();
        attendancePanel.add(new JLabel("Attendance View"));
        attendancePanel.setLayout(null);

        JPanel inquiryPanel = new JPanel();
        inquiryPanel.add(new JLabel("Leave Requests View"));
        inquiryPanel.setLayout(null);

        JPanel leavesPanel = new JPanel();
        leavesPanel.add(new JLabel("Report View"));
        inquiryPanel.setLayout(null);

        //PROFILE PANEL

        // Add components to the AbstractPayroll Panel
            payrollPanel.add(emp_ID_field);
            payrollPanel.add(emp_ID_label);
            payrollPanel.add(emp_NameLabel);
            payrollPanel.add(emp_firstNameTxt);
            payrollPanel.add(emp_surnameLabel);
            payrollPanel.add(emp_surnameTxt);



        // Add individual panels to the main panel
        mainPanel.add(profilePanel, "Profile");
        mainPanel.add(employeeRecordsPanel, "EmployeeRecords");
        mainPanel.add(payrollPanel, "AbstractPayroll");
        mainPanel.add(attendancePanel, "Attendance");
        mainPanel.add(inquiryPanel, "Inquiry");
        mainPanel.add(leavesPanel, "Leave Requests");

        // Add action listeners to the buttons
        profileBtn.addActionListener(e -> cardLayout.show(mainPanel, "Profile"));
        employeeRecordsBtn.addActionListener(e -> cardLayout.show(mainPanel, "EmployeeRecords"));
        payrollBtn.addActionListener(e -> cardLayout.show(mainPanel, "AbstractPayroll"));
        attendanceBtn.addActionListener(e -> cardLayout.show(mainPanel, "Attendance"));
        inquiryBtn.addActionListener(e -> cardLayout.show(mainPanel, "Inquiry"));
        leavesBtn.addActionListener(e -> cardLayout.show(mainPanel, "Leave Requests"));

        // Logs out of the System
        exitBtn.addActionListener(e ->  {
                AdminSystemViewGUI.this.frame.dispose();
                RoleLogin roleLogin = new RoleLogin();
                roleLogin.setVisible(true);
        });

        // Frame Components
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