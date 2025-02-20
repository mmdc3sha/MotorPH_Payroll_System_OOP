package GUI;

public class ConcretePayroll extends Payroll implements PayrollOperationsInterface{
    public ConcretePayroll() {

    }

    @Override
    public void insertPayroll_to_Database() {
        DatabaseConnection db = new DatabaseConnection();
        db.connectToDatabase();

        //String insertPayrollSQL = "";

    }
}
