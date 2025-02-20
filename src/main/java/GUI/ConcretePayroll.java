package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ConcretePayroll extends AbstractPayroll implements PayrollOperationsInterface{
    public ConcretePayroll(String payslip_no, String payPeriodStart, String payPeriodEnd, String employeeName, String firstName,
                           String lastName, String emp_position, double monthly_rate, double daily_rate, int days_worked,
                           int overtime, double totalGrossIncome, double rice_subsidy, double phone_allowance,
                           double clothing_allowance, double totalBenefits) {

        super(payslip_no, payPeriodStart, payPeriodEnd, employeeName, firstName,lastName,
                emp_position, monthly_rate,daily_rate,days_worked, overtime,totalGrossIncome,
                rice_subsidy,phone_allowance,clothing_allowance, totalBenefits);

    }


    @Override
    public void insertPayroll_to_Database() {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.connectToDatabase();

        String insert = "INSERT INTO Payroll(payslip_no, ) VALUES(?,?,?,?,)";

        try (PreparedStatement ps = connection.prepareStatement(insert)) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
