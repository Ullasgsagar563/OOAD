package src.DAO;

import src.model.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    private final Connection connection;

    public AdminDao(Connection connection) {
        this.connection = connection;
    }

    // Method to add an admin user to the database
    public void addAdmin(Admin admin) throws SQLException {
        String query = "INSERT INTO admin (username, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getUsername());
            statement.setString(2, admin.getPassword());
            statement.executeUpdate();
        }
    }

    // Method to retrieve an admin user from the database by username
    public Admin getAdminByUsername(String username) throws SQLException {
        String query = "SELECT * FROM admin WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String user = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    return new Admin(user, password);
                }
            }
        }
        return null;
    }

    // Method to remove an admin user from the database by username
    public void removeAdminByUsername(String username) throws SQLException {
        String query = "DELETE FROM admin WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.executeUpdate();
        }
    }
}
