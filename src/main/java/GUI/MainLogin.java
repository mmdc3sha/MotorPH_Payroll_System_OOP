package GUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainLogin extends javax.swing.JFrame {
    private static final Logger LOGGER = Logger.getLogger(LoginGUI.class.getName());

    public MainLogin() {
        setTitle("MotorPH Payroll System");
        setSize(960, 780);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        //Setting the Look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error setting Nimbus Look and Feel", e);
        }

        JButton employeeButton = new JButton("Employee");
        JButton administratorButton = new JButton("Administrator");

        employeeButton.setBounds(370, 300, 200, 50);
        administratorButton.setBounds(370, 350, 200, 50);

        //Directs to LoginGUI
        employeeButton.addActionListener(e -> {
            LoginGUI employeeGUI = new LoginGUIConnection();
            employeeGUI.setVisible(true);
            dispose(); // Closes this Frame
        });

        //Directs to Payroll Admin Login GUI
        administratorButton.addActionListener(e -> {
            PayrollAdminLoginGUI payrollAdminGUI = new PayrollAdminLoginGUI();
            payrollAdminGUI.setVisible(true);
            dispose();
        });

        //Adds component to the JFrame
        add(employeeButton);
        add(administratorButton);
    }

    public static void main(String[] args) {
        new MainLogin().setVisible(true);
    }
}
