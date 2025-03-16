# MotorPH Payroll System | OOP MS2

 Programmed and Designed by Trisha Belle B. Quismundo for Object-Oriented Programming Course @Mapua Malayan Digital College T2-Y2

---
![MMDC Logo](https://github.com/mmdc3sha/MotorPH_Payroll_System_OOP/blob/master/src/main/resources/images/mmdc_logo.png)

The MotorPH Payroll System is an object-oriented payroll management system designed to efficiently manage employee payrolls.

- Java
- JSwing, Object-Oriented
- SQLite Database

# Features
The payroll system has two modes, one for the Payroll Administrator and the other for Regular MotorPH employees. The Administrator mode features consists of the following:
1. Login - Login Page for Admin and Employees.


![LoginGUI](https://github.com/mmdc3sha/MotorPH_Payroll_System_OOP/blob/master/src/main/resources/images/Screenshot%202025-03-17%20040726.png)

3. Payroll Calculation - In the Employee ID field, enter the employee ID and click the '+' next to it to fill out the basic employee information fields such as "first name, last name, job position, basic salary etc.". However, it does not include days worked, hours worked and overtime automatic setter, you have to input it yourself same as the Pay Period start and End Date.
  * New - Removes the contents of the textfields if there are any, allowing you to start a new file.
  * Print - Opens up an option to Print to PDF, Fax, OneNote etc.
  * Calculate - After you are done with your inputs, click this button to automatically calculate everything for you. The calculations will be displayed on the textArea at the left side. NOTE - you will not be able to edit this TextArea to avoid unnecessary tampering.

![ Payroll Calculation](https://github.com/mmdc3sha/MotorPH_Payroll_System_OOP/blob/master/src/main/resources/images/Screenshot%202025-03-17%20041416.png)

2.1 Payroll History Tab - allows the administrator to view Payslip History. To view the payslip, select a row in the JTable and it will automatically display its contents in the textarea on the left.
  * Print - Opens up an option to Print to PDF, Fax, OneNote etc.
  * Search - In the empty search bar, type in a keyword to easily search for what you're looking for.
  * Update - When a new payslip is saved using "Save File" button, it will not automatically appear right away in the history tab unless you restart the application which is unnecessary. The update button allows you to see the changes on the table immediately.
  * Delete - when clicked, it will open up a popup option wether to delete the record or not. This is added to let you know that the record is going to be deleted.
 

![Payroll History Tab](https://github.com/mmdc3sha/MotorPH_Payroll_System_OOP/blob/master/src/main/resources/images/Screenshot%202025-03-17%20041445.png)

3. Employee Records

Work In-progress:
   * Attendance Management
   * Leave Applications
   * Employee Inquiries
   * EMPLOYEE - DASHBOARD
   * EMPLOYEE - LEAVE PORTAL
   * EMPLOYEE - INQUIRIES PORTAL
   * EMPLOYEE - ATTENDANCE TRACKER
