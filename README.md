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

2. Payroll Calculation - In the Employee ID field, enter the employee ID and click the '+' next to it to fill out the basic employee information fields such as "first name, last name, job position, basic salary". It does not include days worked, hours worked and overtime automatic calculations, you have to input it yourself same as the Pay Period start and End Date.
  * Create New - Removes the contents of the textfields if there are any.
  * Print - Opens up an option to Print to PDF, Fax, OneNote etc.
  * Calculate - After you are done with your inputs, click this button to automatically calculate everything for you. The calculations will be displayed on the textArea at the left side.

![ Payroll Calculation](https://github.com/mmdc3sha/MotorPH_Payroll_System_OOP/blob/master/src/main/resources/images/Screenshot%202025-03-17%20041416.png)

2.1 Payroll History Tab - allows the administrator to view Payslip History. To view the payslip, select a row in the JTable and it will automatically display its contents in the textarea on the left.
