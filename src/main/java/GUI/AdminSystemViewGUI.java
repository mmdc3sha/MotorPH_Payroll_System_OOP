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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdminSystemViewGUI {
    private final String db_path = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
    private final JFrame frame;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;
    private static final Logger LOGGER = Logger.getLogger(AdminSystemViewGUI.class.getName());
    // --Commented out by Inspection (07/03/2025 6:44 PM// --Commented out by Inspection (07/03/2025 6:44 PM):):private DefaultTableModel employeeRecordsModel;
    private final DefaultTableModel employeeRecordsModel;
    private final JTable employeeRecordsTable;

    public AdminSystemViewGUI() throws SQLException {
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
        adminLabel.setForeground(new Color(2, 2, 255));
        adminLabel.setFont(new Font("Lato", Font.BOLD, 23));

        // Admin Panel - Displays "Administrator Label" and current system date and time
        JPanel adminPanel = new JPanel();
        adminPanel.setPreferredSize(new Dimension(300, 60));
        adminPanel.setBackground(new Color(225, 249, 255, 255));
        adminPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        adminPanel.add(adminIconLabel);
        adminPanel.add(adminLabel);

        // Add date and time label
        JLabel dateTimeLabel = new JLabel();
        dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dateTimeLabel.setForeground(new Color(1, 255, 0));
        adminPanel.add(dateTimeLabel);

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
        JButton employeeRecordsBtn = new JButton("Employee Records");
        employeeRecordsBtn.setFont(latoFont);
        employeeRecordsBtn.setBackground(bluish);
        employeeRecordsBtn.setForeground(Color.WHITE);

        JButton payrollBtn = new JButton("Payroll");
        payrollBtn.setFont(latoFont);
        payrollBtn.setBackground(bluish);
        payrollBtn.setForeground(Color.WHITE);

        JButton attendanceBtn = new JButton("Attendance");
        attendanceBtn.setFont(latoFont);
        attendanceBtn.setBackground(bluish);
        attendanceBtn.setForeground(Color.WHITE);

        JButton inquiryBtn = new JButton("Inquiry");
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
        employeeRecordsPanel.setBackground(new Color(204, 245, 255));
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
        DefaultTableModel registered_admin_accounts_model = new DefaultTableModel();
        DefaultTableModel registered_employee_accounts_model = new DefaultTableModel();
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
        registerBtn.addActionListener(e ->{
            new RegisterAccountGUI(frame);
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
                registered_adminLbl.setVisible(true);
                registered_employeeLbl.setVisible(true);
                adminTable_scrollPane.setVisible(true);
                employeeTable_scrollPane.setVisible(true);
                emp_record_label.setText("Registered Accounts");
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

        refreshBtn.addActionListener(e -> {
            sorter.setRowFilter(null);
            emp_record_search.setText("");
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

        //Creates Payroll Panel-
        JPanel payrollPanel = new JPanel();
        payrollPanel.add(new JLabel("Payroll View"));
        payrollPanel.setLayout(null);
            // Payroll Panel Components

        // Adds individual panels to the main panel
        mainPanel.add(employeeRecordsPanel, "EmployeeRecords");
        mainPanel.add(payrollPanel, "AbstractPayroll");
        mainPanel.add(new JPanel(), "Attendance");
        mainPanel.add(new JPanel(), "Inquiry");
        mainPanel.add(new JPanel(), "Leave Requests");

        // Add action listeners to the buttons
        employeeRecordsBtn.addActionListener(e -> cardLayout.show(mainPanel, "EmployeeRecords"));
        payrollBtn.addActionListener(e -> cardLayout.show(mainPanel, "AbstractPayroll"));
        attendanceBtn.addActionListener(e -> cardLayout.show(mainPanel, "Attendance"));
        inquiryBtn.addActionListener(e -> cardLayout.show(mainPanel, "Inquiry"));
        leavesBtn.addActionListener(e -> cardLayout.show(mainPanel, "Leave Requests"));

        // Logs out of the System
        exitBtn.addActionListener(e -> {
            AdminSystemViewGUI.this.frame.dispose();
            RoleLogin roleLogin = new RoleLogin();
            roleLogin.setVisible(true);
        });

        // Frame Components
        frame.getContentPane().add(menuPanel);
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

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
    private  void fetchData_adminAccounts(DefaultTableModel registered_admin_accounts_table) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchData_empAccounts(DefaultTableModel registered_emp_accounts_table) throws SQLException {
        String dbURL = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
        String sqlSelect = "SELECT emp_id, username, password FROM LoginCredentials";


        try (Connection connection = DriverManager.getConnection(dbURL);
             PreparedStatement pstmnt = connection.prepareStatement(sqlSelect);
             ResultSet rs = pstmnt.executeQuery()) {

            while (rs.next()) {
                int empId = rs.getInt("emp_id");
                String username = rs.getString("username");
                String password = rs.getString("password");

                // Add row to table model
                registered_emp_accounts_table.addRow(new Object[]{empId, username, password});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Fetches data from Employee Table
    private void fetchData(DefaultTableModel emp_table) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

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