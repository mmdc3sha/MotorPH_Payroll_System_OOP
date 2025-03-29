package GUI;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddEmployeeGUI extends JDialog {
    private final JTextField txtFirstName;
    private final JTextField txtLastName;
    private final JTextField txtBirthday;
    private final JTextField txtAddress;
    private final JTextField txtPhoneNumber;
    private final JComboBox<String> cbEmploymentStatus;
    private final JTextField txtJobPosition;
    private final JTextField txtImmediateSupervisor;
    private final JTextField txtSSSNumber;
    private final JTextField txtPhilhealthNumber;
    private final JTextField txtTINNumber;
    private final JTextField txtPagIbigNumber;
    private final JTextField txtBasicSalary;
    private final JTextField txtHourlyRate;
    private final JTextField txtRiceSubsidy;
    private final JTextField txtPhoneAllowance;
    private final JTextField txtClothingAllowance;
    private final JTextField txtGrossSemiMonthlyRate;

    public AddEmployeeGUI(JFrame AdminSystemViewGUI) {
        final Logger LOGGER = Logger.getLogger(AddEmployeeGUI.class.getName());
        super(AdminSystemViewGUI, "Add Employee", true);
        setTitle("Add Employee");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(AdminSystemViewGUI);
        Image appIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/user.png"))).getImage();
        setIconImage(appIcon);

        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error setting Look and Feel", e);
        }

        //Panel for the labels and textfield
        JPanel panel = new JPanel();
        panel.setBackground(new Color(100, 184, 255, 255)); // Inner panel background
        panel.setLayout(new GridBagLayout());
        getContentPane().add(panel);

        Font labelFont = new Font("Lato", Font.PLAIN, 14);
        Font textfieldFont = new Font("Lato", Font.PLAIN, 12);

        // Labels and fields for personal information (Left Column)
        JLabel firstName = new JLabel("First Name:");
        firstName.setFont(labelFont);
        JLabel lastName = new JLabel("Last Name:");
        lastName.setFont(labelFont);
        JLabel address = new JLabel("Address:");
        address.setFont(labelFont);
        JLabel phoneNumber = new JLabel("Phone Number:");
        phoneNumber.setFont(labelFont);
        JLabel employmentStatus = new JLabel("Employment Status:");
        employmentStatus.setFont(labelFont);
        JLabel jobPosition = new JLabel("Job Position:");
        jobPosition.setFont(labelFont);
        JLabel immediateSupervisor = new JLabel("Immediate Supervisor:");
        immediateSupervisor.setFont(labelFont);

        txtFirstName = new JTextField(20);
        txtFirstName.setFont(textfieldFont);
        txtLastName = new JTextField(20);
        txtLastName.setFont(textfieldFont);
        txtBirthday = new JTextField(20);
        txtBirthday.setFont(textfieldFont);
        txtAddress = new JTextField(20);
        txtAddress.setFont(textfieldFont);
        txtAddress.setPreferredSize(new Dimension(400, 40));
        txtPhoneNumber = new JTextField(20);
        txtPhoneNumber.setFont(textfieldFont);
        cbEmploymentStatus = new JComboBox<>(new String[]{"Regular", "Part-time", "Intern", "Probationary"});
        cbEmploymentStatus.setFont(textfieldFont);
        txtJobPosition = new JTextField(20);
        txtJobPosition.setFont(textfieldFont);
        txtImmediateSupervisor = new JTextField(20);
        txtImmediateSupervisor.setFont(textfieldFont);
        // Labels and fields for security numbers and salary information (Right Column)
        JLabel lblSSSNumber = new JLabel("SSS Number:");
        lblSSSNumber.setFont(labelFont);
        JLabel lblPhilhealthNumber = new JLabel("Philhealth Number:");
        lblPhilhealthNumber.setFont(labelFont);
        JLabel lblTINNumber = new JLabel("TIN Number:");
        lblTINNumber.setFont(labelFont);
        JLabel lblPagIbigNumber = new JLabel("PagIbig Number:");
        lblPagIbigNumber.setFont(labelFont);
        JLabel lblBasicSalary = new JLabel("Basic Salary:");
        lblBasicSalary.setFont(labelFont);
        JLabel lblHourlyRate = new JLabel("Hourly Rate:");
        lblHourlyRate.setFont(labelFont);
        JLabel lblRiceSubsidy = new JLabel("Rice Subsidy:");
        lblRiceSubsidy.setFont(labelFont);
        JLabel lblPhoneAllowance = new JLabel("Phone Allowance:");
        lblPhoneAllowance.setFont(labelFont);
        JLabel lblClothingAllowance = new JLabel("Clothing Allowance:");
        lblClothingAllowance.setFont(labelFont);
        JLabel lblGrossSemiMonthlyRate = new JLabel("Gross Semi Monthly Rate:");
        lblGrossSemiMonthlyRate.setFont(labelFont);

        txtSSSNumber = new JTextField(20);
        txtSSSNumber.setFont(textfieldFont);
        txtPhilhealthNumber = new JTextField(20);
        txtPhilhealthNumber.setFont(textfieldFont);
        txtTINNumber = new JTextField(20);
        txtTINNumber.setFont(textfieldFont);
        txtPagIbigNumber = new JTextField(20);
        txtPagIbigNumber.setFont(textfieldFont);
        txtBasicSalary = new JTextField(20);
        txtBasicSalary.setFont(textfieldFont);
        txtHourlyRate = new JTextField(20);
        txtHourlyRate.setFont(textfieldFont);
        txtRiceSubsidy = new JTextField(20);
        txtRiceSubsidy.setFont(textfieldFont);
        txtPhoneAllowance = new JTextField(20);
        txtPhoneAllowance.setFont(textfieldFont);
        txtClothingAllowance = new JTextField(20);
        txtClothingAllowance.setFont(textfieldFont);
        txtGrossSemiMonthlyRate = new JTextField(20);
        txtGrossSemiMonthlyRate.setFont(textfieldFont);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 50, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Personal Information (Left Column)
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(firstName, gbc);
        gbc.gridx = 1;
        panel.add(txtFirstName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lastName, gbc);
        gbc.gridx = 1;
        panel.add(txtLastName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(address, gbc);
        gbc.gridx = 1;
        panel.add(txtAddress, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(phoneNumber, gbc);
        gbc.gridx = 1;
        panel.add(txtPhoneNumber, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(employmentStatus, gbc);
        gbc.gridx = 1;
        panel.add(cbEmploymentStatus, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(jobPosition, gbc);
        gbc.gridx = 1;
        panel.add(txtJobPosition, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(immediateSupervisor, gbc);
        gbc.gridx = 1;
        panel.add(txtImmediateSupervisor, gbc);

        // Security Numbers and Salary Information (Right Column)
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(lblSSSNumber, gbc);
        gbc.gridx = 3;
        panel.add(txtSSSNumber, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(lblPhilhealthNumber, gbc);
        gbc.gridx = 3;
        panel.add(txtPhilhealthNumber, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(lblTINNumber, gbc);
        gbc.gridx = 3;
        panel.add(txtTINNumber, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(lblPagIbigNumber, gbc);
        gbc.gridx = 3;
        panel.add(txtPagIbigNumber, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(lblBasicSalary, gbc);
        gbc.gridx = 3;
        panel.add(txtBasicSalary, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        panel.add(lblHourlyRate, gbc);
        gbc.gridx = 3;
        panel.add(txtHourlyRate, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        panel.add(lblRiceSubsidy, gbc);
        gbc.gridx = 3;
        panel.add(txtRiceSubsidy, gbc);

        gbc.gridx = 2;
        gbc.gridy = 7;
        panel.add(lblPhoneAllowance, gbc);
        gbc.gridx = 3;
        panel.add(txtPhoneAllowance, gbc);

        gbc.gridx = 2;
        gbc.gridy = 8;
        panel.add(lblClothingAllowance, gbc);
        gbc.gridx = 3;
        panel.add(txtClothingAllowance, gbc);

        gbc.gridx = 2;
        gbc.gridy = 9;
        panel.add(lblGrossSemiMonthlyRate, gbc);
        gbc.gridx = 3;
        panel.add(txtGrossSemiMonthlyRate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 5;
        panel.add(Box.createRigidArea(new Dimension(20, 40)), gbc);

        // Button Panel
        JButton saveButton;

        // Panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(100, 184, 255, 255));
        buttonPanel.setLayout(new FlowLayout());

        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(0, 176, 24));
        saveButton.setFont(new Font("Lato", Font.BOLD, 14));

        // Sets the Preferred Size of the buttons
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Lato", Font.BOLD, 14));
        Dimension buttonSize = new Dimension(100, 40);


        cancelButton.setBackground(new Color(255, 114, 114));
        saveButton.setPreferredSize(buttonSize);
        cancelButton.setPreferredSize(buttonSize);

        //Add components to the button panel
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        // When save button is pressed, it will save the employee data
        saveButton.addActionListener(e -> {
                // Save data
                try {
                    saveEmployee();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
        });

        // When cancel button is pressed, it will close the AddEmployeeGUI
        cancelButton.addActionListener(e -> {
            dispose();
            JOptionPane.showMessageDialog(null, "Employee not Added.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void saveEmployee() throws SQLException {
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String birthday = txtBirthday.getText().trim();
        String address = txtAddress.getText().trim();
        String phoneNumber = txtPhoneNumber.getText().trim();
        String employmentStatus = (String) cbEmploymentStatus.getSelectedItem();
        String jobPosition = txtJobPosition.getText().trim();
        String immediateSupervisor = txtImmediateSupervisor.getText().trim();
        String sssNumber = txtSSSNumber.getText().trim();
        String philhealthNumber = txtPhilhealthNumber.getText().trim();
        String tinNumber = txtTINNumber.getText().trim();
        String pagIbigNumber = txtPagIbigNumber.getText().trim();
        String basicSalary = txtBasicSalary.getText().trim();
        String hourlyRate = txtHourlyRate.getText().trim();
        String riceSubsidy = txtRiceSubsidy.getText().trim();
        String phoneAllowance = txtPhoneAllowance.getText().trim();
        String clothingAllowance = txtClothingAllowance.getText().trim();
        String grossSemiMonthlyRate = txtGrossSemiMonthlyRate.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || birthday.isEmpty() || address.isEmpty() ||
                phoneNumber.isEmpty() || employmentStatus == null || jobPosition.isEmpty() ||
                immediateSupervisor.isEmpty() || sssNumber.isEmpty() || philhealthNumber.isEmpty() ||
                tinNumber.isEmpty() || pagIbigNumber.isEmpty() || basicSalary.isEmpty() ||
                hourlyRate.isEmpty() || riceSubsidy.isEmpty() || phoneAllowance.isEmpty() ||
                clothingAllowance.isEmpty() || grossSemiMonthlyRate.isEmpty()) {

            JOptionPane.showMessageDialog(this, "All fields must be filled out!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;  // Stop execution if any field is empty
        }

        String url = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
        String sql = "INSERT INTO Employee(employee_id, last_name, first_name, birthday, address, phone_number, SSS_number, philhealth_number, TIN_number, Pagibig_number, employment_status, job_position, Immediate_Supervisor," +
                "basic_salary, hourly_rate, rice_subsidy, phone_allowance, clothing_allowance, gross_semi_monthly_rate) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmnt = conn.prepareStatement(sql)
        ) {
            pstmnt.setString(2, lastName);
            pstmnt.setString(3, firstName);
            pstmnt.setString(4, birthday);
            pstmnt.setString(5, address);
            pstmnt.setString(6, phoneNumber);
            pstmnt.setString(7, sssNumber);
            pstmnt.setString(8, philhealthNumber);
            pstmnt.setString(9, tinNumber);
            pstmnt.setString(10, pagIbigNumber);
            pstmnt.setString(11, employmentStatus);
            pstmnt.setString(12, jobPosition);
            pstmnt.setString(13, immediateSupervisor);
            pstmnt.setString(14, basicSalary);
            pstmnt.setString(15, hourlyRate);
            pstmnt.setString(16, riceSubsidy);
            pstmnt.setString(17, phoneAllowance);
            pstmnt.setString(18, clothingAllowance);
            pstmnt.setString(19, grossSemiMonthlyRate);
            pstmnt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Employee Added Successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error in Adding Employee." + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddEmployeeGUI(new JFrame()).setVisible(true));
    }
}