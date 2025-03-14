package AdminView;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PayrollServices implements PayrollServiceInterface {
    private static final Logger LOGGER = Logger.getLogger(PayrollServices.class.getName());
    private static final String DB_URL = "jdbc:sqlite:src/main/java/MotorPHDatabase.db"; // Externalize Database Path

    private final JDateChooser dateChooser;
    private final JDateChooser dateChooser2;
    private final JTextField empIDTextField;
    private final JTextField fNameTextField;
    private final JTextField lastNameTextField;
    private final JTextField positionTextField;
    private final JTextField daysWorkedTextField;
    private final JTextField hoursWorkedTextField;
    private final JTextField overtimeTextField;
    private final JTextField basicSalaryTextField;
    private final JTextField hrlyRateTextField;
    private final JTextField grossIncomeField;
    private final JTextField riceSubsidyTextField;
    private final JTextField phoneAllowanceTextField;
    private final JTextField clothAllowanceTextField;
    private final JTextField sssTextField;
    private final JTextField philHealthTextField;
    private final JTextField pagIBIGTextField;
    private final JTextField withholdingTextField;
    private final JTextField totalBenefitsTextField;
    private final JTextField totalDeductionsTextField;
    private final JTextField netIncomeTextField;

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        if (dateChooser.getDate() == null || dateChooser2.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Please choose valid pay period dates.", "Date Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            if (empIDTextField == null) {
                JOptionPane.showMessageDialog(null, "Error: Employee ID TextField is not initialized!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                empIDTextField.setText(empIDTextField.getText());
            }

            // Convert Date to String
            String startDate = dateFormat.format(dateChooser.getDate());
            String endDate = dateFormat.format(dateChooser2.getDate());
            String empID = empIDTextField.getText();
            String fName = fNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String position = positionTextField.getText();

            // ✅ Apply default values if the text fields are empty
            int daysWorked = daysWorkedTextField.getText().trim().isEmpty() ? 0 : Integer.parseInt(daysWorkedTextField.getText().trim());
            int hoursWorked = hoursWorkedTextField.getText().trim().isEmpty() ? 0 : Integer.parseInt(hoursWorkedTextField.getText().trim());
            int overtime = overtimeTextField.getText().trim().isEmpty() ? 0 : Integer.parseInt(overtimeTextField.getText().trim());

            double basicSalary = basicSalaryTextField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(basicSalaryTextField.getText().trim());
            double hourlyRate = hrlyRateTextField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(hrlyRateTextField.getText().trim());
            double riceSubsidy = riceSubsidyTextField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(riceSubsidyTextField.getText().trim());
            double phoneAllowance = phoneAllowanceTextField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(phoneAllowanceTextField.getText().trim());
            double clothAllowance = clothAllowanceTextField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(clothAllowanceTextField.getText().trim());
            double sss = sssTextField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(sssTextField.getText().trim());
            double philHealth = philHealthTextField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(philHealthTextField.getText().trim());
            double pagIBIG = pagIBIGTextField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(pagIBIGTextField.getText().trim());

            double grossIncome = basicSalary + (hourlyRate * overtime);
            double totalBenefits = riceSubsidy + phoneAllowance + clothAllowance;
            double totalDeductions = sss + philHealth + pagIBIG;
            double taxableIncome = grossIncome - totalDeductions;
            double withholdingTax = calculateWithholdingTax(taxableIncome);
            double netIncome = taxableIncome - withholdingTax;

            grossIncomeField.setText(String.format("%.2f", grossIncome));
            totalBenefitsTextField.setText(String.format("%.2f", totalBenefits));
            totalDeductionsTextField.setText(String.format("%.2f", totalDeductions));
            withholdingTextField.setText(String.format("%.2f", withholdingTax));
            netIncomeTextField.setText(String.format("%.2f", netIncome));

            // ✅ Step 7: Insert into Database
            insertPayrollData(Integer.parseInt(empID), String.format(startDate), String.format(endDate), fName, lastName, position, basicSalary, hourlyRate, daysWorked, hoursWorked,
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
        String sql = "INSERT INTO Payroll (emp_id, pay_period_start, pay_period_end, first_name, last_name, job_position, " +
                "basic_salary, hourly_rate, days_worked, hours_worked, overtime_hours, gross_income, " +
                "rice_subsidy, phone_allowance, clothing_allowance, total_benefits, " +
                "sss_deduction, philhealth_deduction, pagibig_deduction, total_deductions, taxable_income, " +
                "withholding_tax, net_income) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection conn = DriverManager.getConnection(DB_URL);
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
}
