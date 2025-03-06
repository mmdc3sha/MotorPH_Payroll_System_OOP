package GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddEmployeeGUI extends JDialog {
    private final JTextField txtEmployeeID;
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
    private final JButton saveButton;

    public AddEmployeeGUI(JFrame AdminSystemViewGUI) {
        super(AdminSystemViewGUI, "Add Employee", true);
        setTitle("Add Employee");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(950, 650);
        setLocationRelativeTo(AdminSystemViewGUI);
        Image appIcon = new ImageIcon(getClass().getResource("/user.png")).getImage();
        setIconImage(appIcon);
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        getContentPane().add(panel);

        Font labelFont = new Font("Lato", Font.PLAIN, 14);
        Font textfieldFont = new Font("Monospaced", Font.PLAIN, 14);

        // Labels and fields for personal information (Left Column)
        JLabel lblEmployeeID = new JLabel("Employee ID:");
        lblEmployeeID.setFont(labelFont);
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

        txtEmployeeID = new JTextField(20);
        txtFirstName = new JTextField(20);
        txtLastName = new JTextField(20);
        txtBirthday = new JTextField(20);
        txtAddress = new JTextField(20);
        txtPhoneNumber = new JTextField(20);
        cbEmploymentStatus = new JComboBox<>(new String[]{"Regular", "Part-time", "Intern"});
        txtJobPosition = new JTextField(20);
        txtImmediateSupervisor = new JTextField(20);

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
        txtPhilhealthNumber = new JTextField(20);
        txtTINNumber = new JTextField(20);
        txtPagIbigNumber = new JTextField(20);
        txtBasicSalary = new JTextField(20);
        txtHourlyRate = new JTextField(20);
        txtRiceSubsidy = new JTextField(20);
        txtPhoneAllowance = new JTextField(20);
        txtClothingAllowance = new JTextField(20);
        txtGrossSemiMonthlyRate = new JTextField(20);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Personal Information (Left Column)
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblEmployeeID, gbc);
        gbc.gridx = 1;
        panel.add(txtEmployeeID, gbc);

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
        panel.add(Box.createRigidArea(new Dimension(0, 40)), gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        Dimension buttonSize = new Dimension(100, 40);
        saveButton.setPreferredSize(buttonSize);
        cancelButton.setPreferredSize(buttonSize);
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        // Disable save button initially
        saveButton.setEnabled(false);

        // Add document listeners to text fields
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkFields();
            }
        };

        txtEmployeeID.getDocument().addDocumentListener(documentListener);
        txtFirstName.getDocument().addDocumentListener(documentListener);
        txtLastName.getDocument().addDocumentListener(documentListener);
        txtBirthday.getDocument().addDocumentListener(documentListener);
        txtAddress.getDocument().addDocumentListener(documentListener);
        txtPhoneNumber.getDocument().addDocumentListener(documentListener);
        txtJobPosition.getDocument().addDocumentListener(documentListener);
        txtImmediateSupervisor.getDocument().addDocumentListener(documentListener);
        txtSSSNumber.getDocument().addDocumentListener(documentListener);
        txtPhilhealthNumber.getDocument().addDocumentListener(documentListener);
        txtTINNumber.getDocument().addDocumentListener(documentListener);
        txtPagIbigNumber.getDocument().addDocumentListener(documentListener);
        txtBasicSalary.getDocument().addDocumentListener(documentListener);
        txtHourlyRate.getDocument().addDocumentListener(documentListener);
        txtRiceSubsidy.getDocument().addDocumentListener(documentListener);
        txtPhoneAllowance.getDocument().addDocumentListener(documentListener);
        txtClothingAllowance.getDocument().addDocumentListener(documentListener);
        txtGrossSemiMonthlyRate.getDocument().addDocumentListener(documentListener);

        // When save button is pressed, it will save the employee data
        saveButton.addActionListener(e -> {
            try {
                saveEmployee();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } finally {
                dispose();
            }
        });

        // When cancel button is pressed, it will close the AddEmployeeGUI
        cancelButton.addActionListener(e -> {
            dispose();
            JOptionPane.showConfirmDialog(null, "Add Employee Cancelled.", "Cancel", JOptionPane.OK_CANCEL_OPTION);
        });
    }

    private void checkFields() {
        boolean allFieldsFilled =
                !txtEmployeeID.getText().isEmpty() &&
                        !txtFirstName.getText().isEmpty() &&
                        !txtLastName.getText().isEmpty() &&
                        !txtBirthday.getText().isEmpty() &&
                        !txtAddress.getText().isEmpty() &&
                        !txtPhoneNumber.getText().isEmpty() &&
                        !txtJobPosition.getText().isEmpty() &&
                        !txtImmediateSupervisor.getText().isEmpty() &&
                        !txtSSSNumber.getText().isEmpty() &&
                        !txtPhilhealthNumber.getText().isEmpty() &&
                        !txtTINNumber.getText().isEmpty() &&
                        !txtPagIbigNumber.getText().isEmpty() &&
                        !txtBasicSalary.getText().isEmpty() &&
                        !txtHourlyRate.getText().isEmpty() &&
                        !txtRiceSubsidy.getText().isEmpty() &&
                        !txtPhoneAllowance.getText().isEmpty() &&
                        !txtClothingAllowance.getText().isEmpty() &&
                        !txtGrossSemiMonthlyRate.getText().isEmpty();

        saveButton.setEnabled(allFieldsFilled);
    }

    private void saveEmployee() throws SQLException {
        String employeeID = txtEmployeeID.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String birthday = txtBirthday.getText();
        String address = txtAddress.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String employmentStatus = (String) cbEmploymentStatus.getSelectedItem();
        String jobPosition = txtJobPosition.getText();
        String immediateSupervisor = txtImmediateSupervisor.getText();
        String sssNumber = txtSSSNumber.getText();
        String philhealthNumber = txtPhilhealthNumber.getText();
        String tinNumber = txtTINNumber.getText();
        String pagIbigNumber = txtPagIbigNumber.getText();
        String basicSalary = txtBasicSalary.getText();
        String hourlyRate = txtHourlyRate.getText();
        String riceSubsidy = txtRiceSubsidy.getText();
        String phoneAllowance = txtPhoneAllowance.getText();
        String clothingAllowance = txtClothingAllowance.getText();
        String grossSemiMonthlyRate = txtGrossSemiMonthlyRate.getText();

        String url = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
        String sql = "INSERT INTO Employee(employee_id, last_name, first_name, birthday, address, phone_number, SSS_number, philhealth_number, TIN_number, Pagibig_number, employment_status, job_position, Immediate_Supervisor," +
                "basic_salary, hourly_rate, rice_subsidy, phone_allowance, clothing_allowance, gross_semi_monthly_rate) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmnt = conn.prepareStatement(sql)
        ) {
            pstmnt.setString(1, employeeID);
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
            JOptionPane.showMessageDialog(this, "Error in Adding Employee. Connection to Database Failed." + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddEmployeeGUI(new JFrame()).setVisible(true));
    }
}