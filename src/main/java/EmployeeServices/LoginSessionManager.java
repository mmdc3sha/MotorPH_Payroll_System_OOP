package EmployeeServices;

public class LoginSessionManager {
    private static LoginSessionManager instance;
    private int loggedInEmpID; // Stores the Employee ID

    private LoginSessionManager() {
        // Private constructor to prevent instantiation
    }

    public static LoginSessionManager getInstance() {
        if (instance == null) {
            instance = new LoginSessionManager();
        }
        return instance;
    }

    public void setLoggedInEmpID(int empID) {
        this.loggedInEmpID = empID;
    }

    public int getLoggedInEmpID() {
        return loggedInEmpID;
    }
}
