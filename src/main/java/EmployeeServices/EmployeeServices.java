package EmployeeServices;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            Logger.getLogger(EmployeeServices.class.getName()).log(Level.SEVERE, null, e);
        }
    }

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

    @Override
    public void cancelLeaveApplications(JTable leaveTable) {
        int selectedRow = leaveTable.getSelectedRow(); // Use JTable parameter
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a leave application to cancel.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection(db_path)) {
            int empID = LoginSessionManager.getInstance().getLoggedInEmpID(); // Get logged-in Employee ID
            int leaveID = (int) leaveTable.getValueAt(selectedRow, 0); // Get Leave ID from JTable

            String sql = "UPDATE LeaveRequests SET leave_status = 'Cancelled' WHERE leave_request_id = ? AND emp_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, leaveID);
                pstmt.setInt(2, empID);

                int updatedRows = pstmt.executeUpdate();
                if (updatedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Leave application has been cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                    ((DefaultTableModel) leaveTable.getModel()).removeRow(selectedRow); // ✅ Remove row from UI
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to cancel leave. You may not have permission.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "An error occurred. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    protected void reflectLeaveSelectionStatus(JTable leave_table) {
        ListSelectionModel selectionModel = leave_table.getSelectionModel();
        selectionModel.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = leave_table.getSelectedRow();
                if (selectedRow == -1) {
                    handleRowSelection(leave_table, selectedRow);
                }
            }
        });
    }

    // Partial Implementation of HandleRowSelection
    protected abstract void handleRowSelection(JTable leave_table, int selectedRow);
}

