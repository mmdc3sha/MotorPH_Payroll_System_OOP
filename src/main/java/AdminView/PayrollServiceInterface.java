package AdminView;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public interface PayrollServiceInterface {
    String fetchPayrollHistory(DefaultTableModel tableModel) throws SQLException;
}
