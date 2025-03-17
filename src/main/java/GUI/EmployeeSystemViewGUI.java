package GUI;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeSystemViewGUI {
    private static final Logger LOGGER = Logger.getLogger(EmployeeSystemViewGUI.class.getName());
    private final String db_path = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private static JTextField employeeIDtxt;
    private static JDateChooser leave_applicationDateTxt;
    private static JDateChooser leaveStartTxt;
    private static JDateChooser leaveEndTxt;
    private static JTextArea leaveReasonTxt;
    private static JComboBox<String> leaveTypeTxt;
    private static JTextField daysUsedTxt;
    private static JTextField leaveStatusTxt;
    private static JTextField status_updatedByTxt;
    private static JTextField status_updatedDateTxt;
    private static JTextField searchLeaveTxt;

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

        JButton attendanceBtn = new JButton("Attendance");
        attendanceBtn.setFont(latoFont);
        attendanceBtn.setBackground(bluish);
        attendanceBtn.setForeground(fontColor);

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
            reasonLbl.setFont(leaveFontLabel);
            reasonLbl.setForeground(leaveFontColor);
            reasonLbl.setBounds(100,500,200,35);
            leaveReasonTxt.setBounds(100, 545,800,450 );
            leaveReasonTxt.setBorder(BorderFactory.createTitledBorder("Description"));
            leavesPanel.add(reasonLbl);
            leavesPanel.add(leaveReasonTxt);

            JLabel daysUsedLbl = new JLabel("Days Used:");
            daysUsedTxt = new JTextField();
            daysUsedTxt.setEditable(false);
            daysUsedLbl.setFont(leaveFontLabel);
            daysUsedLbl.setForeground(leaveFontColor);
            daysUsedLbl.setBounds(400,90,200,35);
            daysUsedTxt.setBounds(400, 130, 200, 35);
            leavesPanel.add(daysUsedLbl);
            leavesPanel.add(daysUsedTxt);

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
            submitLeaveBtn.setBackground(new Color(2, 134, 18));
            submitLeaveBtn.setForeground(Color.white);
            submitLeaveBtn.setFont(new Font("Lato", Font.PLAIN, 15));
            submitLeaveBtn.setBorderPainted(false);
            leavesPanel.add(submitLeaveBtn);

            searchLeaveTxt = new JTextField();
            searchLeaveTxt.setBounds(1000,90,350,40);
            searchLeaveTxt.setBorder(BorderFactory.createTitledBorder("Search"));
            leavesPanel.add(searchLeaveTxt);


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
            try {
                int result = JOptionPane.showConfirmDialog(frame, "Would you like to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    new LoginGUI();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            };
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

    public void setVisible(boolean b) {
    }
}