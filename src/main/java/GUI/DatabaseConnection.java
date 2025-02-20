package GUI;
import java.sql.DriverManager;

public class DatabaseConnection extends EmployeeLoginGUI {
    @Override
    protected void connectToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/java/MotorPHDatabase.db");
            System.out.println("Connection to SQLite has been established.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occured while connecting to MOTORPH Database.");
        }
    }
}
