package GUI;

import AdminView.InterfaceAdminProfile;
import AdminView.InterfaceEmployeeRecords;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        frame.setResizable(true);

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

        // Layout for the MenuPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.ipady = 20; // This will increase the height of the buttons

        ImageIcon adminIcon = new ImageIcon("src/main/resources/user-gear.png");
        Image icon = adminIcon.getImage();
        Image resizedAdminIcon = icon.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        adminIcon = new ImageIcon(resizedAdminIcon);
        JLabel adminIconLabel = new JLabel(adminIcon);
        JLabel adminLabel = new JLabel(" Administrator");
        adminLabel.setFont(new Font("Lato", Font.BOLD, 16));

        JPanel adminPanel = new JPanel();
        adminPanel.setPreferredSize(new Dimension(300, 60));
        adminPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        adminPanel.add(adminIconLabel);
        adminPanel.add(adminLabel);
        // Add date and time label
        JLabel dateTimeLabel = new JLabel();
        dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        adminPanel.add(dateTimeLabel);

        // Timer to update the date and time label every second
        Timer timer = new Timer(1000, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
            dateTimeLabel.setText(sdf.format(new Date()));
            dateTimeLabel.setFont(new Font("Lato", Font.BOLD, 14));
        });
        timer.start();
        // Creating new JButtons for the MenuPanel
        Font latoFont = new Font("Lato", Font.BOLD, 13);
        JButton employeeRecordsBtn = new JButton("Employee Records");
        employeeRecordsBtn.setFont(latoFont);
        JButton payrollBtn = new JButton("Payroll");
        payrollBtn.setFont(latoFont);
        JButton attendanceBtn = new JButton("Attendance");
        attendanceBtn.setFont(latoFont);
        JButton inquiryBtn = new JButton("Inquiry");
        inquiryBtn.setFont(latoFont);
        JButton leavesBtn = new JButton("Leave Requests");
        leavesBtn.setFont(latoFont);
        JButton exitBtn = new JButton("Logout");
        exitBtn.setFont(latoFont);

        //GC.GRIDY Adds the Button in Order
            gbc.gridy = 0;
            menuPanel.add(adminPanel, gbc);
            gbc.gridy = 1;
            menuPanel.add(payrollBtn, gbc);
            gbc.gridy = 2;
            menuPanel.add(employeeRecordsBtn, gbc);
            gbc.gridy = 3;
            menuPanel.add(attendanceBtn, gbc);
            gbc.gridy = 4;
            menuPanel.add(leavesBtn, gbc);
            gbc.gridy = 5;
            menuPanel.add(inquiryBtn, gbc);
            gbc.gridy = 6;
            menuPanel.add(exitBtn, gbc);

        // Create the main panel with CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.setBounds(300, 0, 1620, 1080);

        // Create individual panels for each button
        JPanel employeeRecordsPanel = new JPanel();
        employeeRecordsPanel.add(new JLabel("Employee Records"));
        employeeRecordsPanel.setLayout(null);
            // Components for the Employee Records
            JLabel emp_record_label = new JLabel("Employee Record");
            emp_record_label.setFont(new Font("Lato", Font.BOLD, 20));
            emp_record_label.setBounds(30, 30, 400, 20);

            JTextField emp_record_search = new JTextField();
            emp_record_search.setBounds(130, 80, 300, 40);
            JButton searchBtn = new JButton("Search");
            searchBtn.setFont(latoFont);
            searchBtn.setBounds(30, 80, 100, 40);
            //Placeholder text for the Search Textfield
            String placeholder = "Search";
            emp_record_search.setText(placeholder);
            emp_record_search.setForeground(Color.GRAY);
            emp_record_search.addFocusListener(new FocusListener() {

                /**
                 * @param e the event to be processed
                 */
                @Override
                public void focusGained(FocusEvent e) {
                    if (emp_record_search.getText().equals(placeholder)) {
                        emp_record_search.setText("");
                        emp_record_search.setForeground(Color.BLACK);
                    }
                }

                /**
                 * @param e the event to be processed
                 */
                @Override
                public void focusLost(FocusEvent e) {
                    if (emp_record_search.getText().isEmpty()) {
                        emp_record_search.setText(placeholder);
                        emp_record_search.setForeground(Color.GRAY);
                    }
                }
            });

            JButton addEmployeeBtn = new JButton("Add");
            addEmployeeBtn.setFont(latoFont);
            addEmployeeBtn.setBounds(430, 80, 100, 40);
            JButton deleteEmployeeBtn = new JButton("Delete");
            deleteEmployeeBtn.setFont(latoFont);
            deleteEmployeeBtn.setBounds(530, 80, 100, 40);
            JButton updateEmployeeBtn = new JButton("Update");
            updateEmployeeBtn.setFont(latoFont);
            updateEmployeeBtn.setBounds(630, 80, 100, 40);

            DefaultTableModel emp_table = new DefaultTableModel();
            emp_table.addColumn("ID");
            emp_table.addColumn("Last Name");
            emp_table.addColumn("First Name");
            emp_table.addColumn("Birthday");
            emp_table.addColumn("Address");
            emp_table.addColumn("Phone Number");
            emp_table.addColumn("SSS No.");
            emp_table.addColumn("Philhealth No.");
            emp_table.addColumn("TIN No.");
            emp_table.addColumn("Pag-ibig No.");
            emp_table.addColumn("Employment Status");
            emp_table.addColumn("Immediate Supervisor");
            emp_table.addColumn("Basic Salary");
            emp_table.addColumn("Rate /hr");
            emp_table.addColumn("Rice Subsidy");
            emp_table.addColumn("Phone Allowance");
            emp_table.addColumn("Clothing Allowance");
            emp_table.addColumn("Gross Semi-Monthly Rate");

            JTable employeeRecordsTable = new JTable(emp_table);
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(emp_table);
            employeeRecordsTable.setRowSorter(sorter);
            employeeRecordsTable.setGridColor(Color.white);
            employeeRecordsTable.setFont(new Font("Calibri", Font.PLAIN, 13));
            employeeRecordsTable.setRowHeight(30);
            employeeRecordsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            employeeRecordsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            JScrollPane scrollPane = new JScrollPane(employeeRecordsTable);
            scrollPane.setBounds(30, 120, 1580, 840);

        //Buttons Functions - addEmployeeBtn, deleteEmployeeBtn, updateEmployeeBtn
        addEmployeeBtn.addActionListener(e -> {
            AddEmployeeGUI gui = new AddEmployeeGUI(frame);
            gui.setVisible(true);
        });

        // Sorts the table when a keyword is typed into the Search Textfield
        searchBtn.addActionListener(e -> {
            String keyword = emp_record_search.getText();
            if (keyword.trim().isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
            }
        });

        // Add components to the Employee Records Panel
        employeeRecordsPanel.add(emp_record_label);
        employeeRecordsPanel.add(scrollPane);
        // Fetch data from the database and fill the table
        fetchData(emp_table);

            //Add components to the Employee Records Panel
            employeeRecordsPanel.add(emp_record_label);
            employeeRecordsPanel.add(searchBtn);
            employeeRecordsPanel.add(emp_record_search);
            employeeRecordsPanel.add(addEmployeeBtn);
            employeeRecordsPanel.add(deleteEmployeeBtn);
            employeeRecordsPanel.add(updateEmployeeBtn);
            employeeRecordsPanel.add(emp_record_label);



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
            emp_ID_label.setBounds(20,30,200,30);
            emp_ID_field.setBounds(150, 30, 150, 30);

            //Employee Name
            JLabel emp_NameLabel = new JLabel("First Name:");
            JTextField emp_firstNameTxt = new JTextField("Enter First Name:");
                emp_NameLabel.setBounds(20, 80, 200, 30);
                emp_firstNameTxt.setBounds(150, 80, 200, 30);

            JLabel emp_surnameLabel = new JLabel("Last Name:");
            JTextField emp_surnameTxt = new JTextField("Enter Last Name:");
                emp_surnameLabel.setBounds(20, 120, 200, 30);
                emp_surnameTxt.setBounds(150, 120, 200, 30);
            //Period Start Date
            JLabel period_start_date = new JLabel("Period Start Date:");
            JTextField period_start_dateTxt = new JTextField("MM/DD/YYYY");
                period_start_date.setBounds(20, 160, 200, 30);
                period_start_dateTxt.setBounds(150, 160, 200, 30);
            //Period End Date
            JLabel period_end_date = new JLabel("Period End Date:");
            JTextField period_end_dateTxt = new JTextField("MM/DD/YYYY");
                period_end_date.setBounds(20, 200, 200, 30);
                period_end_dateTxt.setBounds(150, 200, 200, 30);
            //Employee Position
            JComboBox<String> department = new JComboBox<>();
            department.addItem("IT");
            department.addItem("HR");
            department.addItem("Employee");
            department.addItem("Manager");
            //Employee Department
            JLabel employee_department_label = new JLabel();
                employee_department_label.setText("Department:");
            JComboBox<String> emp_department_box = new JComboBox<>();
                emp_department_box.addItem("HR Department");
                emp_department_box.addItem("IT Department");
                emp_department_box.addItem("Sales & Marketing");
                emp_department_box.addItem("Customer Service & Relations");
                emp_department_box.addItem("Supply Chains and Logistic");
                emp_department_box.addItem("Finance Department");
                employee_department_label.setBounds(20, 240, 200, 30);
                emp_department_box.setBounds(150, 240, 200, 30);


        JPanel attendancePanel = new JPanel();
        attendancePanel.add(new JLabel("Attendance View"));
        attendancePanel.setLayout(null);

        JPanel inquiryPanel = new JPanel();
        inquiryPanel.add(new JLabel("Leave Requests View"));
        inquiryPanel.setLayout(null);

        JPanel leavesPanel = new JPanel();
        leavesPanel.add(new JLabel("Report View"));
        inquiryPanel.setLayout(null);

        // Add components to the AbstractPayroll Panel
            payrollPanel.add(emp_ID_field);
            payrollPanel.add(emp_ID_label);
            payrollPanel.add(emp_NameLabel);
            payrollPanel.add(emp_firstNameTxt);
            payrollPanel.add(emp_surnameLabel);
            payrollPanel.add(emp_surnameTxt);
            payrollPanel.add(period_start_date);
            payrollPanel.add(period_start_dateTxt);
            payrollPanel.add(period_end_date);
            payrollPanel.add(period_end_dateTxt);
            payrollPanel.add(department);
            payrollPanel.add(employee_department_label);
            payrollPanel.add(emp_department_box);


        // Add individual panels to the main panel
        mainPanel.add(employeeRecordsPanel, "EmployeeRecords");
        mainPanel.add(payrollPanel, "AbstractPayroll");
        mainPanel.add(attendancePanel, "Attendance");
        mainPanel.add(inquiryPanel, "Inquiry");
        mainPanel.add(leavesPanel, "Leave Requests");

        // Add action listeners to the buttons
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
        frame.getContentPane().add(menuPanel);
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }
    private void fetchData(DefaultTableModel employeeRecordsModel) {
        String dbURL = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
        String sql = "SELECT employee_id, last_name, first_name, birthday, address, phone_number, SSS_number, philhealth_number, " +
                "TIN_number, Pagibig_number, employment_status, job_position, Immediate_Supervisor, basic_salary, hourly_rate, " +
                "rice_subsidy, phone_allowance, clothing_allowance, gross_semi_monthly_rate, * FROM Employee";

        try (Connection conn = DriverManager.getConnection(dbURL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {
            
            employeeRecordsModel.setRowCount(0);
            
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("Employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("birthday"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("SSS_number"),
                        rs.getString("philhealth_number"),
                        rs.getString("TIN_number"),
                        rs.getString("Pagibig_number"),
                        rs.getString("employment_status"),
                        rs.getString("job_position"),
                        rs.getString("Immediate_Supervisor"),
                        rs.getString("basic_salary"),
                        rs.getString("hourly_rate"),
                        rs.getString("rice_subsidy"),
                        rs.getString("phone_allowance"),
                        rs.getString("clothing_allowance"),
                        rs.getDouble("gross_semi_monthly_rate")
                };
                employeeRecordsModel.addRow(row);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
    public static void main(String[] args) {
        new AdminSystemViewGUI();
    }
}