package src.factory;

import src.model.*;

public class UserFactory {

    public static User createUser( String username, String password, String role) {
        // Create a new User object with the given parameters
        return new User(username, password, role);
    }
}

