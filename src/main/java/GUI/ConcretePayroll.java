package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ConcretePayroll extends AbstractPayroll implements PayrollOperationsInterface{
    public ConcretePayroll(int employee_id, String payslip_no, String payPeriodStart, String payPeriodEnd, String employeeName, String firstName,
                           String lastName, String emp_position, double monthly_rate, double daily_rate, int days_worked,
                           int overtime, double totalGrossIncome, double rice_subsidy, double phone_allowance,
                           double clothing_allowance, double totalBenefits) {

        super(employee_id, payPeriodStart, payPeriodEnd, employeeName, firstName,lastName,
                emp_position, monthly_rate,daily_rate,days_worked, overtime,totalGrossIncome,
                rice_subsidy,phone_allowance,clothing_allowance, totalBenefits);

    }


    @Override
    public void insertPayroll_to_Database() {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.connectToDatabase();

        String insert = "INSERT INTO Payroll(emp_id, pay_period_start, pay_period_end, job_position, basic_salary, hourly_rate, days_worked, hours_worked, overtime_hours, gross_income, " +
                "rice_subsidy, phone_allowance, clothing_allowance, total_benefits, sss_deduction, philhealth_deduction, pagibig_deduction, withholding_tax, total_deductions, net_income, total_thirteenth_month_pay) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(insert)) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
