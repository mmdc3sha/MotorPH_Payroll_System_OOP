package AdminView;
import java.sql.SQLException;

public interface PayrollServiceInterface {
    // Fetches data from the Payroll table in the database
    void calculatePayroll() throws SQLException;
    void displayPayslip();
}
