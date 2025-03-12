package AdminView;

import GUI.AdminSystemViewGUI;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class PayrollServices implements PayrollServiceInterface {
    /**
     * @param tableModel
     * @return
     */
    @Override
    public String fetchPayrollHistory(DefaultTableModel tableModel) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/java/MotorPHDatabase.db");
            statement = connection.createStatement();

            //Execute the SQL Query to retrieve certain columns
            String selectQuery = "SELECT payroll_id, emp_ID, pay_period_start, pay_period_end, first_name, last_name, total_benefits," +
                    " gross_income, total_deductions, net_income FROM Payroll";
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
                };
                tableModel.addRow(rowData);
            }
            return "Data Retrieved Successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Data Retrieved Failed." + e.getMessage();
        } finally {
            //Close the resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     */
}
