// AuthenticationController.java
package src.controller;

import src.DAO.AuthenticationService;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(Connection connection) {
        this.authenticationService = new AuthenticationService(connection);
    }

    public boolean authenticate(String username, String password) throws SQLException {
        return authenticationService.authenticate(username, password);
    }
}
