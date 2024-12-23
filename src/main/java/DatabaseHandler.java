import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:sqlite:email_system.db";

    static {
        try (Connection conn = getConnection()) {
            // Create Users table if it doesn't exist
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "username TEXT UNIQUE, "
                    + "password TEXT, "
                    + "email_address TEXT UNIQUE)";
            Statement stmt = conn.createStatement();
            stmt.execute(createUsersTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
