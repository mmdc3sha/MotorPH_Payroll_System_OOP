package GUI;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConcretePayroll extends AbstractPayroll implements PayrollOperationsInterface{
    public ConcretePayroll(int employee_id, String payslip_no, String payPeriodStart, String payPeriodEnd, String employeeName, String firstName,
                           String lastName, String emp_position, double monthly_rate, double daily_rate, int days_worked,
                           int overtime, double totalGrossIncome, double rice_subsidy, double phone_allowance,
                           double clothing_allowance, double totalBenefits, double totalSSSDeduction, double totalPhilHealthDeduction, double totalPagIbigDeduction, double totalWithholdingTax,
                           double totalDeductions, double totalNetIncome) {
        super(employee_id, payslip_no, payPeriodStart, payPeriodEnd, employeeName, firstName, lastName, emp_position,  monthly_rate, daily_rate, days_worked, overtime, totalGrossIncome, rice_subsidy, phone_allowance,
                clothing_allowance, totalBenefits, totalSSSDeduction, totalPhilHealthDeduction, totalPagIbigDeduction, totalWithholdingTax, totalDeductions, totalNetIncome);
    }

    @Override
    public void insertPayroll_to_Database() {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.connectToDatabase();

        String insert = "INSERT INTO Payroll(emp_id, pay_period_start, pay_period_end, job_position, basic_salary, hourly_rate, days_worked, hours_worked, overtime_hours, gross_income, " +
                "rice_subsidy, phone_allowance, clothing_allowance, total_benefits, sss_deduction, philhealth_deduction, pagibig_deduction, withholding_tax, total_deductions, net_income, total_thirteenth_month_pay) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement psm = connection.prepareStatement(insert)) {
            psm.setInt(1, employee_id);
            psm.setString(2, payPeriodStart);
            psm.setString(3, payPeriodEnd);
            psm.setString(4, emp_position);
            psm.setDouble(5, monthly_rate);
            psm.setDouble(6, daily_rate);
            psm.setInt(7, days_worked);
            psm.setInt(8, 0); // Assuming hours worked is 0
            psm.setInt(9, overtime);
            psm.setDouble(10, totalGrossIncome);
            psm.setDouble(11, rice_subsidy);
            psm.setDouble(12, phone_allowance);
            psm.setDouble(13, clothing_allowance);
            psm.setDouble(14, totalBenefits);
            psm.setDouble(15, totalSSSDeduction);
            psm.setDouble(16, totalPhilHealthDeduction);
            psm.setDouble(17, totalPagIbigDeduction);
            psm.setDouble(18, totalWithholdingTax);
            psm.setDouble(19, totalDeductions);
            psm.setDouble(20, totalNetIncome);
            psm.setDouble(21, 0.0); // Assuming thirteenth month pay is 0
            psm.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to Insert Payroll. Please try again later.");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}