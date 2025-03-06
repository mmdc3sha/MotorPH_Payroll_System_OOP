package GUI;

public abstract class AbstractPayroll {
    protected final String payslip_no;
    protected final int employee_id;
    protected final String payPeriodStart;
    protected final String payPeriodEnd;
    protected final String employeeName;
    protected final String firstName;
    protected final String lastName;
    protected final String emp_position;
    protected final double monthly_rate;
    protected final double daily_rate;
    protected final int days_worked;
    protected final int overtime;
    protected final double totalGrossIncome;
    protected final double rice_subsidy;
    protected final double phone_allowance;
    protected final double clothing_allowance;
    protected final double totalBenefits;

    public AbstractPayroll(int employee_id, String payslip_no, String payPeriodStart, String payPeriodEnd,
                           String employeeName, String firstName, String lastName, String emp_position,
                           double monthly_rate, double daily_rate, int days_worked, int overtime, double totalGrossIncome,
                           double rice_subsidy, double phone_allowance, double clothing_allowance, double totalBenefits, double totalSSSDeduction,
                           double totalPhilHealthDeduction, double totalPagIbigDeduction, double totalWithholdingTax, double totalDeductions, double totalNetIncome) {
        this.employee_id = employee_id;
        this.payslip_no = payslip_no;
        this.payPeriodStart = payPeriodStart;
        this.payPeriodEnd = payPeriodEnd;
        this.employeeName = employeeName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emp_position = emp_position;
        this.monthly_rate = monthly_rate;
        this.daily_rate = daily_rate;
        this.days_worked = days_worked;
        this.overtime = overtime;
        this.totalGrossIncome = totalGrossIncome;
        this.rice_subsidy = rice_subsidy;
        this.phone_allowance = phone_allowance;
        this.clothing_allowance = clothing_allowance;
        this.totalBenefits = totalBenefits;
        this.totalSSSDeduction = totalSSSDeduction;
        this.totalPhilHealthDeduction = totalPhilHealthDeduction;
        this.totalPagIbigDeduction = totalPagIbigDeduction;
        this.totalWithholdingTax = totalWithholdingTax;
        this.totalDeductions = totalDeductions;
        this.totalNetIncome = totalNetIncome;
    }

    protected final double totalSSSDeduction;
    protected final double totalPhilHealthDeduction;
    protected final double totalPagIbigDeduction;
    protected final double totalWithholdingTax;
    protected final double totalDeductions;
    protected final double totalNetIncome;


    // Abstract method to be implemented by subclasses
//    public abstract void insertIntoDatabase();
}

