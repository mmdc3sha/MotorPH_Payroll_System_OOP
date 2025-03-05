package GUI;

import javax.swing.*;

public class EmployeeSystemViewGUI {
    public EmployeeSystemViewGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Main System");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(true);
        frame.setLayout(null);
        frame.setVisible(true); // Set the frame's visibility to true
    }

    public static void main(String[] args) {
        // Create an instance of MainSystem to display the frame
        new EmployeeSystemViewGUI();
    }
}