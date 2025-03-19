package EmployeeServices;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public interface EmployeeDatabaseOperations {
    DefaultTableModel getLeaveTable(int empID) throws SQLException;
}
