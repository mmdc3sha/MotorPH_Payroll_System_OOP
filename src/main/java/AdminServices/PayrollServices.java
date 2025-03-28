package AdminServices;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class PayrollServices implements PayrollServiceInterface {
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
    private final JTextArea outputTextArea;
    private final JScrollPane outputScrollPane;

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
                           JTextField netIncomeTextField,
                           JTextArea outputTextArea,
                           JScrollPane outputScrollPane) {

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
        this.outputTextArea = outputTextArea;
        this.outputScrollPane = outputScrollPane;
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

            // Apply default values if the text fields are empty
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
            // Uses getters Contribution methods for Calculations
            sssTextField.setText(String.format("%.2f", getSSSContribution(basicSalary))); // calculated with getSSSContribution method
            philHealthTextField.setText(String.format("%.2f", getPhilHealthContribution(basicSalary))); // calculated with getPhilHealthContribution method
            pagIBIGTextField.setText(String.format("%.2f", getPagIBIGContribution(basicSalary))); // calculated with getPagIBIGContribution method
            totalBenefitsTextField.setText(String.format("%.2f", totalBenefits));
            totalDeductionsTextField.setText(String.format("%.2f", totalDeductions));
            withholdingTextField.setText(String.format("%.2f", withholdingTax));
            netIncomeTextField.setText(String.format("%.2f", netIncome));

            //Displays the Payslip on the textarea
            double totalEarnings = basicSalary + grossIncome + totalBenefits;
            String separator = "===========================================";
            String payslipOutput = separator + "\n"
                    + "PAYSLIP" + "\n"
                    + separator + "\n"
                    + String.format("%-20s %20s%n", "Employee ID:", empID)
                    + String.format("%-20s %20s%n", "First Name:", fName)
                    + String.format("%-20s %20s%n", "Last Name:", lastName)
                    + String.format("%-20s %20s%n", "Position:", position)
                    + String.format("%-20s %20s%n", "Pay Period Start:",startDate)
                    + String.format("%-20s %20s%n", "Pay Period End:", endDate)
                    + separator + "\n"
                    + String.format("%-20s %20s%n", "EARNINGS", "AMOUNT")
                    + separator + "\n"
                    + String.format("%-20s %20s%n", "Basic Salary:", "PHP " +basicSalary)
                    + String.format("%-20s %20s%n", "Gross Income:", "PHP " +grossIncome)
                    + String.format("%-20s %20s%n", "Total Benefits:", "PHP " +totalBenefits)
                    + String.format("%-20s %20s%n", "Total Earnings:", "PHP " +totalEarnings)
                    + separator + "\n"
                    + String.format("%-20s %20s%n", "DEDUCTIONS", "AMOUNT")
                    + separator + "\n"
                    + String.format("%-20s %20s%n", "SSS Contribution:", "PHP " + getSSSContribution(basicSalary))
                    + String.format("%-20s %20s%n", "PhilHealth:", "PHP " + getPhilHealthContribution(basicSalary))
                    + String.format("%-20s %20s%n", "Pag-IBIG:", "PHP " + getPagIBIGContribution(basicSalary))
                    + String.format("%-20s %20s%n", "Tax Withholding:", "PHP " + withholdingTax)
                    + String.format("%-20s %20s%n", "Total Deductions:", "PHP " + totalDeductions)
                    + "---------------------------------------" + "\n"
                    + String.format("%-20s %20s%n", "Net Salary:", "PHP " + netIncome)
                    + separator + "\n";
            outputTextArea.setText(payslipOutput);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format! Please check your input. Remove ',' in Numbers (e.g 5,000 > 5000)", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double getTaxableIncome() {
        double basicSalary = basicSalaryTextField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(basicSalaryTextField.getText().trim());
        double hourlyRate = hrlyRateTextField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(hrlyRateTextField.getText().trim());
        double sss = sssTextField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(sssTextField.getText().trim());
        double philHealth = philHealthTextField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(philHealthTextField.getText().trim());
        double pagIBIG = pagIBIGTextField.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(pagIBIGTextField.getText().trim());
        int overtime = overtimeTextField.getText().trim().isEmpty() ? 0 : Integer.parseInt(overtimeTextField.getText().trim());

        double grossIncome = basicSalary + (hourlyRate * overtime);
        double totalDeductions = sss + philHealth + pagIBIG;
        return grossIncome - totalDeductions;
    }

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

    /**
     * @param salary
     * @return
     */
    @Override
    public double getPhilHealthContribution(double salary) {
        double premiumRate = 0.03; // 3% premium rate
        double monthlyPremium;

        if (salary <= 10000) { // if the salary is less than PHP10,000
            monthlyPremium = 300.00; // Monthly Premium is PHP300
        } else if (salary > 10000 && salary < 60000) {
            monthlyPremium = salary * premiumRate;
        } else {
            monthlyPremium = 1800.00;
        }
        return monthlyPremium / 2; // Employee pays half, employer pays half
    }

    /**
     * @param salary
     */
    @Override
    public double getSSSContribution(double salary) {
        double[][] brackets = {
                {0, 3249.99, 135.00}, {3250, 3749.99, 157.50}, {3750, 4249.99, 180.00},
                {4250, 4749.99, 202.50}, {4750, 5249.99, 225.00}, {5250, 5749.99, 247.50},
                {5750, 6249.99, 270.00}, {6250, 6749.99, 292.50}, {6750, 7249.99, 315.00},
                {7250, 7749.99, 337.50}, {7750, 8249.99, 360.00}, {8250, 8749.99, 382.50},
                {8750, 9249.99, 405.00}, {9250, 9749.99, 427.50}, {9750, 10249.99, 450.00},
                {10250, 10749.99, 472.50}, {10750, 11249.99, 495.00}, {11250, 11749.99, 517.50},
                {11750, 12249.99, 540.00}, {12250, 12749.99, 562.50}, {12750, 13249.99, 585.00},
                {13250, 13749.99, 607.50}, {13750, 14249.99, 630.00}, {14250, 14749.99, 652.50},
                {14750, 15249.99, 675.00}, {15250, 15749.99, 697.50}, {15750, 16249.99, 720.00},
                {16250, 16749.99, 742.50}, {16750, 17249.99, 765.00}, {17250, 17749.99, 787.50},
                {17750, 18249.99, 810.00}, {18250, 18749.99, 832.50}, {18750, 19249.99, 855.00},
                {19250, 19749.99, 877.50}, {19750, 20249.99, 900.00}, {20250, 20749.99, 922.50},
                {20750, 21249.99, 945.00}, {21250, 21749.99, 967.50}, {21750, 22249.99, 990.00},
                {22250, 22749.99, 1012.50}, {22750, 23249.99, 1035.00}, {23250, 23749.99, 1057.50},
                {23750, 24249.99, 1080.00}, {24250, 24749.99, 1102.50}, {24750, Double.MAX_VALUE, 1125.00}
        };

        for (double[] bracket : brackets) {
            if (salary >= bracket[0] && salary <= bracket[1] ) {
                return bracket[2];
            }
        }

        return 0.0; // Return 0 if salary doesn't match any range
    }

    /**
     * @param salary
     */
    @Override
    public double getPagIBIGContribution(double salary) {
        double employeeRate = (salary <= 1500) ? 0.01 : 0.02; // 1% if salary is 1,000 - 1,500, otherwise 2%
        double employerRate = 0.02; // Employer always contributes 2%
        double employeeShare = salary * employeeRate;
        double employerShare = salary * employerRate;

        // Apply the maximum contribution cap
        employeeShare = Math.min(employeeShare, 100.00);

        return employeeShare; // Return only the employee's contribution
    }

    // LEAVE APPLICATIONS OPERATIONS
    //loads the table
    public void loadLeaveData(DefaultTableModel tableModel) {
        String query = "SELECT leave_request_id, emp_id, leave_type_id, leave_start_date, leave_end_date, reason_for_leave, leave_status FROM LeaveRequests";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // Clear previous data in the table
            tableModel.setRowCount(0);

            // Iterate through the result set and add rows to the table
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("leave_request_id"),
                        rs.getInt("emp_id"),
                        rs.getString("leave_type_id"),
                        rs.getString("leave_start_date"),
                        rs.getString("leave_end_date"),
                        rs.getString("reason_for_leave"),
                        rs.getString("leave_status")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            JOptionPane.showMessageDialog(null, "Error loading leave data: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

