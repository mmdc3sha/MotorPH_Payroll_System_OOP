package EmployeeServices;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public abstract class EmployeeServices implements EmployeeDatabaseOperations {
    private final String db_path = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
    public void insertLeaveApplication(int empID, String type, String startDate, String endDate, String status) {
        String query = "INSERT INTO LeaveRequests (emp_id, leave_type_id, leave_start_date, leave_end_date, leave_status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(db_path);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, empID);
            pstmt.setString(2, type);
            pstmt.setString(3, startDate);
            pstmt.setString(4, endDate);
            pstmt.setString(5, status);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Leave application submitted successfully.");
            } else {
                System.out.println("Failed to submit leave application.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * @return
     * @throws SQLException
     */

    @Override
    public DefaultTableModel getLeaveTable(int empID) {
        String db_path = "jdbc:sqlite:src/main/java/MotorPHDatabase.db";
        String[] columns = {"Leave ID", "Employee ID", "Type", "Start Date", "End Date", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        String query = "SELECT leave_request_id, emp_id, leave_type_id, leave_start_date, leave_end_date, leave_status FROM LeaveRequests WHERE emp_id = ?";

        try (Connection conn = DriverManager.getConnection(db_path);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, empID); // Securely bind the Employee ID
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("leave_request_id"),
                        rs.getInt("emp_id"),
                        rs.getString("leave_type_id"),
                        rs.getString("leave_start_date"),
                        rs.getString("leave_end_date"),
                        rs.getString("leave_status")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
}
