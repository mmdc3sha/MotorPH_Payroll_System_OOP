package EmployeeServices;

import GUI.LoginGUI;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeSystemViewGUI extends EmployeeServices {
    protected static final Logger LOGGER = Logger.getLogger(EmployeeSystemViewGUI.class.getName());
    private final String db_path = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
    protected final JPanel mainPanel;
    protected final CardLayout cardLayout;
    protected static JPanel leavesPanel;
    protected static JTextField employeeIDtxt;
    protected static JDateChooser leave_applicationDateTxt;
    protected static JDateChooser leaveStartTxt;
    protected static JDateChooser leaveEndTxt;
    protected static JTextArea leaveReasonTxt;
    protected static JComboBox<String> leaveTypeTxt;
    protected static JTextField leaveStatusTxt;
    protected static JTextField status_updatedByTxt;
    protected static JTextField status_updatedDateTxt;
    protected static DefaultTableModel leaveModel;
    protected static JTable leaveTable;
    protected static JScrollPane leaveScrollPane;

    public EmployeeSystemViewGUI(){
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
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error setting Nimbus Look and Feel", e);
        }

        // Panel for the Left Side
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(2, 37, 101, 255));
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
        adminLabel.setFont(new Font("Lato", Font.BOLD, 25));
        adminLabel.setForeground(Color.WHITE);

        JPanel employeePanel = new JPanel();
        employeePanel.setPreferredSize(new Dimension(300, 100));
        employeePanel.setBackground(new Color(2, 37, 101, 255));
        employeePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        employeePanel.add(empIconLabel);
        employeePanel.add(adminLabel);

        //Buttons Background Color
        Color bluish = new Color(255, 255, 255);
        Color fontColor = new Color(2, 37, 101);
        // Add date and time label
        JLabel dateTimeLabel = new JLabel();
        dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        employeePanel.add(dateTimeLabel);

        // Timer to update the date and time label every second
        Timer timer = new Timer(1000, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
            dateTimeLabel.setText(sdf.format(new Date()));
            dateTimeLabel.setFont(new Font("Lato", Font.BOLD, 18));
            dateTimeLabel.setForeground(Color.WHITE);
        });
        timer.start();

        // Creates new JButtons for the MenuPanel
        Font latoFont = new Font("Lato", Font.BOLD, 14);

        JButton dashboardBtn = new JButton("Dashboard");
        dashboardBtn.setFont(latoFont);
        dashboardBtn.setBackground(bluish);
        dashboardBtn.setForeground(fontColor);

        JButton inquiryBtn = new JButton("Create Inquiry");
        inquiryBtn.setFont(latoFont);
        inquiryBtn.setBackground(bluish);
        inquiryBtn.setForeground(fontColor);

        JButton leavesBtn = new JButton("Leave Requests");
        leavesBtn.setFont(latoFont);
        leavesBtn.setBackground(bluish);
        leavesBtn.setForeground(fontColor);



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
        menuPanel.add(dashboardBtn, gbc);
        gbc.gridy = 2;
        menuPanel.add(leavesBtn, gbc);
        gbc.gridy = 3;
        menuPanel.add(exitBtn, gbc);

        //Create the main panel with CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.setBounds(300, 0, 1620, 1080);

        JPanel dashboardPanel = new JPanel();
        dashboardPanel.add(new JLabel("Inquiry View"));
        dashboardPanel.setLayout(new BorderLayout());

        leavesPanel = new JPanel();
        leavesPanel.add(new JLabel("Leave Requests View"));
        leavesPanel.setLayout(null);
        leavesPanel.setBackground(Color.WHITE);

            JPanel titlePanel = new JPanel();
            titlePanel.setLayout(null);
            titlePanel.setBackground(new Color(3, 49, 137, 255));
            titlePanel.setBounds(0, 0,1800 , 80);
            leavesPanel.add(titlePanel);

            //Create the ImageIcon, title and Add the Title and Icon to the panel
            ImageIcon leaveIcon = new ImageIcon("src/main/resources/leave_icon.png");
            JLabel title = new JLabel("  LEAVE APPLICATION", leaveIcon, JLabel.LEFT);
            title.setFont(new Font("Lato", Font.BOLD, 30));
            title.setForeground(Color.WHITE);
            title.setBounds(70,20,500,40);
            titlePanel.add(title);

            // Font for the Label in Leave Requests Panel
            Font leaveFontLabel = new Font("Lato", Font.BOLD, 14);
            Color leaveFontColor = new Color(1, 23, 71);

            JLabel employeeIDLbl = new JLabel("Employee ID");
            employeeIDLbl.setFont(leaveFontLabel);
            employeeIDLbl.setForeground(leaveFontColor);
            employeeIDLbl.setBounds(100,90,150,35);
            employeeIDtxt = new JTextField();
            employeeIDtxt.setBounds(100, 130, 200, 35);
            leavesPanel.add(employeeIDLbl);
            leavesPanel.add(employeeIDtxt);

            JLabel dateApplicationlbl = new JLabel("Date of Application:");
            leave_applicationDateTxt = new JDateChooser();
            dateApplicationlbl.setFont(leaveFontLabel);
            dateApplicationlbl.setForeground(leaveFontColor);
            dateApplicationlbl.setBounds(100,175,200,35);
            leave_applicationDateTxt.setBounds(100,210,200,35);
            leavesPanel.add(dateApplicationlbl);
            leavesPanel.add(leave_applicationDateTxt);

            JLabel leaveStartLbl = new JLabel("Start of Leave:");
            leaveStartTxt = new JDateChooser();
            leaveStartLbl.setFont(leaveFontLabel);
            leaveStartLbl.setForeground(leaveFontColor);
            leaveStartLbl.setBounds(100,245,200,35);
            leaveStartTxt.setBounds(100, 280,200,35);
            leavesPanel.add(leaveStartLbl);
            leavesPanel.add(leaveStartTxt);

            JLabel leaveEndLbl = new JLabel("End of Leave:");
            leaveEndTxt = new JDateChooser();
            leaveEndLbl.setFont(leaveFontLabel);
            leaveEndLbl.setForeground(leaveFontColor);
            leaveEndLbl.setBounds(100,330,200,35);
            leaveEndTxt.setBounds(100, 365,200,35);
            leavesPanel.add(leaveEndLbl);
            leavesPanel.add(leaveEndTxt);

            JLabel leaveTypeLbl = new JLabel("Leave Type:");
            leaveTypeLbl.setFont(leaveFontLabel);
            leaveTypeLbl.setForeground(leaveFontColor);
            leaveTypeTxt = new JComboBox<>();
            leaveTypeTxt.setModel(new DefaultComboBoxModel<>(new String[] {"Vacation Leave",
                    "Sick Leave",
                    "Maternity Leave",
                    "Paternity Leave",
                    "Bereavement Leave",
                    "Vacation Leave (VL)",
                    "Sick Leave (SL)",
                    "Emergency Leave (EL)",
                    "Study Leave (ST)",
                    "Special Leave (ML)",
                    "Unpaid Leave (PL)",}));
            leaveTypeLbl.setBounds(100,405,200,35);
            leaveTypeTxt.setBounds(100, 445,200,35);
            leavesPanel.add(leaveTypeLbl);
            leavesPanel.add(leaveTypeTxt);

            JLabel reasonLbl = new JLabel("Reason for Leave:");
            leaveReasonTxt = new JTextArea();
            leaveReasonTxt.setFont(new Font("Lato", Font.PLAIN, 18));
            reasonLbl.setFont(leaveFontLabel);
            reasonLbl.setForeground(leaveFontColor);
            reasonLbl.setBounds(100,500,200,35);
            leaveReasonTxt.setBounds(100, 545,800,450 );
            leaveReasonTxt.setBorder(BorderFactory.createTitledBorder("Description"));
            leavesPanel.add(reasonLbl);
            leavesPanel.add(leaveReasonTxt);

            JLabel leaveStatusLbl = new JLabel("Leave Status:");
            leaveStatusTxt = new JTextField();
            leaveStatusLbl.setFont(leaveFontLabel);
            leaveStatusLbl.setForeground(leaveFontColor);
            leaveStatusLbl.setBounds(400,175,200,35);
            leaveStatusTxt.setBounds(400, 210,200,35);
            leaveStatusTxt.setEditable(false);
            leavesPanel.add(leaveStatusLbl);
            leavesPanel.add(leaveStatusTxt);

            JLabel statusUpdateLbl = new JLabel("Status Update By:");
            status_updatedByTxt = new JTextField();
            statusUpdateLbl.setFont(leaveFontLabel);
            statusUpdateLbl.setForeground(leaveFontColor);
            statusUpdateLbl.setBounds(400,245,200,35);
            status_updatedByTxt.setBounds(400,280,200,35);
            status_updatedByTxt.setEditable(false);
            leavesPanel.add(statusUpdateLbl);
            leavesPanel.add(status_updatedByTxt);

            JLabel statusUpdateDateLbl = new JLabel("Status Update at:");
            status_updatedDateTxt = new JTextField();
            statusUpdateDateLbl.setFont(leaveFontLabel);
            statusUpdateDateLbl.setForeground(leaveFontColor);
            statusUpdateDateLbl.setBounds(400,330,200,35);
            status_updatedDateTxt.setBounds(400, 365,200,35);
            status_updatedDateTxt.setEditable(false);
            leavesPanel.add(statusUpdateDateLbl);
            leavesPanel.add(status_updatedDateTxt);

            JButton submitLeaveBtn = new JButton("Submit");
            submitLeaveBtn.setBounds(400, 430,200,50);
            submitLeaveBtn.setBackground(new Color(2, 37, 101));
            submitLeaveBtn.setForeground(Color.white);
            submitLeaveBtn.setFont(new Font("Lato", Font.PLAIN, 15));
            submitLeaveBtn.setBorderPainted(false);
            leavesPanel.add(submitLeaveBtn);

            submitLeaveBtn.addActionListener(e -> {
                submitLeaveApplications();
            });

            JButton loadRequestBtn = new JButton("Load Request");
            loadRequestBtn.setBounds(650,150,250,50);
            loadRequestBtn.setBackground(new Color(1, 147, 110));
            loadRequestBtn.setForeground(Color.white);
            loadRequestBtn.setFont(new Font("Lato", Font.PLAIN, 15));
            loadRequestBtn.setBorderPainted(false);
            leavesPanel.add(loadRequestBtn);
            loadRequestBtn.addActionListener(e -> {
                loadLeaveApplications();
            });

            JButton cancelLeaveBtn = new JButton("Cancel Leave");
            cancelLeaveBtn.setBounds(650,210,250,50);
            cancelLeaveBtn.setFont(new Font("Lato", Font.PLAIN, 15));
            cancelLeaveBtn.setBackground(new Color(156, 6, 6));
            cancelLeaveBtn.setForeground(Color.white);
            leavesPanel.add(cancelLeaveBtn);

            //Cancels a Leave Application
            cancelLeaveBtn.addActionListener(e -> {
                cancelLeaveApplications(leaveTable); // Cancel Leave Application method called from EmployeeDatabaseOperation
            });

            JTextArea remarksTxt = new JTextArea();
            remarksTxt.setEditable(false);
            remarksTxt.setBackground(new Color(226, 226, 226));
            remarksTxt.setBounds(1000,600, 540, 300);
        remarksTxt.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(2, 37, 101), 3), // Outer border with 3-pixel thickness
                new TitledBorder(null, "Remarks", TitledBorder.LEFT,
                        TitledBorder.TOP, new Font("Lato", Font.BOLD, 25))
        ));


            // Column names for the Leave Table
            leaveModel = new DefaultTableModel();

            // Creates "Leave Application" table
            leaveTable = new JTable(leaveModel);

            // Sets Selection of rows to single selection
            leaveTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


            leaveScrollPane = new JScrollPane(leaveTable);
            leaveScrollPane.setBounds(1000,175,540,400);
            leaveScrollPane.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(2, 37, 101, 3)),
                            new TitledBorder(null, "Leave Applications", TitledBorder.LEFT, TitledBorder.TOP, new Font("Lato", Font.BOLD, 25))
            ));

            leavesPanel.add(leaveScrollPane);
            leavesPanel.add(remarksTxt);


        // Add individual panels to the main panel
        mainPanel.add(dashboardPanel, "Dashboard");
        mainPanel.add(leavesPanel, "Leave Requests");

        // Add action listeners to the buttons
        dashboardBtn.addActionListener(e -> {cardLayout.show(mainPanel, "Dashboard");});
        leavesBtn.addActionListener(e -> cardLayout.show(mainPanel, "Leave Requests"));

        // Logs out of the System
        exitBtn.addActionListener(e -> {
            try {
                int result = JOptionPane.showConfirmDialog(frame, "Would you like to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    new LoginGUI();
                }
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });

        // Frame Components
        frame.getContentPane().add(menuPanel);
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    private void submitLeaveApplications() {
        try {
            // Retrieve the logged-in Employee ID from LoginSessionManager
            int empID = LoginSessionManager.getInstance().getLoggedInEmpID();

            // Validate empID (ensure it's not zero or negative)
            if (empID <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid Employee ID. Please log in again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String type = Objects.requireNonNull(leaveTypeTxt.getSelectedItem()).toString();
            String startDate = leaveStartTxt.getDate().toString();
            String endDate = leaveEndTxt.getDate().toString();
            String status = "Pending";

            // Insert leave application into the database
            insertLeaveApplication(empID, type, startDate, endDate, status);

            JOptionPane.showMessageDialog(null, "Your Leave application has been submitted.", "Application Submitted", JOptionPane.INFORMATION_MESSAGE);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Please select all required fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error submitting leave application", ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Submit Request Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to Load leave applications of the Logged-in user
    @Override
    public void loadLeaveApplications(){
        try {
            // Get logged-in Employee ID from LoginSessionManager
            int empID = LoginSessionManager.getInstance().getLoggedInEmpID();

            // Validate empID (ensure it's not zero or negative)
            if (empID <= 0) {
                JOptionPane.showMessageDialog(null, "Error: Invalid session. Please log in again.", "Session Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Load leave applications for the logged-in employee only
            leaveModel = getLeaveTable(empID);
            leaveTable.setModel(leaveModel);

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error loading leave applications", ex);
            JOptionPane.showMessageDialog(null, "Failed to load leave applications. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static JTextField getEmployeeIDtxt() {
        return employeeIDtxt;
    }

    public static JDateChooser getLeave_applicationDateTxt() {
        return leave_applicationDateTxt;
    }

    public static JDateChooser getLeaveStartTxt() {
        return leaveStartTxt;
    }

    public static JDateChooser getLeaveEndTxt() {
        return leaveEndTxt;
    }

    public static JTextArea getLeaveReasonTxt() {
        return leaveReasonTxt;
    }

    public static JComboBox<String> getLeaveTypeTxt() {
        return leaveTypeTxt;
    }

    public static JTextField getLeaveStatusTxt() {
        return leaveStatusTxt;
    }

    public static JTextField getStatus_updatedByTxt() {
        return status_updatedByTxt;
    }

    public static JTextField getStatus_updatedDateTxt() {
        return status_updatedDateTxt;
    }

    public static DefaultTableModel getLeaveModel() {
        return leaveModel;
    }

    public static JTable getLeaveTable() {
        return leaveTable;
    }

    public static JScrollPane getLeaveScrollPane() {
        return leaveScrollPane;
    }

    public static void main(String[] args) {
        // Create an instance of MainSystem to display the frame
        SwingUtilities.invokeLater(EmployeeSystemViewGUI::new);
    }

    /**
     * @param leave_table
     * @param selectedRow
     */
    @Override
    protected void handleRowSelection(JTable leave_table, int selectedRow) {

    }
}