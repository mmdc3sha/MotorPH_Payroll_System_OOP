package AdminView;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PayrollServices implements PayrollServiceInterface {
    private static final Logger LOGGER = Logger.getLogger(PayrollServices.class.getName());
    private static final String DB_URL = "jdbc:sqlite:src/main/java/MotorPHDatabase.db"; // Externalize Database Path

    private JDateChooser dateChooser;
    private JDateChooser dateChooser2;
    private JTextField empIDTextField;
    private JTextField fNameTextField;
    private JTextField lastNameTextField;
    private JTextField positionTextField;
    private JTextField daysWorkedTextField;
    private JTextField hoursWorkedTextField;
    private JTextField overtimeTextField;
    private JTextField basicSalaryTextField;
    private JTextField hrlyRateTextField;
    private JTextField grossIncomeField;
    private JTextField riceSubsidyTextField;
    private JTextField phoneAllowanceTextField;
    private JTextField clothAllowanceTextField;
    private JTextField sssTextField;
    private JTextField philHealthTextField;
    private JTextField pagIBIGTextField;
    private JTextField withholdingTextField;
    private JTextField totalBenefitsTextField;
    private JTextField totalDeductionsTextField;
    private JTextField netIncomeTextField;

    public PayrollServices(JDateChooser dateChooser,
                           JDateChooser dateChooser2,
                           JTextField empIDTextField,
                           JTextField fNameTextField,
                           JTextField lastNameTextField,
                           JTextField positionTextField,
                           JTextField daysWorkedTextField,
                           JTextField hoursWorkedTextField,
                           JTextField overtimeTextField,
                           JTextField basicSalaryTextField,
                           JTextField hrlyRateTextField,
                           JTextField grossIncomeField,
                           JTextField riceSubsidyTextField,
                           JTextField phoneAllowanceTextField,
                           JTextField clothAllowanceTextField,
                           JTextField sssTextField,
                           JTextField philHealthTextField,
                           JTextField pagIBIGTextField,
                           JTextField withholdingTextField,
                           JTextField totalBenefitsTextField,
                           JTextField totalDeductionsTextField,
                           JTextField netIncomeTextField) {

        this.dateChooser = dateChooser;
        this.dateChooser2 = dateChooser2;
        this.empIDTextField = empIDTextField;
        this.fNameTextField = fNameTextField;
        this.lastNameTextField = lastNameTextField;
        this.positionTextField = positionTextField;
        this.daysWorkedTextField = daysWorkedTextField;
        this.hoursWorkedTextField = hoursWorkedTextField;
        this.overtimeTextField = overtimeTextField;
        this.basicSalaryTextField = basicSalaryTextField;
        this.hrlyRateTextField = hrlyRateTextField;
        this.grossIncomeField = grossIncomeField;
        this.riceSubsidyTextField = riceSubsidyTextField;
        this.phoneAllowanceTextField = phoneAllowanceTextField;
        this.clothAllowanceTextField = clothAllowanceTextField;
        this.sssTextField = sssTextField;
        this.philHealthTextField = philHealthTextField;
        this.pagIBIGTextField = pagIBIGTextField;
        this.withholdingTextField = withholdingTextField;
        this.totalBenefitsTextField = totalBenefitsTextField;
        this.totalDeductionsTextField = totalDeductionsTextField;
        this.netIncomeTextField = netIncomeTextField;

    }


    // Calculates the Payroll
    @Override
    public void calculatePayroll() throws SQLException {
        try {
            String startDate = dateChooser.getDate().toString();
            String endDate = dateChooser2.getDate().toString();
            String empID = empIDTextField.getText();
            String fName = fNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String position = positionTextField.getText();
            int daysWorked = Integer.parseInt(daysWorkedTextField.getText());
            int hoursWorked = Integer.parseInt(hoursWorkedTextField.getText());
            int overtime = Integer.parseInt(overtimeTextField.getText());
            double basicSalary = Double.parseDouble(basicSalaryTextField.getText());
            double hourlyRate = Double.parseDouble(hrlyRateTextField.getText());
            double riceSubsidy = Double.parseDouble(riceSubsidyTextField.getText());
            double phoneAllowance = Double.parseDouble(phoneAllowanceTextField.getText());
            double clothAllowance = Double.parseDouble(clothAllowanceTextField.getText());
            double sss = Double.parseDouble(sssTextField.getText());
            double philHealth = Double.parseDouble(philHealthTextField.getText());
            double pagIBIG = Double.parseDouble(pagIBIGTextField.getText());

            // ✅ Step 1: Compute Gross Income
            double grossIncome = basicSalary + (hourlyRate * overtime);

            // ✅ Step 2: Compute Total Benefits and Deductions
            double totalBenefits = riceSubsidy + phoneAllowance + clothAllowance;
            double totalDeductions = sss + philHealth + pagIBIG;

            // ✅ Step 3: Compute Taxable Income
            double taxableIncome = grossIncome - totalDeductions;

            // ✅ Step 4: Compute Withholding Tax
            double withholdingTax = calculateWithholdingTax(taxableIncome);

            // ✅ Step 5: Compute Net Income
            double netIncome = taxableIncome - withholdingTax;

            // ✅ Step 6: Update UI Fields
            grossIncomeField.setText(String.format("%.2f", grossIncome));
            totalBenefitsTextField.setText(String.format("%.2f", totalBenefits));
            totalDeductionsTextField.setText(String.format("%.2f", totalDeductions));
            withholdingTextField.setText(String.format("%.2f", withholdingTax));
            netIncomeTextField.setText(String.format("%.2f", netIncome));

            // ✅ Step 7: Insert into Database
            insertPayrollData(Integer.parseInt(empID), startDate, endDate, fName, lastName, position, basicSalary, hourlyRate, daysWorked, hoursWorked,
                    overtime, grossIncome, riceSubsidy, phoneAllowance, clothAllowance, totalBenefits, sss, philHealth, pagIBIG, totalDeductions, taxableIncome, withholdingTax, netIncome);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format! Please check your input.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    // ✅ Withholding Tax Calculation Method
    public double calculateWithholdingTax(double taxableIncome) {
        if (taxableIncome <= 20832) {
            return 0;
        } else if (taxableIncome <= 33333) {
            return (taxableIncome - 20833) * 0.20;
        } else if (taxableIncome <= 66667) {
            return 2500 + (taxableIncome - 33333) * 0.25;
        } else if (taxableIncome <= 166667) {
            return 10833 + (taxableIncome - 66667) * 0.30;
        } else if (taxableIncome <= 666667) {
            return 40833.33 + (taxableIncome - 166667) * 0.32;
        } else {
            return 200833.33 + (taxableIncome - 666667) * 0.35;
        }
    }


    // Insert Payroll Data into SQLite Database
    public void insertPayrollData(int empID, String startDate, String endDate, String fName, String lastName, String position, double basicSalary, double hourlyRate,
                                  int daysWorked, int hoursWorked, int overtime, double grossIncome, double riceSubsidy, double phoneAllowance, double clothAllowance,
                                  double totalBenefits, double sss, double philHealth, double pagIBIG, double totalDeductions, double taxableIncome, double withholdingTax,
                                  double netIncome) {
        String sql = "INSERT INTO Payroll (emp_id, pay_period_start, pay_period_end, first_name, last_name, job_position, basic_salary, hourly_rate, days_worked, hours_worked, overtime_hours, " +
                "gross_income, rice_subsidy, phone_allowance, clothing_allowance, total_benefits, sss_deduction, philhealth_deduction, pagibig_deduction, total_deductions, taxable_income, withholding_tax, net_income) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:path_to_your_database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empID);
            pstmt.setString(2, startDate);
            pstmt.setString(3, endDate);
            pstmt.setString(4, fName);
            pstmt.setString(5, lastName);
            pstmt.setString(6, position);
            pstmt.setDouble(7, basicSalary);
            pstmt.setDouble(8, hourlyRate);
            pstmt.setInt(9, daysWorked);
            pstmt.setInt(10, hoursWorked);
            pstmt.setInt(11, overtime);
            pstmt.setDouble(12, grossIncome);
            pstmt.setDouble(13, riceSubsidy);
            pstmt.setDouble(14, phoneAllowance);
            pstmt.setDouble(15, clothAllowance);
            pstmt.setDouble(16, totalBenefits);
            pstmt.setDouble(17, sss);
            pstmt.setDouble(18, philHealth);
            pstmt.setDouble(19, pagIBIG);
            pstmt.setDouble(20, totalDeductions);
            pstmt.setDouble(21, taxableIncome);
            pstmt.setDouble(22, withholdingTax);
            pstmt.setDouble(23, netIncome);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Payroll Calculation Completed.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, String.format("Calculation Failed: %s", e.getMessage()));
        }
    }
    // Displays the data in the payroll history tab

    @Override
    public void fetchPayrollHistory(DefaultTableModel tableModel) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();

            //Execute the SQL Query to retrieve certain columns
            String selectQuery = "SELECT payroll_id, emp_ID, pay_period_start, pay_period_end, first_name, last_name, total_benefits," +
                    " gross_income, total_deductions, taxable_income, net_income FROM Payroll";
            resultSet = statement.executeQuery(selectQuery);

            tableModel.setRowCount(0);

            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getInt("payroll_id"),
                        resultSet.getInt("emp_ID"),
                        resultSet.getDate("pay_period_start"),
                        resultSet.getDate("pay_period_end"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("total_benefits"),
                        resultSet.getString("taxable_income"),
                        resultSet.getString("gross_income"),
                        resultSet.getDouble("total_deductions"),
                        resultSet.getDouble("net_income"),
                };
                tableModel.addRow(rowData);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred." + e.getMessage());
        } finally {
            //Close the resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "An error occurred.", e);
            }
        }
    }
}
