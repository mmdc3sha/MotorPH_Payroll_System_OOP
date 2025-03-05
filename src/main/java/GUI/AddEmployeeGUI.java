package GUI;

import javax.swing.*;
import java.awt.*;

public class AddEmployeeGUI extends JDialog {
    public AddEmployeeGUI(JFrame AdminSystemViewGUI) {
        super(AdminSystemViewGUI, "Add Employee", true);
        setTitle("Add Employee");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(AdminSystemViewGUI);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Add Employee Form"));
        getContentPane().add(panel);
        panel.setLayout(null);

        JTextField nameField = new JTextField(20);
        JTextField positionField = new JTextField(20);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Position:"));
        panel.add(positionField);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        panel.add(buttonPanel);

        //When cancel button has been pressed, it will automatically close the AddEmployeeGUI
        saveButton.addActionListener(e -> {
           dispose();
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });
    }
}
