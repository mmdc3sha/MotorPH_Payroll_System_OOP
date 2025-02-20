package GUI;

public abstract class AbstractPayroll {
    protected int payrollId;
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


    protected double totalNetIncome;
    protected double totalDeductions;
    protected double totalBenefits;
    protected double totalSSSDeduction;
    protected double totalPhilHealthDeduction;
    protected double totalPagIbigDeduction;
    protected double totalWithholdingTax;
    protected double totalThirteenthMonthPay;


    // Abstract method to be implemented by subclasses
    public abstract void insertIntoDatabase();
}

