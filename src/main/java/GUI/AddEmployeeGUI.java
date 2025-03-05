package GUI;

import javax.swing.*;
import java.awt.*;


public class AddEmployeeGUI extends JDialog {
    public AddEmployeeGUI(JFrame AdminSystemViewGUI) {
        super(AdminSystemViewGUI, "Add Employee", true);
        setTitle("Add Employee");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(950, 650);
        setLocationRelativeTo(AdminSystemViewGUI);
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

        JTextField txtEmployeeID = new JTextField(20);
        JTextField txtFirstName = new JTextField(20);
        JTextField txtLastName = new JTextField(20);
        JTextField txtAddress = new JTextField(20);
        JTextField txtPhoneNumber = new JTextField(20);
        JComboBox<String> cbEmploymentStatus = new JComboBox<>(new String[] {"Regular", "Part-time", "Intern"});
        JTextField txtJobPosition = new JTextField(20);
        JTextField txtImmediateSupervisor = new JTextField(20);

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

        JTextField txtSSSNumber = new JTextField(20);
        JTextField txtPhilhealthNumber = new JTextField(20);
        JTextField txtTINNumber = new JTextField(20);
        JTextField txtPagIbigNumber = new JTextField(20);
        JTextField txtBasicSalary = new JTextField(20);
        JTextField txtHourlyRate = new JTextField(20);
        JTextField txtRiceSubsidy = new JTextField(20);
        JTextField txtPhoneAllowance = new JTextField(20);
        JTextField txtClothingAllowance = new JTextField(20);
        JTextField txtGrossSemiMonthlyRate = new JTextField(20);

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
        JButton saveButton = new JButton("Save");
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

        // When cancel button has been pressed, it will automatically close the AddEmployeeGUI
        saveButton.addActionListener(e -> {

            dispose();
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddEmployeeGUI(new JFrame()).setVisible(true));
    }
}