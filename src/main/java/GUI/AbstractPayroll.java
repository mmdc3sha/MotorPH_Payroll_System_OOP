package GUI;

public abstract class AbstractPayroll {
    protected String payslip_no;
    protected String payPeriodStart;
    protected String payPeriodEnd;
    protected String employeeName;
    protected String firstName;
    protected String lastName;
    protected String emp_position;
    protected double monthly_rate;
    protected double daily_rate;
    protected int days_worked;
    protected int overtime;
    protected double totalGrossIncome;
    protected double rice_subsidy;
    protected double phone_allowance;
    protected double clothing_allowance;
    protected double totalBenefits;

    public AbstractPayroll(String payslip_no, String payPeriodStart, String payPeriodEnd,
                           String employeeName, String firstName, String lastName, String emp_position,
                           double monthly_rate, double daily_rate, int days_worked, int overtime, double totalGrossIncome,
                           double rice_subsidy, double phone_allowance, double clothing_allowance, double totalBenefits, double totalSSSDeduction,
                           double totalPhilHealthDeduction, double totalPagIbigDeduction, double totalWithholdingTax, double totalDeductions, double totalNetIncome) {
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

    protected double totalSSSDeduction;
    protected double totalPhilHealthDeduction;
    protected double totalPagIbigDeduction;
    protected double totalWithholdingTax;
    protected double totalDeductions;
    protected double totalNetIncome;

    public AbstractPayroll(String payslipNo, String payPeriodStart, String payPeriodEnd, String employeeName, String firstName, String lastName, String empPosition, double monthlyRate, double dailyRate, int daysWorked, int overtime, double totalGrossIncome, double riceSubsidy, double phoneAllowance, double clothingAllowance, double totalBenefits) {
    }


    // Abstract method to be implemented by subclasses
//    public abstract void insertIntoDatabase();
}

