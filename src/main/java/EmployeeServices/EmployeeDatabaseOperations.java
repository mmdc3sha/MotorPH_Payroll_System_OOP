package EmployeeServices;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public interface EmployeeDatabaseOperations {
    DefaultTableModel getLeaveTable(int empID) throws SQLException;

    default void loadLeaveApplications() {
    }

    default void cancelLeaveApplications(JTable leaveTable) {

    }
}