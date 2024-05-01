package src.model;
public class Admin extends User {
    public Admin( String username, String password) {
        super( username, password, "admin");
    }

    // Additional methods specific to administrators can be added here
}
