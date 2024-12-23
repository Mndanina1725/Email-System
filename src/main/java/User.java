import java.sql.*;

public class User {
    private String username;
    private String password;
    private String emailAddress;

    public User(String username, String password, String emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public boolean register() {
        try (Connection conn = DatabaseHandler.getConnection()) {
            String query = "INSERT INTO users (username, password, email_address) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password); // In production, hash the password!
            stmt.setString(3, emailAddress);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User login(String username, String password) {
        try (Connection conn = DatabaseHandler.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("username"), rs.getString("password"), rs.getString("email_address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}

