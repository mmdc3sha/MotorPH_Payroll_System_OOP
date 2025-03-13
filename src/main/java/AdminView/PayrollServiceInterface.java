package AdminView;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public interface PayrollServiceInterface {
    // Fetches data from the Payroll table in the database
    void fetchPayrollHistory(DefaultTableModel tableModel) throws SQLException;
    void calculatePayroll();
}
