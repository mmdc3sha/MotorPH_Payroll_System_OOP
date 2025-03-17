package GUI;

import AdminServices.PayrollServices;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminSystemViewGUI extends PayrollServices {
    private static final Logger LOGGER = Logger.getLogger(AdminSystemViewGUI.class.getName());
    private final String db_path = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
    private final JFrame frame;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;
    private static DefaultTableModel employeeRecordsModel;
    private static JTable employeeRecordsTable;
    private static DefaultTableModel registered_admin_accounts_model = new DefaultTableModel();
    private static DefaultTableModel registered_employee_accounts_model = new DefaultTableModel();
    private static DefaultTableModel payrollRecordsModel;
    private static JTable payrollRecordsTable;
    private static JDateChooser dateChooser;
    private static JDateChooser dateChooser2;
    private static JTextField empIDTextField;
    private static JTextField fNameTextField;
    private static JTextField lastNameTextField;
    private static JTextField positionTextField;
    private static JTextField daysWorkedTextField;
    private static JTextField hoursWorkedTextField;
    private static JTextField overtimeTextField;
    private static JTextField basicSalaryTextField;
    private static JTextField hrlyRateTextField;
    private static JTextField grossIncomeField;
    private static JTextField riceSubsidyTextField;
    private static JTextField phoneAllowanceTextField;
    private static JTextField clothAllowanceTextField;
    private static JTextField sssTextField;
    private static JTextField philHealthTextField;
    private static JTextField pagIBIGTextField;
    private static JTextField withholdingTextField;
    private static JTextField totalBenefitsTextField;
    private static JTextField totalDeductionsTextField;
    private static JTextField netIncomeTextField;
    private static JTextArea outputTextArea;
    private static JScrollPane outputScrollPane;
    private static JTextArea payrollHistoryOutput;

    public AdminSystemViewGUI() throws SQLException {
        dateChooser = new JDateChooser();
        dateChooser.setBackground(Color.white);
        dateChooser.setFont(new Font("Lato", Font.PLAIN, 15));
        dateChooser.setBounds(870,80,200,30);
        dateChooser.setDateFormatString("MM/dd/yyyy"); // Set the date format
        dateChooser.setDate(new Date()); // Set default date to current date
        // Creates the Date Chooser
        dateChooser2 = new JDateChooser();
        dateChooser2.setBackground(Color.white);
        dateChooser2.setFont(new Font("Lato", Font.PLAIN, 15));
        dateChooser2.setBounds(1200,80,200,30);
        dateChooser2.setDateFormatString("MM/dd/yyyy"); // Set the date format
        dateChooser2.setDate(new Date()); // Set default date to current date

        empIDTextField = new JTextField();
        empIDTextField.setBounds(870,130,150,40);
        empIDTextField.setFont(new Font("Lato", Font.PLAIN, 16));
        fNameTextField = new JTextField();
        lastNameTextField = new JTextField();
        positionTextField = new JTextField();
        daysWorkedTextField = new JTextField();
        hoursWorkedTextField = new JTextField();
        overtimeTextField = new JTextField();
        basicSalaryTextField = new JTextField();
        hrlyRateTextField = new JTextField();
        grossIncomeField = new JTextField();
        riceSubsidyTextField = new JTextField();
        phoneAllowanceTextField = new JTextField();
        clothAllowanceTextField = new JTextField();
        sssTextField = new JTextField();
        philHealthTextField = new JTextField();
        pagIBIGTextField = new JTextField();
        withholdingTextField = new JTextField();
        totalBenefitsTextField = new JTextField();
        totalDeductionsTextField = new JTextField();
        netIncomeTextField = new JTextField();
        outputTextArea = new JTextArea();
        outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        outputScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        super(dateChooser, dateChooser2, empIDTextField, fNameTextField, lastNameTextField, positionTextField, daysWorkedTextField, hoursWorkedTextField, overtimeTextField, basicSalaryTextField, hrlyRateTextField, grossIncomeField, riceSubsidyTextField, phoneAllowanceTextField, clothAllowanceTextField,
                sssTextField, philHealthTextField, pagIBIGTextField, withholdingTextField, totalBenefitsTextField, totalDeductionsTextField, netIncomeTextField, outputTextArea, outputScrollPane);



        frame = new JFrame("MotorPH: Administrator Mode");
        Image appIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/motorph_logo.png"))).getImage();
        frame.setIconImage(appIcon);
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setResizable(true);

        // Set Nimbus Look and Feel
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error setting Nimbus Look and Feel", e);
        }

        // Panel for the Left Side
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(58, 147, 255, 255));
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBounds(0, 0, 300, 1080);

        // Layout for the MenuPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.ipady = 20; // This will increase the height of the buttons

        //Image Icons
        ImageIcon motorPHIcon = new ImageIcon("src/main/resources/motorph_logo.png");
        ImageIcon adminIcon = new ImageIcon("src/main/resources/user-gear.png");
        Image icon = adminIcon.getImage();
        Image resizedAdminIcon = icon.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        adminIcon = new ImageIcon(resizedAdminIcon);

        // MotorPH Logo
        JLabel motorph_logo = new JLabel(motorPHIcon);
        menuPanel.add(motorph_logo);
        //Admin Icon for Admin Panel
        JLabel adminIconLabel = new JLabel(adminIcon);
        JLabel adminLabel = new JLabel(" Administrator");
        adminLabel.setForeground(new Color(255, 255, 255));
        adminLabel.setFont(new Font("Lato", Font.BOLD, 23));

        // Admin Panel - Displays "Administrator Label" and current system date and time
        JPanel adminPanel = new JPanel();
        adminPanel.setPreferredSize(new Dimension(300, 60));
        adminPanel.setBackground(new Color(58, 147, 255, 255));

        adminPanel.add(adminIconLabel);
        adminPanel.add(adminLabel);

        // Add date and time label
        JLabel dateTimeLabel = new JLabel();
        dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dateTimeLabel.setForeground(new Color(0, 0, 0));
        adminPanel.add(dateTimeLabel);

        // Timer to update the date and time label every second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
                dateTimeLabel.setText(sdf.format(new Date()));
                dateTimeLabel.setFont(new Font("Lato", Font.BOLD, 20));
                dateTimeLabel.setForeground(new Color(255, 255, 255));
            }
        });
        timer.start();

        // Creates new JButtons for the MenuPanel
        ImageIcon emp_recordsIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/emp_icon.png")));
        Font latoFont = new Font("Lato", Font.BOLD, 14);
        JButton employeeRecordsBtn = new JButton("Employee Records", emp_recordsIcon);
        employeeRecordsBtn.setFont(latoFont);
        employeeRecordsBtn.setBackground(new Color(255, 255, 255));
        employeeRecordsBtn.setForeground(new Color(2, 37, 101));
        employeeRecordsBtn.setHorizontalAlignment(SwingConstants.LEFT);
        employeeRecordsBtn.setHorizontalTextPosition(SwingConstants.RIGHT);

        ImageIcon payrollIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/salary.png")));
        JButton payrollBtn = new JButton("Payroll", payrollIcon);
        payrollBtn.setFont(latoFont);
        payrollBtn.setBackground(new Color(255, 255, 255));
        payrollBtn.setForeground(new Color(2, 37, 101));
        payrollBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        payrollBtn.setHorizontalAlignment(SwingConstants.LEFT);

        ImageIcon attendanceIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/calendar.png")));
        JButton attendanceBtn = new JButton("Attendance", attendanceIcon);
        attendanceBtn.setFont(latoFont);
        attendanceBtn.setBackground(new Color(255, 255, 255));
        attendanceBtn.setForeground(new Color(2, 37, 101));
        attendanceBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        attendanceBtn.setHorizontalAlignment(SwingConstants.LEFT);

        ImageIcon inquiryIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/message.png")));
        JButton inquiryBtn = new JButton("Inquiry", inquiryIcon);
        inquiryBtn.setFont(latoFont);
        inquiryBtn.setBackground(new Color(255, 255, 255));
        inquiryBtn.setForeground(new Color(2, 37, 101));
        inquiryBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        inquiryBtn.setHorizontalAlignment(SwingConstants.LEFT);

        ImageIcon leavesIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/briefcase.png")));
        JButton leavesBtn = new JButton("Leave Requests", leavesIcon);
        leavesBtn.setFont(latoFont);
        leavesBtn.setBackground(new Color(255, 255, 255));
        leavesBtn.setForeground(new Color(2, 37, 101));
        leavesBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        leavesBtn.setHorizontalAlignment(SwingConstants.LEFT);

        // Logout Button
        ImageIcon logoutIcon = new ImageIcon("src/main/resources/logout.png");
        Image resizedLogoutIcon = logoutIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon resizedLogout = new ImageIcon(resizedLogoutIcon);
        // Creates the Button for Logout
        JButton exitBtn = new JButton("Logout", resizedLogout);
        exitBtn.setHorizontalAlignment((SwingConstants.LEFT));
        exitBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
        exitBtn.setFont(latoFont);
        exitBtn.setBackground(new Color(255, 255, 255));
        exitBtn.setForeground(new Color(2, 37, 101));
        // Disposes this frame and opens the LoginGUI
        exitBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   int result = JOptionPane.showConfirmDialog(frame, "Would you like to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
                   if (result == JOptionPane.YES_OPTION) {
                       frame.dispose();
                       new LoginGUI();
                   }
               } catch (Exception ex) {
                   ex.printStackTrace();
               };
           }
        });

        // GC.GRIDY Adds the Button in Order
        gbc.gridx = 0;
        gbc.gridy = 0;
        menuPanel.add(motorph_logo, gbc);
        gbc.gridy = 2;
        menuPanel.add(adminPanel, gbc);
        gbc.gridy = 3;
        menuPanel.add(payrollBtn, gbc);
        gbc.gridy = 4;
        menuPanel.add(employeeRecordsBtn, gbc);
        gbc.gridy = 5;
        menuPanel.add(attendanceBtn, gbc);
        gbc.gridy = 6;
        menuPanel.add(leavesBtn, gbc);
        gbc.gridy = 7;
        menuPanel.add(inquiryBtn, gbc);
        gbc.gridy = 8;
        menuPanel.add(exitBtn, gbc);

        //Create the main panel with CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.setBounds(300, 0, 1620, 1080);

        //Create individual panels for each button
        JPanel employeeRecordsPanel = new JPanel();
        employeeRecordsPanel.setBackground(new Color(2, 88, 173));
        employeeRecordsPanel.add(new JLabel("Employee Records"));
        employeeRecordsPanel.setLayout(null);

        //Components for the Employee Records
        JLabel emp_record_label = new JLabel("Employee Record");
        emp_record_label.setFont(new Font("Lato", Font.BOLD, 20));
        emp_record_label.setBounds(30, 30, 400, 50);

        JTextField emp_record_search = new JTextField();
        emp_record_search.setBounds(130, 80, 300, 40);
        JButton searchBtn = new JButton("Search");
        searchBtn.setFont(latoFont);
        searchBtn.setBounds(30, 80, 100, 40);

        //Placeholder text for the Search Textfield
        String placeholder = "Search";
        emp_record_search.setText(placeholder);
        emp_record_search.setForeground(Color.GRAY);
        employeeRecordsPanel.setBackground(Color.WHITE);
        emp_record_search.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (emp_record_search.getText().equals(placeholder)) {
                    emp_record_search.setText("");
                    emp_record_search.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emp_record_search.getText().isEmpty()) {
                    emp_record_search.setText(placeholder);
                    emp_record_search.setForeground(Color.GRAY);
                }
            }
        });

        // Resizes the Refresh icon from 512 x 512 px -> 16 by 16.
        ImageIcon refreshIcon = new ImageIcon("src/main/resources/reload.png");
        Image resizedRefreshIcon = refreshIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon resizedRefresh = new ImageIcon(resizedRefreshIcon);

        //Adds the refresh Button
        JButton refreshBtn = new JButton(resizedRefresh);
        refreshBtn.setBounds(420, 80, 50, 40);

        //Adds the Employee Button - Displays a Popup window where you can add new employees to the record
        ImageIcon addIcon = new ImageIcon("src/main/resources/add.png");
        Image resizedAddIcon = addIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon resizedAdd = new ImageIcon(resizedAddIcon);
        JButton addEmployeeBtn = new JButton("Add", resizedAdd);
        addEmployeeBtn.setFont(latoFont);
        addEmployeeBtn.setBounds(470, 80, 100, 40);
        addEmployeeBtn.setToolTipText("Adds New Employee to the List");
        addEmployeeBtn.setBackground(new Color(5, 153, 10));
        addEmployeeBtn.setForeground(Color.WHITE);

        //Adds the Delete Employee Button - "Deletes a Cell in the Table"
        ImageIcon deleteIcon = new ImageIcon("src/main/resources/minus.png");
        Image resizedMinus = deleteIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        ImageIcon resizedMinusIcon = new ImageIcon(resizedMinus);
        JButton deleteEmployeeBtn = new JButton("Delete", resizedMinusIcon);
        deleteEmployeeBtn.setFont(latoFont);
        deleteEmployeeBtn.setBounds(570, 80, 100, 40);
        deleteEmployeeBtn.setBackground(new Color(158, 15, 10));
        deleteEmployeeBtn.setForeground(Color.WHITE);

        //Adds the Update Button - Updates the Table - "Commit New Changes"
        JButton updateEmployeeBtn = new JButton("Update");
        updateEmployeeBtn.setFont(latoFont);
        updateEmployeeBtn.setBounds(670, 80, 100, 40);
        updateEmployeeBtn.setBackground(new Color(141, 11, 181));
        updateEmployeeBtn.setForeground(Color.WHITE);

        // Creates a Table Model
        employeeRecordsModel = new DefaultTableModel();
        employeeRecordsModel.addColumn("ID");
        employeeRecordsModel.addColumn("Last Name");
        employeeRecordsModel.addColumn("First Name");
        employeeRecordsModel.addColumn("Birthday");
        employeeRecordsModel.addColumn("Address");
        employeeRecordsModel.addColumn("Phone Number");
        employeeRecordsModel.addColumn("SSS No.");
        employeeRecordsModel.addColumn("Philhealth No.");
        employeeRecordsModel.addColumn("TIN No.");
        employeeRecordsModel.addColumn("Pag-ibig No.");
        employeeRecordsModel.addColumn("Employment Status");
        employeeRecordsModel.addColumn("Immediate Supervisor");
        employeeRecordsModel.addColumn("Basic Salary");
        employeeRecordsModel.addColumn("Rate /hr");
        employeeRecordsModel.addColumn("Rice Subsidy");
        employeeRecordsModel.addColumn("Phone Allowance");
        employeeRecordsModel.addColumn("Clothing Allowance");
        employeeRecordsModel.addColumn("Gross Semi-Monthly Rate");

        // Creates the table
        employeeRecordsTable = new JTable(employeeRecordsModel);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(employeeRecordsModel);
        employeeRecordsTable.setRowSorter(sorter);
        employeeRecordsTable.setGridColor(Color.white);
        employeeRecordsTable.setFont(new Font("Calibri", Font.PLAIN, 15));
        employeeRecordsTable.setRowHeight(50);
        employeeRecordsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        employeeRecordsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Wraps the Table in a JScrollpane - adds a scrollbar
        JScrollPane scrollPane = new JScrollPane(employeeRecordsTable);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setViewportView(employeeRecordsTable);
        scrollPane.setBounds(30, 150, 1580, 840);

        // Table Models for the Admin Accouns and Employee Accounts

        String[] registered_accounts_columnNames = {"Employee ID","Username","Password"};
        // For loop para di paulit-ulit - simplifies the code when adding column names, also most efficient for adding new columns and removing
        for(String registered_adminModel : registered_accounts_columnNames ){
            registered_admin_accounts_model.addColumn(registered_adminModel);
        }
        for (String registered_employeeModel : registered_accounts_columnNames){
            registered_employee_accounts_model.addColumn(registered_employeeModel);
        }



        // Creates the JTable for Employee Accounts
        JTable registered_employee_accounts_table = new JTable(registered_employee_accounts_model);
        TableRowSorter<DefaultTableModel> employee_accounts_sorter = new TableRowSorter<>(registered_employee_accounts_model);
        registered_employee_accounts_table.setRowSorter(employee_accounts_sorter);
        registered_employee_accounts_table.setFont(new Font("Calibri", Font.PLAIN, 15));
        registered_employee_accounts_table.setRowHeight(50);
        registered_employee_accounts_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        registered_employee_accounts_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        //Creates the JTable for Admin Accounts
        JTable registered_admin_accounts_table = new JTable(registered_admin_accounts_model);
        TableRowSorter<DefaultTableModel> admin_accounts_sorter = new TableRowSorter<>(registered_admin_accounts_model);
        registered_admin_accounts_table.setRowSorter(admin_accounts_sorter);
        registered_admin_accounts_table.setFont(new Font("Calibri", Font.PLAIN, 15));
        registered_admin_accounts_table.setRowHeight(50);
        registered_admin_accounts_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        registered_admin_accounts_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Labels for the Employee and Admin Accounts Table
        JLabel registered_employeeLbl = new JLabel("Registered Employee");
        JLabel registered_adminLbl = new JLabel("Registered Admin");

        registered_adminLbl.setFont(latoFont);
        registered_adminLbl.setBounds(30,140,400, 50);
        registered_employeeLbl.setFont(latoFont);
        registered_employeeLbl.setBounds(850, 140, 400, 50);
        employeeRecordsPanel.add(registered_employeeLbl);
        employeeRecordsPanel.add(registered_adminLbl);
        registered_adminLbl.setVisible(false);
        registered_employeeLbl.setVisible(false);

        // Creates Scrolling within the Admin Table
        JScrollPane adminTable_scrollPane = new JScrollPane(registered_admin_accounts_table);
        adminTable_scrollPane.setWheelScrollingEnabled(true);
        adminTable_scrollPane.setBounds(30, 180, 800, 770);
        adminTable_scrollPane.setVisible(false);
        employeeRecordsPanel.add(adminTable_scrollPane);

        // Creates Scrolling within the Employee Table
        JScrollPane employeeTable_scrollPane = new JScrollPane(registered_employee_accounts_table);
        employeeTable_scrollPane.setWheelScrollingEnabled(true);
        employeeTable_scrollPane.setBounds(850, 180, 750, 770);
        employeeTable_scrollPane.setVisible(false);
        employeeRecordsPanel.add(employeeTable_scrollPane);

        // Creates Register button - registers new employees to the system--give access to log into the system
        JButton registerBtn = new JButton("Register");
        registerBtn.setFont(latoFont);
        registerBtn.setBounds(970, 80, 100, 40);
        registerBtn.setVisible(false);
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterAccountGUI(frame);
            }
        });
        employeeRecordsPanel.add(registerBtn);


        // Creates toggle button for Account Table
        JToggleButton accountToggleBtn = new JToggleButton("Registered Accounts");
        accountToggleBtn.setFont(latoFont);
        accountToggleBtn.setBounds(770, 80, 200, 40);
        employeeRecordsPanel.add(accountToggleBtn);

        // View Accounts Table Toggle button - if toggled, it will display account table
        accountToggleBtn.addActionListener(e -> {
            if (accountToggleBtn.isSelected()) {

                adminTable_scrollPane.setVisible(true);
                employeeTable_scrollPane.setVisible(true);
                emp_record_label.setText("Registered Accounts");
                registered_adminLbl.setVisible(true);
                registered_employeeLbl.setVisible(true);
                scrollPane.setVisible(false);
                accountToggleBtn.setText("Hide Accounts Table");
                accountToggleBtn.setToolTipText("Displayed Registered Accounts. Toggle to Display Employee Information.");
                registerBtn.setVisible(true);
            } else {
                registered_adminLbl.setVisible(false);
                registered_employeeLbl.setVisible(false);
                adminTable_scrollPane.setVisible(false);
                employeeTable_scrollPane.setVisible(false);
                emp_record_label.setText("Employee Record");
                accountToggleBtn.setText("Show Accounts Table");
                registerBtn.setVisible(false);
                scrollPane.setVisible(true);
            }
        });

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

        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorter.setRowFilter(null);
                emp_record_search.setText("");
            }
        });

        deleteEmployeeBtn.addActionListener(e -> {
            try {
                deleteEmployee();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        updateEmployeeBtn.addActionListener(e -> {
            String selectQuery = "SELECT * FROM Employee";

            try (Connection updateConn = DriverManager.getConnection(db_path);
                 Statement selectStmt = updateConn.createStatement();
                 ResultSet rs = selectStmt.executeQuery(selectQuery)) {

                // Clear the existing table model
                employeeRecordsModel.setRowCount(0);
                employeeRecordsModel.setColumnCount(0);

                // Get Column Names from the ResultSet and add to the model
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    employeeRecordsModel.addColumn(rs.getMetaData().getColumnName(i));
                }

                // Add rows from the ResultSet to the model
                while (rs.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    employeeRecordsModel.addRow(row);
                }

                JOptionPane.showMessageDialog(frame, "Update Successful.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error while updating Employee Record", "Failed", JOptionPane.ERROR_MESSAGE);
            }

        });

        // Add components to the Employee Records Panel
        employeeRecordsPanel.add(emp_record_label);
        employeeRecordsPanel.add(scrollPane);

        // Fetch data from the database and fill the table
        fetchData(employeeRecordsModel);
        fetchData_empAccounts(registered_employee_accounts_model);
        fetchData_adminAccounts(registered_admin_accounts_model);

        //Add components to the Employee Records Panel
        employeeRecordsPanel.add(emp_record_label);
        employeeRecordsPanel.add(searchBtn);
        employeeRecordsPanel.add(emp_record_search);
        employeeRecordsPanel.add(addEmployeeBtn);
        employeeRecordsPanel.add(deleteEmployeeBtn);
        employeeRecordsPanel.add(updateEmployeeBtn);
        employeeRecordsPanel.add(emp_record_label);
        employeeRecordsPanel.add(refreshBtn);

        //Create a Payroll Panel
        JPanel payrollPanel = new JPanel();
        payrollPanel.setBackground(new Color(25, 136, 173));
        payrollPanel.setLayout(new BoxLayout(payrollPanel, BoxLayout.Y_AXIS));
        // Payroll Panel Components
            JTabbedPane payrollTabbedPane = new JTabbedPane(); // Creates the Tabbed Pane
            payrollTabbedPane.setFont(new Font("Lato", Font.BOLD, 15)); // Sets the Tabs' font to Lato, BOLD, size 18
            payrollTabbedPane.setBackground(Color.white);

            // CALCULATE TAB
            JPanel calculateTab = new JPanel();
            calculateTab.setBackground(new Color(204, 242, 255, 255));
            calculateTab.setLayout(null);

            // Creates the Save File and Print Button
            JButton newFileBtn = new JButton("New");
            newFileBtn.setFont(new Font("Lato", Font.BOLD, 15));
            newFileBtn.setForeground(Color.white);
            newFileBtn.setBackground(new Color(29, 141, 53));
            newFileBtn.setToolTipText("Create New Payslip");
            // Resets the textfields to default
            newFileBtn.addActionListener(e -> {
                int newFilemsg = JOptionPane.showConfirmDialog(null, "Create New File?", "New", JOptionPane.YES_NO_OPTION);

                if (newFilemsg == JOptionPane.YES_OPTION) {
                    empIDTextField.setText("");
                    fNameTextField.setText("");
                    lastNameTextField.setText("");
                    positionTextField.setText("");
                    hoursWorkedTextField.setText("");
                    daysWorkedTextField.setText("");
                    overtimeTextField.setText("");
                    basicSalaryTextField.setText("");
                    hrlyRateTextField.setText("");
                    grossIncomeField.setText("");
                    riceSubsidyTextField.setText("");
                    clothAllowanceTextField.setText("");
                    phoneAllowanceTextField.setText("");
                    sssTextField.setText("");
                    philHealthTextField.setText("");
                    pagIBIGTextField.setText("");
                    withholdingTextField.setText("");
                    totalDeductionsTextField.setText("");
                    totalBenefitsTextField.setText("");
                    netIncomeTextField.setText("");
                    outputTextArea.setText("");
                }
            });


            JButton printPayslipBtn = new JButton("Print");
            printPayslipBtn.setBackground(new Color(114, 13, 119));
            printPayslipBtn.setForeground(Color.white);
            printPayslipBtn.setFont(new Font("Lato", Font.BOLD, 15));
            newFileBtn.setBounds(740,900,121,45);
            printPayslipBtn.setBounds(861,900,121,45);
            //Prints the Payslip
            printPayslipBtn.addActionListener(e -> {
               try {
                   boolean complete = outputTextArea.print();
                   if (complete) {
                       JOptionPane.showMessageDialog(null, "Payslip Printed Successfully.", "Print Completed", JOptionPane.INFORMATION_MESSAGE);
                   } else {
                       JOptionPane.showMessageDialog(frame, "Payslip Printed Failed.", "Print Failed", JOptionPane.ERROR_MESSAGE);
                   }
               } catch (PrinterException ex) {
                   JOptionPane.showMessageDialog(null, "Error while printing Payslip", "ERROR", JOptionPane.ERROR_MESSAGE);
               }
            });

            // Adds the Save File Button and Print Payslip Button
            calculateTab.add(newFileBtn);
            calculateTab.add(printPayslipBtn);

                //Pay Period
                JLabel PayPeriodlbl = new JLabel("Pay Period");
                PayPeriodlbl.setFont(new Font("Lato", Font.BOLD, 25));
                PayPeriodlbl.setForeground(Color.BLACK);
                PayPeriodlbl.setBounds(740,30,150,30);
                calculateTab.add(PayPeriodlbl);

                //Creates the JLabel for Period Start
                JLabel payStartDateLbl = new JLabel("Start Date:");
                payStartDateLbl.setFont(new Font("Lato", Font.PLAIN, 15));
                payStartDateLbl.setForeground(Color.BLACK);
                payStartDateLbl.setBounds(740,80,150,30);
                calculateTab.add(payStartDateLbl);

        // Creates the JLabel for Period End
        JLabel payEndDateLbl = new JLabel("End Date:");
        payEndDateLbl.setFont(new Font("Lato", Font.PLAIN, 15));
        payEndDateLbl.setForeground(Color.BLACK);
        payEndDateLbl.setBounds(1120,80,150,30);
        calculateTab.add(payEndDateLbl);
                calculateTab.add(dateChooser2);
                calculateTab.add(dateChooser);


            // Font and Color for the Totals Labels
            Font totalsLabelFont = new Font("Lato", Font.BOLD, 16);
            Color totalsLabelColor = new Color(2, 95, 5);
            Font labelFont2 = new Font("Lato", Font.PLAIN, 16);
            Color blackColor = Color.BLACK;
            // Create and add components
            JLabel empIDlbl = new JLabel("Employee ID:");
            empIDlbl.setFont(labelFont2);
            empIDlbl.setForeground(blackColor);
            empIDlbl.setBounds(740,130,200,40);


            JLabel fNameLbl = new JLabel("First Name:");
            fNameLbl.setBounds(740,190,200,40);
            fNameLbl.setFont(labelFont2);
            fNameLbl.setForeground(blackColor);
            fNameTextField.setBounds(870,190,250,40);
            fNameTextField.setFont(labelFont2);
            fNameTextField.setForeground(blackColor);

            JLabel lastNameLbl = new JLabel("Last Name:");
            lastNameLbl.setBounds(740,250,200,40);
            lastNameLbl.setFont(labelFont2);
            lastNameLbl.setForeground(blackColor);
            lastNameTextField.setBounds(870,250,250,40);
            lastNameTextField.setFont(labelFont2);
            lastNameTextField.setForeground(blackColor);

            JLabel positionLbl = new JLabel("Position:");
            positionLbl.setBounds(740,310,200,40);
            positionLbl.setFont(labelFont2);
            positionLbl.setForeground(blackColor);
            positionTextField.setBounds(870,310,250,40);
            positionTextField.setFont(labelFont2);
            positionTextField.setForeground(blackColor);

            JLabel daysWorkedLbl = new JLabel("Days Worked:");
            daysWorkedLbl.setBounds(740,370,120,40);
            daysWorkedLbl.setFont(labelFont2);
            daysWorkedLbl.setForeground(blackColor);
            daysWorkedTextField = new JTextField();
            daysWorkedTextField.setBounds(870,370,250,40);
            daysWorkedTextField.setFont(labelFont2);
            daysWorkedTextField.setForeground(blackColor);

            JLabel hoursWorkedLbl = new JLabel("Hours Worked:");
            hoursWorkedLbl.setBounds(740,430,150,40);
            hoursWorkedLbl.setFont(labelFont2);
            hoursWorkedLbl.setForeground(blackColor);
            hoursWorkedTextField.setBounds(870,430,250,40);
            hoursWorkedTextField.setFont(labelFont2);
            hoursWorkedTextField.setForeground(blackColor);

            JLabel overtimeLbl = new JLabel("Overtime(Hours):");
            overtimeLbl.setBounds(740,490,150,40);
            overtimeLbl.setFont(labelFont2);
            overtimeLbl.setForeground(blackColor);
            overtimeTextField.setBounds(870,490,250,40);
            overtimeTextField.setFont(labelFont2);
            overtimeTextField.setForeground(blackColor);

            JLabel basicSalaryLbl = new JLabel("Basic Salary:");
            basicSalaryLbl.setBounds(740,550,150,40);
            basicSalaryLbl.setFont(labelFont2);
            basicSalaryLbl.setForeground(blackColor);
            basicSalaryTextField.setBounds(870,550,250,40);
            basicSalaryTextField.setFont(labelFont2);
            basicSalaryTextField.setForeground(blackColor);

            JLabel hrlyRateLbl = new JLabel("Hourly Rate:");
            hrlyRateLbl.setBounds(740,610,150,40);
            hrlyRateLbl.setFont(labelFont2);
            hrlyRateLbl.setForeground(blackColor);
            hrlyRateTextField.setBounds(870,610,250,40);
            hrlyRateTextField.setFont(labelFont2);
            hrlyRateTextField.setForeground(blackColor);

            JLabel grossIncomeLbl = new JLabel("Gross Income:");
            grossIncomeLbl.setBounds(740,670,150,40);
            grossIncomeLbl.setFont(labelFont2);
            grossIncomeLbl.setForeground(blackColor);
            grossIncomeField.setBounds(870,670,250,40);
            grossIncomeField.setFont(totalsLabelFont);
            grossIncomeField.setForeground(totalsLabelColor);
            grossIncomeField.setEditable(false);

            JLabel benefitsLbl = new JLabel("Benefits");
            benefitsLbl.setBounds(1180, 130,150,50);
            benefitsLbl.setFont(new Font("Lato", Font.BOLD, 20));
            benefitsLbl.setForeground(blackColor);

            JLabel riceSubsidyLbl = new JLabel("Rice Subsidy:");
            riceSubsidyLbl.setFont(labelFont2);
            riceSubsidyLbl.setForeground(blackColor);
            riceSubsidyLbl.setBounds(1180,190,150,40);
            riceSubsidyTextField.setBounds(1330,190,250,40);
            riceSubsidyTextField.setFont(labelFont2);
            riceSubsidyTextField.setForeground(blackColor);

            JLabel phoneAllowanceLbl = new JLabel("Phone Allowance:");
            phoneAllowanceLbl.setBounds(1180,250,150,40);
            phoneAllowanceLbl.setFont(labelFont2);
            phoneAllowanceLbl.setForeground(blackColor);
            phoneAllowanceTextField.setBounds(1330,250,250,40);
            phoneAllowanceTextField.setFont(labelFont2);
            phoneAllowanceTextField.setForeground(blackColor);

            JLabel clothAllowanceLbl = new JLabel("Clothing Allowance:");
            clothAllowanceLbl.setBounds(1180,310,150,40);
            clothAllowanceLbl.setFont(labelFont2);
            clothAllowanceLbl.setForeground(blackColor);
            clothAllowanceTextField.setBounds(1330,310,250,40);
            clothAllowanceTextField.setFont(labelFont2);
            clothAllowanceTextField.setForeground(blackColor);

            //TAX
            JLabel deductionsLbl = new JLabel("Deductions");
            deductionsLbl.setBounds(1180,370,150,40);
            deductionsLbl.setFont(new Font("Lato", Font.BOLD, 20));
            deductionsLbl.setForeground(blackColor);

            JLabel sssLbl = new JLabel("SSS:");
            sssLbl.setBounds(1180,430,150,40);
            sssLbl.setFont(labelFont2);
            sssLbl.setForeground(blackColor);
            sssTextField.setBounds(1330,430,250,40);
            sssTextField.setFont(labelFont2);
            sssTextField.setForeground(blackColor);

            JLabel philHealthLbl = new JLabel("PhilHealth:");
            philHealthLbl.setBounds(1180,490,150,40);
            philHealthLbl.setFont(labelFont2);
            philHealthLbl.setForeground(blackColor);
            philHealthTextField.setBounds(1330,490,250,40);
            philHealthTextField.setFont(labelFont2);
            philHealthTextField.setForeground(blackColor);

            JLabel pagIBIGlbl = new JLabel("PagIBIG:");
            pagIBIGlbl.setBounds(1180,550,150,40);
            pagIBIGlbl.setFont(labelFont2);
            pagIBIGlbl.setForeground(blackColor);
            pagIBIGTextField.setBounds(1330,550,250,40);
            pagIBIGTextField.setFont(labelFont2);
            pagIBIGTextField.setForeground(blackColor);

            //Withholding Label
            JLabel withholdingLbl = new JLabel("Withholding Tax:");
            withholdingLbl.setFont(totalsLabelFont);
            withholdingLbl.setForeground(totalsLabelColor);
            withholdingLbl.setBounds(1180,610,150,40);

            // Withholding Textfield
            withholdingTextField.setBounds(1330,610,250,40);
            withholdingTextField.setFont(totalsLabelFont);
            withholdingTextField.setForeground(totalsLabelColor);
            withholdingTextField.setEditable(false);
            withholdingTextField.setBorder(new LineBorder(totalsLabelColor));

            // Total benefits Label
            JLabel totalBenefitsLbl = new JLabel("Total Benefits:");
            totalBenefitsLbl.setBounds(1180,670,150,40);
            totalBenefitsLbl.setFont(totalsLabelFont);
            totalBenefitsLbl.setForeground(totalsLabelColor);

            // Total Benefits Textfield
            totalBenefitsTextField.setEditable(false);
            totalBenefitsTextField.setBounds(1330,670,250,40);
            totalBenefitsTextField.setFont(totalsLabelFont);
            totalBenefitsTextField.setForeground(totalsLabelColor);
            totalBenefitsTextField.setBorder(new LineBorder(totalsLabelColor));

            //total Deductions
            JLabel totalDeductionsLbl = new JLabel("Total Deductions:");
            totalDeductionsLbl.setFont(totalsLabelFont);
            totalDeductionsLbl.setForeground(totalsLabelColor);
            totalDeductionsLbl.setBounds(1180,730,150,40);
            totalDeductionsTextField.setEditable(false);
            totalDeductionsTextField.setBounds(1330,730,250,40);
            totalDeductionsTextField.setBorder(new LineBorder(totalsLabelColor));
            totalDeductionsTextField.setFont(totalsLabelFont);

            //Net Income
            JLabel netIncomeLbl = new JLabel("Net Income:");
            netIncomeLbl.setFont(totalsLabelFont);
            netIncomeLbl.setForeground(totalsLabelColor);
            netIncomeLbl.setBounds(1180,790,150,40);
            netIncomeTextField.setEditable(false);
            netIncomeTextField.setBorder(new LineBorder(totalsLabelColor));
            netIncomeTextField.setBounds(1330,790,250,40);
            netIncomeTextField.setFont(totalsLabelFont);

            //Fill Button
            ImageIcon fillerIcon = new ImageIcon(getClass().getResource("/add-button.png"));
            JButton fillBtn = new JButton("",fillerIcon);
            fillBtn.setBounds(1020,130,100,40);
            fillBtn.setFont(labelFont2);
            fillBtn.setForeground(blackColor);
            fillBtn.setBackground(new Color(75, 188, 255));
            fillBtn.setHorizontalAlignment(SwingConstants.CENTER);
            fillBtn.addActionListener(new ActionListener() {
                /**
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        fillEmployeeInfo();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            //Calculate Payroll Button
            JButton calculateBtn = new JButton("Calculate");
            calculateBtn.setFont(new Font("Lato", Font.BOLD, 15));
            calculateBtn.setForeground(Color.white);
            calculateBtn.setBounds(982,900,121,45);
            calculateBtn.setBackground(new Color(6, 129, 181));

            calculateBtn.addActionListener(e -> {
                try {
                    calculatePayroll(); // ✅ Attempt to calculate payroll
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid number format! Please check your input.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            //Creates the Save Button
            JButton saveBtn = new JButton();
            saveBtn.setIcon(new ImageIcon(getClass().getResource("/save_icon.png")));
            saveBtn.setBounds(1105,900,60,45);
            saveBtn.setBackground(new Color(96, 152, 255));

            // Saves the Calculated Payslip
            saveBtn.addActionListener(e -> {

                    int choice = JOptionPane.showConfirmDialog(null,
                            "Do you want to save this Payslip?",
                            "Save Payslip",
                            JOptionPane.YES_NO_OPTION);

                    if(choice == JOptionPane.YES_NO_OPTION) {
                        try {
                            calculatePayroll();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        insertPayrollData(
                                Integer.parseInt(empIDTextField.getText()),
                                dateFormat.format(dateChooser.getDate()),
                                dateFormat.format(dateChooser2.getDate()),
                                fNameTextField.getText(),
                                lastNameTextField.getText(),
                                positionTextField.getText(),
                                Double.parseDouble(basicSalaryTextField.getText()),
                                Double.parseDouble(hrlyRateTextField.getText()),
                                Integer.parseInt(daysWorkedTextField.getText()),
                                Integer.parseInt(hoursWorkedTextField.getText()),
                                Integer.parseInt(overtimeTextField.getText()),
                                Double.parseDouble(grossIncomeField.getText()),
                                Double.parseDouble(riceSubsidyTextField.getText()),
                                Double.parseDouble(phoneAllowanceTextField.getText()),
                                Double.parseDouble(clothAllowanceTextField.getText()),
                                Double.parseDouble(totalBenefitsTextField.getText()),
                                Double.parseDouble(sssTextField.getText()),
                                Double.parseDouble(philHealthTextField.getText()),
                                Double.parseDouble(pagIBIGTextField.getText()),
                                Double.parseDouble(totalDeductionsTextField.getText()),
                                getTaxableIncome(),
                                Double.parseDouble(withholdingTextField.getText()),
                                Double.parseDouble(netIncomeTextField.getText())
                        );
                    }

            });

            // JTextArea that displays the Payroll History
            outputTextArea.setEditable(false);
            outputTextArea.setBackground(new Color(255, 255, 255));
            outputTextArea.setBounds(20, 40,700,900);
            outputTextArea.setMargin(new Insets(50,45,10,40));
            outputTextArea.setFont(new Font("Monospaced", Font.PLAIN, 22));
                calculateTab.add(outputTextArea);
                calculateTab.add(empIDlbl);
                calculateTab.add(empIDTextField);
                calculateTab.add(fNameLbl);
                calculateTab.add(fNameTextField);
                calculateTab.add(lastNameLbl);
                calculateTab.add(lastNameTextField);
                calculateTab.add(positionLbl);
                calculateTab.add(positionTextField);
                calculateTab.add(daysWorkedLbl);
                calculateTab.add(daysWorkedTextField);
                calculateTab.add(hoursWorkedLbl);
                calculateTab.add(hoursWorkedTextField);
                calculateTab.add(overtimeLbl);
                calculateTab.add(overtimeTextField);
                calculateTab.add(basicSalaryLbl);
                calculateTab.add(basicSalaryTextField);
                calculateTab.add(hrlyRateLbl);
                calculateTab.add(hrlyRateTextField);
                calculateTab.add(grossIncomeLbl);
                calculateTab.add(grossIncomeField);
                calculateTab.add(benefitsLbl);
                calculateTab.add(riceSubsidyLbl);
                calculateTab.add(riceSubsidyTextField);
                calculateTab.add(phoneAllowanceLbl);
                calculateTab.add(phoneAllowanceTextField);
                calculateTab.add(clothAllowanceLbl);
                calculateTab.add(clothAllowanceTextField);
                calculateTab.add(deductionsLbl);
                calculateTab.add(sssLbl);
                calculateTab.add(sssTextField);
                calculateTab.add(philHealthLbl);
                calculateTab.add(philHealthTextField);
                calculateTab.add(pagIBIGlbl);
                calculateTab.add(pagIBIGTextField);
                calculateTab.add(withholdingLbl);
                calculateTab.add(withholdingTextField);
                calculateTab.add(totalBenefitsLbl);
                calculateTab.add(totalBenefitsTextField);
                calculateTab.add(totalDeductionsLbl);
                calculateTab.add(totalDeductionsTextField);
                calculateTab.add(netIncomeLbl);
                calculateTab.add(netIncomeTextField);
                calculateTab.add(fillBtn);
                calculateTab.add(calculateBtn);
                calculateTab.add(saveBtn);
        // PAYROLL HISTORY TAB
        JPanel payrollHistory = new JPanel();
        payrollHistory.setBackground(new Color(25, 136, 173));
        payrollHistory.setLayout(null);

        //Creates the Search Textfield
        JTextField phistorySearchTextField = new JTextField();
        phistorySearchTextField.setBounds(830,20,300,30);

        payrollHistory.add(phistorySearchTextField);


        // Creates the Print Button for the JTable
        ImageIcon printIcon = new ImageIcon(getClass().getResource("/print_icon.png"));
        Image resizedPrint = printIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        printIcon = new ImageIcon(resizedPrint);
        JButton phistory_printBtn = new JButton("PRINT", printIcon);
        phistory_printBtn.setBackground(Color.WHITE);
        phistory_printBtn.setFont(new Font("Lato", Font.BOLD, 16));
        phistory_printBtn.setForeground(new Color(2, 37, 101));
        phistory_printBtn.setBounds(1490, 920,120,40);


        // Delete Button
        JButton phistory_updateBtn = new JButton("Update");
        phistory_updateBtn.setBackground(new Color(41, 207, 1));
        phistory_updateBtn.setFont(new Font("Lato", Font.BOLD, 14));
        phistory_updateBtn.setForeground(new Color(255, 255, 255));
        phistory_updateBtn.setBounds(1390, 20,110,35);
        payrollHistory.add(phistory_updateBtn);

        JButton phistory_delBtn = new JButton("Delete");
        phistory_delBtn.setBounds(1510, 20,100,35);
        phistory_delBtn.setBackground(new Color(223, 42, 53));
        phistory_delBtn.setForeground(new Color(255, 255, 255));
        payrollHistory.add(phistory_delBtn);

        // Payroll History Tab > Payroll History Output Panel - displays the selected row in the table
        payrollHistoryOutput = new JTextArea();
        payrollHistoryOutput.setBackground(new Color(255, 255, 255));
        payrollHistoryOutput.setBounds(20, 40, 700, 900);
        payrollHistoryOutput.setFont(new Font("Monospaced", Font.PLAIN, 23));
        payrollHistoryOutput.setMargin(new Insets(10,25,10,10));
        payrollHistoryOutput.setEditable(false);
        payrollHistory.add(payrollHistoryOutput);

        //Prints the Payroll History Output panel
        phistory_printBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean complete = payrollHistoryOutput.print();
                    if (complete) {
                        JOptionPane.showMessageDialog(null, "Payslip Printed Successfully.", "Print Completed", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Payslip Printed Failed.", "Print Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (PrinterException ex) {
                    JOptionPane.showMessageDialog(null, "Error while printing Payslip", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //Creates the Table Model
        payrollRecordsModel = new DefaultTableModel();
        payrollRecordsModel.addColumn("Payroll ID");
        payrollRecordsModel.addColumn("Employee ID");
        payrollRecordsModel.addColumn("Pay Period Date");
        payrollRecordsModel.addColumn("Pay Period End");
        payrollRecordsModel.addColumn("First Name");
        payrollRecordsModel.addColumn("Last Name");
        payrollRecordsModel.addColumn("Position");
        payrollRecordsModel.addColumn("Total Benefits");
        payrollRecordsModel.addColumn("Total Gross");
        payrollRecordsModel.addColumn("Total Deductions");
        payrollRecordsModel.addColumn("Net Income");

        // Creates the JTable for the Payroll History
        payrollRecordsTable = new JTable(payrollRecordsModel);
        TableRowSorter<TableModel> sorterPayroll = new TableRowSorter<>(payrollRecordsTable.getModel());
        payrollRecordsTable.setRowSorter(sorterPayroll);
        payrollRecordsTable.setRowHeight(40);
        payrollRecordsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        payrollRecordsTable.setFont(new Font("Calibri", Font.PLAIN, 18));
        for (int i = 0; i < payrollRecordsTable.getColumnModel().getColumnCount(); i++) {
            payrollRecordsTable.getColumnModel().getColumn(i).setPreferredWidth(100);
        }
        // Creates the Search button for the Payroll History Table
        JButton phistory_searchBtn = new JButton("Search");
        phistory_searchBtn.setBounds(750, 20,80,30);
        phistory_searchBtn.addActionListener(e -> {
            String keyword = phistorySearchTextField.getText();
            if (keyword.trim().isEmpty()) {
                sorterPayroll.setRowFilter(null);
            } else {
                sorterPayroll.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
            }
        });

        //Adds a scrollpane to the JTable
        JScrollPane payrollHistoryScrollPane = new JScrollPane(payrollRecordsTable);
        payrollHistoryScrollPane.setBounds(750, 60, 860, 850);
        payrollHistoryScrollPane.setWheelScrollingEnabled(true);
        payrollHistory.add(payrollHistoryScrollPane);
        payrollHistory.add(phistory_printBtn);

        // ADDED THE TABBED PANE TO THE PAYROLL PANEL
        displayPayrollRecord(payrollRecordsModel);
        payrollTabbedPane.addTab("Calculate", calculateTab);
        payrollTabbedPane.addTab("Payroll History", payrollHistory);
        payrollPanel.add(payrollTabbedPane);
        payrollHistory.add(phistory_searchBtn);

        //Updates the Table
        phistory_updateBtn.addActionListener(e -> {
            String selectQuery = "SELECT * FROM Payroll";

            try (Connection updateConn = DriverManager.getConnection(db_path);
                 Statement selectStmt = updateConn.createStatement();
                 ResultSet rs = selectStmt.executeQuery(selectQuery)) {

                // Clear the existing table model
                payrollRecordsModel.setRowCount(0);
                payrollRecordsModel.setColumnCount(0);

                // Get Column Names from the ResultSet and add to the model
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    payrollRecordsModel.addColumn(rs.getMetaData().getColumnName(i));
                }

                // Add rows from the ResultSet to the model
                while (rs.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    payrollRecordsModel.addRow(row);
                }
                JOptionPane.showMessageDialog(frame, "Update Successful.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error while updating Payroll Record", "Error", JOptionPane.ERROR_MESSAGE);
            }


        });

        // Detects the selected row
        payrollRecordsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = payrollRecordsTable.getSelectedRow();
                if (selectedRow != -1) {
                    int payrollID = (int) payrollRecordsModel.getValueAt(selectedRow, 0);
                    displayPayslip(payrollID);
                }
            }
        });

        //Deletes Payroll History Record
        phistory_delBtn.addActionListener(e -> {
            int delChoice = JOptionPane.showConfirmDialog(null, "Delete Record?", "Delete Record", JOptionPane.YES_NO_OPTION);
            if (delChoice == JOptionPane.YES_OPTION) {
                try {
                    int selectedRow = payrollRecordsTable.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(frame, "Please select a record to delete.");
                        return;
                    }

                    String payrollID = payrollRecordsModel.getValueAt(selectedRow, 0).toString();
                    String sql = "DELETE FROM Payroll WHERE payroll_id = ?";

                    try (Connection conn = DriverManager.getConnection(db_path);
                         PreparedStatement pstmnt = conn.prepareStatement(sql)) { // ✅ Closing parenthesis here

                        pstmnt.setString(1, payrollID);
                        int rowsAffected = pstmnt.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(frame, "Record Deleted Successfully.");
                            payrollRecordsModel.removeRow(selectedRow);
                        } else {
                            JOptionPane.showMessageDialog(frame, "No record deleted. Check if payroll ID exists.", "Warning", JOptionPane.WARNING_MESSAGE);
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Error in Deleting Record: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Unexpected error: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Creates the Attendance GUI
        JPanel attendancePanel = new JPanel();
        attendancePanel.setBackground(new Color(208, 237, 255)); // Changes the Color of the Attendance Panel to a bluish color
        attendancePanel.setLayout(null); // sets the Layout of the Attendance Panel to Null

        JLabel attendanceLabel = new JLabel("Attendance");
        attendanceLabel.setFont(new Font("Lato", Font.BOLD, 20));
        attendanceLabel.setBounds(30, 10, 200, 50);
        attendancePanel.add(attendanceLabel);

        JSeparator attendanceSeperator = new JSeparator();
        attendancePanel.add(attendanceSeperator);
        attendanceSeperator.setBounds(30, 55, 200, 50);

        //Creates a TableModel for the Attendance Tabel
        DefaultTableModel attendanceTableModel = new DefaultTableModel();
        TableRowSorter<DefaultTableModel> attendanceTable_sorter = new TableRowSorter<>(attendanceTableModel);
        attendanceTable_sorter.setSortsOnUpdates(true);
        JTable attendanceTable = new JTable(attendanceTableModel);
        attendanceTable.setRowHeight(20);
        attendanceTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        attendanceTable.setFont(new Font("Calibri", Font.PLAIN, 14));
        attendanceTable.setRowSorter(attendanceTable_sorter);

        //Fetches the Attendance Record Table from the SQLite Database
        try (Connection conn = DriverManager.getConnection(db_path)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from AttendanceRecord");

            //Get metadata to dynamically set columns
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            //Add columns to table Model
            for (int column = 1; column <= columnCount; column++) {
                attendanceTableModel.addColumn(metaData.getColumnName(column));
            }

            //Add rows to table model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    row[column - 1] = rs.getObject(column);
                }
                attendanceTableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //creates a ScrollPane and wraps the AttendanceTable
        JScrollPane attendanceScrollPane = new JScrollPane(attendanceTable);
        attendanceScrollPane.setBounds(30, 70, 860, 950);
        attendanceScrollPane.setWheelScrollingEnabled(true);
        attendancePanel.add(attendanceScrollPane);


        // Adds individual panels to the main panel
        mainPanel.add(employeeRecordsPanel, "EmployeeRecords");
        mainPanel.add(payrollPanel, "Payroll");
        mainPanel.add(attendancePanel, "Attendance");
        mainPanel.add(new JPanel(), "Inquiry");
        mainPanel.add(new JPanel(), "Leave Requests");

        // Add action listeners to the buttons
        employeeRecordsBtn.addActionListener(e -> cardLayout.show(mainPanel, "EmployeeRecords"));
        payrollBtn.addActionListener(e -> cardLayout.show(mainPanel, "Payroll"));
        attendanceBtn.addActionListener(e -> cardLayout.show(mainPanel, "Attendance"));
        inquiryBtn.addActionListener(e -> cardLayout.show(mainPanel, "Inquiry"));
        leavesBtn.addActionListener(e -> cardLayout.show(mainPanel, "Leave Requests"));

        // Frame Components
        frame.getContentPane().add(menuPanel);
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }


    //Method for Automatically filling the first name, last name and job position based on the Emp ID textfield
    private static void fillEmployeeInfo() throws SQLException {
        String employeeID = empIDTextField.getText();
        if(employeeID.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter Employee ID");
            return;
        }
        String dbURL = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
        String fillQuery = "SELECT first_name, last_name, job_position, basic_salary, hourly_rate, rice_subsidy, phone_allowance,clothing_allowance FROM Employee WHERE employee_id = ?";

        try(Connection connect =DriverManager.getConnection(dbURL);
            PreparedStatement ps = connect.prepareStatement(fillQuery);
        ) {

            ps.setString(1, employeeID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                fNameTextField.setText(rs.getString("first_name"));
                lastNameTextField.setText(rs.getString("last_name"));
                positionTextField.setText(rs.getString("job_position"));
                basicSalaryTextField.setText(rs.getString("basic_salary").replaceAll(",",""));
                hrlyRateTextField.setText(rs.getString("hourly_rate"));
                riceSubsidyTextField.setText(rs.getString("rice_subsidy").replaceAll(",",""));
                phoneAllowanceTextField.setText(rs.getString("phone_allowance").replaceAll(",",""));
                clothAllowanceTextField.setText(rs.getString("clothing_allowance").replaceAll(",",""));
            } else {
                JOptionPane.showMessageDialog(null, "Employee ID does not exist");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }


    }



    //Deletes Employee in the Employee Records Table
    public void deleteEmployee() throws SQLException {
        int selectedRow = employeeRecordsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select an employee to delete.");
            return;
        }
        String employeeID = employeeRecordsModel.getValueAt(selectedRow, 0).toString();
        String sql = "DELETE FROM Employee WHERE employee_id = ?";

        try (Connection conn = DriverManager.getConnection(db_path);
             PreparedStatement pstmnt = conn.prepareStatement(sql)
        ) {
            pstmnt.setString(1, employeeID);
            int rowsAffected = pstmnt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Employee Deleted Successfully.");
                employeeRecordsModel.removeRow(selectedRow);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error in Deleting Employee. Connection to Database Failed." + e.getMessage());
        }
    }




    // Fetches Data from 'LoginCredentials' Table
    private static void fetchData_adminAccounts(DefaultTableModel registered_admin_accounts_table) {
        String dbURL2 = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
        String sqlSelect = "SELECT emp_id, username, password FROM AdminLoginCredentials";


        try (Connection connect = DriverManager.getConnection(dbURL2);
             PreparedStatement pstmnt = connect.prepareStatement(sqlSelect);
             ResultSet rs = pstmnt.executeQuery()) {

            while (rs.next()) {
                int empId = rs.getInt("emp_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                // Add row to table model
                registered_admin_accounts_table.addRow(new Object[]{empId, username, password});
            }
            registered_admin_accounts_table.fireTableDataChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    //Fetches Registered Employee accounts from the LoginCredentials table
    private static void fetchData_empAccounts(DefaultTableModel registered_emp_accounts_table){
        String dbURL = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
        String sqlSelect = "SELECT emp_id, username, password FROM LoginCredentials";


        try (Connection connection = DriverManager.getConnection(dbURL);
             PreparedStatement pstmnt = connection.prepareStatement(sqlSelect);
             ResultSet rs = pstmnt.executeQuery()) {

            registered_emp_accounts_table.setRowCount(0);

            while (rs.next()) {
                int empId = rs.getInt("emp_id");
                String username = rs.getString("username");
                String password = rs.getString("password");

                // Add row to table model
                registered_emp_accounts_table.addRow(new Object[]{empId, username, password});

            }
            registered_emp_accounts_table.fireTableDataChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    //Fetches data from Employee Table
    private static void fetchData(DefaultTableModel emp_table){
        String dbURL = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
        String sqlSelect = "SELECT employee_id, last_name, first_name, birthday, address, phone_number, SSS_number, philhealth_number, " +
                "TIN_number, Pagibig_number, employment_status, job_position, Immediate_Supervisor, basic_salary, hourly_rate, " +
                "rice_subsidy, phone_allowance, clothing_allowance, gross_semi_monthly_rate FROM Employee";

        try (Connection conn = DriverManager.getConnection(dbURL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {

            emp_table.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("employee_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
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
                emp_table.addRow(row);
            }
            emp_table.fireTableDataChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayPayrollRecord(DefaultTableModel payrollRecordsModel) throws SQLException {
        String sql = "SELECT payroll_id, emp_id, pay_period_start, pay_period_end, first_name, last_name, job_position, gross_income, total_benefits, total_deductions, net_income FROM Payroll";
        try (Connection conn = DriverManager.getConnection(db_path);
             Statement stmnt = conn.createStatement();
             ResultSet rs = stmnt.executeQuery(sql)
        ) {
            payrollRecordsModel.setRowCount(0);

            while ((rs.next())) {
                payrollRecordsModel.addRow(new Object[]{
                        rs.getInt("payroll_id"),
                        rs.getInt("emp_id"),
                        rs.getInt("pay_period_start"),
                        rs.getString("pay_period_end"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("job_position"),
                        rs.getString("gross_income"),
                        rs.getDouble("total_benefits"),
                        rs.getDouble("total_deductions"),
                        rs.getDouble("net_income")
                });
            }
            payrollRecordsModel.fireTableDataChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve and Display Payslip Data in JTextArea
    private void displayPayslip(int payrollID) {
        try {
            String query = "SELECT * FROM Payroll WHERE payroll_id = ?";
            Connection connection = DriverManager.getConnection(db_path);
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, payrollID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String separator = "===========================================";

                // Retrieve payroll details
                int empID = rs.getInt("emp_id");
                String fName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String position = rs.getString("job_position");
                String startDate = rs.getString("pay_period_start");
                String endDate = rs.getString("pay_period_end");
                double basicSalary = rs.getDouble("basic_salary");
                double grossIncome = rs.getDouble("gross_income");
                double totalBenefits = rs.getDouble("total_benefits");
                double withholdingTax = rs.getDouble("withholding_tax");
                double totalDeductions = rs.getDouble("total_deductions");
                double netIncome = rs.getDouble("net_income");

                // Compute government contributions
                double sssContribution = getSSSContribution(basicSalary);
                double philHealthContribution = getPhilHealthContribution(basicSalary);
                double pagIBIGContribution = getPagIBIGContribution(basicSalary);

                // Format the payslip
                double totalEarnings = basicSalary + grossIncome + totalBenefits;
                String payslipOutput = separator + "\n"
                        + "PAYSLIP\n"
                        + separator + "\n"
                        + String.format("%-20s %20s%n", "Employee ID:", empID)
                        + String.format("%-20s %20s%n", "First Name:", fName)
                        + String.format("%-20s %20s%n", "Last Name:", lastName)
                        + String.format("%-20s %20s%n", "Position:", position)
                        + String.format("%-20s %20s%n", "Pay Period Start:", startDate)
                        + String.format("%-20s %20s%n", "Pay Period End:", endDate)
                        + separator + "\n"
                        + String.format("%-20s %20s%n", "EARNINGS", "AMOUNT")
                        + separator + "\n"
                        + String.format("%-20s %20s%n", "Basic Salary:", "PHP " + basicSalary)
                        + String.format("%-20s %20s%n", "Gross Income:", "PHP " + grossIncome)
                        + String.format("%-20s %20s%n", "Total Benefits:", "PHP " + totalBenefits)
                        + String.format("%-20s %20s%n", "Total Earnings:", "PHP " + totalEarnings)
                        + separator + "\n"
                        + String.format("%-20s %20s%n", "DEDUCTIONS", "AMOUNT")
                        + separator + "\n"
                        + String.format("%-20s %20s%n", "SSS Contribution:", "PHP " + sssContribution)
                        + String.format("%-20s %20s%n", "PhilHealth:", "PHP " + philHealthContribution)
                        + String.format("%-20s %20s%n", "Pag-IBIG:", "PHP " + pagIBIGContribution)
                        + String.format("%-20s %20s%n", "Tax Withholding:", "PHP " + withholdingTax)
                        + String.format("%-20s %20s%n", "Total Deductions:", "PHP " + totalDeductions)
                        + "-----------------------------------\n"
                        + String.format("%-20s %20s%n", "Net Salary:", "PHP " + netIncome)
                        + separator + "\n";

                payrollHistoryOutput.setText(payslipOutput);
            }

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static JDateChooser getDateChooser() {
        return dateChooser;
    }

    public static JDateChooser getDateChooser2() {
        return dateChooser2;
    }

    public static JTextField getEmpIDTextField() {
        return empIDTextField;
    }

    public static JTextField getfNameTextField() {
        return fNameTextField;
    }

    public static JTextField getLastNameTextField() {
        return lastNameTextField;
    }

    public static JTextField getPositionTextField() {
        return positionTextField;
    }

    public static JTextField getDaysWorkedTextField() {
        return daysWorkedTextField;
    }

    public static JTextField getHoursWorkedTextField() {
        return hoursWorkedTextField;
    }

    public static JTextField getOvertimeTextField() {
        return overtimeTextField;
    }

    public static JTextField getBasicSalaryTextField() {
        return basicSalaryTextField;
    }

    public static JTextField getHrlyRateTextField() {
        return hrlyRateTextField;
    }

    public static JTextField getGrossIncomeField() {
        return grossIncomeField;
    }

    public static JTextField getRiceSubsidyTextField() {
        return riceSubsidyTextField;
    }

    public static JTextField getPhoneAllowanceTextField() {
        return phoneAllowanceTextField;
    }

    public static JTextField getClothAllowanceTextField() {
        return clothAllowanceTextField;
    }

    public static JTextField getSssTextField() {
        return sssTextField;
    }

    public static JTextField getPhilHealthTextField() {
        return philHealthTextField;
    }

    public static JTextField getPagIBIGTextField() {
        return pagIBIGTextField;
    }

    public static JTextField getWithholdingTextField() {
        return withholdingTextField;
    }

    public static JTextField getTotalBenefitsTextField() {
        return totalBenefitsTextField;
    }

    public static JTextField getTotalDeductionsTextField() {
        return totalDeductionsTextField;
    }

    public static JTextField getNetIncomeTextField() {
        return netIncomeTextField;
    }

    //Sets the frame to Visible
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    //Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new AdminSystemViewGUI();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}