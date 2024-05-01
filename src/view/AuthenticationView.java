// AuthenticationView.java
package src.view;

import src.controller.AuthenticationController;
import src.controller.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AuthenticationView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private AuthenticationController authController;
    private AdminController adminController; // Add AdminController field
    private UserController userController;

    // Modify constructor to accept AdminController parameter
    public AuthenticationView(AuthenticationController authController, AdminController adminController,UserController userController) {
        this.authController = authController;
        this.adminController = adminController; // Assign the parameter to the field
        this.userController=userController;

        // Initialize frame and components
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(null);

        // Initialize components
        usernameField = new JTextField();
        usernameField.setBounds(20, 20, 250, 25);
        add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(20, 50, 250, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 85, 100, 25);
        add(loginButton);

        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    boolean isAuthenticated = authController.authenticate(username, password);
                    if (isAuthenticated) {
                        dispose(); // Close login window
                        if (isAdmin(username)) {
                            // Pass the adminController to AdminView constructor
                            new AdminView(adminController).setVisible(true);
                        } else {
                            new UserView(userController).setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: Could not authenticate user");
                }
            }
        });
    }

    private boolean isAdmin(String username) {
        // Logic to check if the user is an admin based on username
        // You can modify this method according to your database schema
        return username.equals("admin"); // Example: Check if username is "admin"
    }
}
