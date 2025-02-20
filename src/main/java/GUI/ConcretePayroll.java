package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ConcretePayroll extends AbstractPayroll implements PayrollOperationsInterface{
    public ConcretePayroll(int employee_id, String payslip_no, String payPeriodStart, String payPeriodEnd, String employeeName, String firstName,
                           String lastName, String emp_position, double monthly_rate, double daily_rate, int days_worked,
                           int overtime, double totalGrossIncome, double rice_subsidy, double phone_allowance,
                           double clothing_allowance, double totalBenefits, double total_sss_deduction, double total_philhealth_deduction, double total_pagibig_deduction, double total_withholding_tax,
                           double total_deductions, double total_net_income) {

        super(employee_id, payslip_no, payPeriodStart, payPeriodEnd, employeeName, firstName, lastName, emp_position,  monthly_rate, daily_rate, days_worked, overtime, totalGrossIncome, rice_subsidy, phone_allowance,
                clothing_allowance, totalBenefits, total_sss_deduction, total_philhealth_deduction, total_pagibig_deduction, total_withholding_tax, total_deductions, total_net_income);


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
            psm.setString(4, employeeName);
            psm.setString(5, firstName);
            psm.setString(6, lastName);
            psm.setString(7, emp_position);
            psm.setDouble(8, monthly_rate);
            psm.setDouble(9, daily_rate);
            psm.setInt(10, days_worked);
            psm.setInt(11, overtime);
            psm.setDouble(12, totalGrossIncome);
            psm.setDouble(13, rice_subsidy);
            psm.setDouble(14, phone_allowance);
            psm.setDouble(15, clothing_allowance);
            psm.setDouble(16, totalBenefits);
            psm.setDouble(17, totalSSSDeduction);
            psm.setDouble(18, totalPhilHealthDeduction);
            psm.setDouble(19, totalPagIbigDeduction);
            psm.setDouble(20, totalWithholdingTax);
            psm.setDouble(21, totalDeductions);
            psm.setDouble(22, totalNetIncome);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
