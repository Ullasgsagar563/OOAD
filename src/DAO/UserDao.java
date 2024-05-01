
package src.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.model.User;

public interface UserDao {
    List<User> getAllUsers() throws SQLException;
    User getUserById(int id) throws SQLException;
    User getUserByUsername(String username) throws SQLException;
    void addUser(User user) throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(int id) throws SQLException;

    // Nested class for implementation
    class UserDaoImpl implements UserDao {
        private Connection connection;
        private static final String SELECT_ALL_USERS = "SELECT * FROM users";
        private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
        private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
        private static final String INSERT_USER = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        private static final String UPDATE_USER = "UPDATE users SET username = ?, password = ?, role = ? WHERE id = ?";
        private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";

        public UserDaoImpl(Connection connection) {
            this.connection = connection;
        }

        @Override
        public List<User> getAllUsers() throws SQLException {
            List<User> users = new ArrayList<>();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String role = resultSet.getString("role");
                    User user = new User(username, password, role);
                    user.setId(id); // Set the ID for the user
                    users.add(user);
                }
            }
            return users;
        }

        @Override
        public User getUserById(int id) throws SQLException {
            User user = null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String username = resultSet.getString("username");
                        String password = resultSet.getString("password");
                        String role = resultSet.getString("role");
                        user = new User(username, password, role);
                        user.setId(id); // Set the ID for the user
                    }
                }
            }
            return user;
        }

        @Override
        public User getUserByUsername(String username) throws SQLException {
            User user = null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String password = resultSet.getString("password");
                        String role = resultSet.getString("role");
                        user = new User(username, password, role);
                        user.setId(id); // Set the ID for the user
                    }
                }
            }
            return user;
        }

        @Override
        public void addUser(User user) throws SQLException {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getRole());
                preparedStatement.executeUpdate();
            }
        }

        @Override
        public void updateUser(User user) throws SQLException {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getRole());
                preparedStatement.setInt(4, user.getId());
                preparedStatement.executeUpdate();
            }
        }

        @Override
        public void deleteUser(int id) throws SQLException {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        }
    }
}
