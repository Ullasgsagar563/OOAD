
package src.view;

import src.DAO.*;
import src.controller.*;
import src.model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;


public class AdminView extends JFrame {

    private AdminController adminController;

    public AdminView(AdminController adminController) {
        this.adminController = adminController;

        // Initialize frame
        setTitle("Hospital Management System - Admin Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Example size
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(null);

        // Add heading label
        JLabel headingLabel = new JLabel("Hospital Management System");
        headingLabel.setBounds(250, 50, 300, 30);
        add(headingLabel);

        // Add button for adding user
        JButton addUserButton = new JButton("Add User");
        addUserButton.setBounds(300, 100, 200, 30);
        add(addUserButton);

        // Add action listener to add user button
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt user for username, password, and role
                String username = JOptionPane.showInputDialog("Enter username:");
                String password = JOptionPane.showInputDialog("Enter password:");
                String role = JOptionPane.showInputDialog("Enter role:");
                adminController.addUser(username, password, role);
                // Show confirmation message
                JOptionPane.showMessageDialog(null, "User added successfully");
            }
        });

        JButton addDoctorButton = new JButton("Add Doctor");
        addDoctorButton.setBounds(300, 150, 200, 30);
        add(addDoctorButton);

        // Add action listener to add doctor button
        addDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt user for doctor details
                String Dname = JOptionPane.showInputDialog("Enter doctor name:");
                String spl = JOptionPane.showInputDialog("Enter specialization:");
                String email = JOptionPane.showInputDialog("Enter email:");
                String phoneNumber= JOptionPane.showInputDialog("Enter phone number:");
                // Remove non-numeric characters from phone number
                //String phoneNumberCleaned = phoneNumberString.replaceAll("[^\\d]", "");
                //int phoneNumber = Integer.parseInt(phoneNumberCleaned);
                adminController.addDoctor(Dname, spl, email, phoneNumber);
                // Show confirmation message
                JOptionPane.showMessageDialog(null, "Doctor added successfully");
            }
        });
        //Add button for adding service
        JButton addServiceButton = new JButton("Add Service");
        addServiceButton.setBounds(300, 200, 200, 30);
        add(addServiceButton);

        // Add action listener to add service button
        addServiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt user for service details
                String name = JOptionPane.showInputDialog("Enter service name:");
                String priceString = JOptionPane.showInputDialog("Enter service price:");
                double price = Double.parseDouble(priceString);
                adminController.addService(name, price);
                // Show confirmation message
                JOptionPane.showMessageDialog(null, "Service added successfully");
            }
        });


        // Add button for showing all doctors
        JButton showAllDoctorButton = new JButton("Show All Doctors");
        showAllDoctorButton.setBounds(300, 250, 200, 30);
        add(showAllDoctorButton);

        showAllDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Doctor> doctors = adminController.getAllDoctors();
                StringBuilder docText = new StringBuilder("All doctors:\n");
                if (doctors != null) {
                    for (Doctor doc : doctors) {
                        docText.append(doc.toString()).append("\n");
                    }
                } else {
                    docText.append("No doctors found.");
                }
                JOptionPane.showMessageDialog(null, docText.toString());
            }
        });

        // Add button for showing all users
        JButton showAllUsersButton = new JButton("Show All Users");
        showAllUsersButton.setBounds(300, 300, 200, 30);
        add(showAllUsersButton);

        // Add action listener to show all users button
        showAllUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display all users in a dialog
                List<User> users = adminController.getAllUsers();
                StringBuilder usersText = new StringBuilder("All Users:\n");
                if (users != null) {
                    for (User user : users) {
                        usersText.append(user.toString()).append("\n");
                    }
                } else {
                    usersText.append("No users found.");
                }
                JOptionPane.showMessageDialog(null, usersText.toString());
            }
        });

        JButton showAllServiceButton = new JButton("Show All Service");
        showAllServiceButton.setBounds(300, 350, 200, 30);
        add(showAllServiceButton);

        // Add action listener to show all users button
        showAllServiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display all users in a dialog
                List<Service> Services = adminController.getAllService();
                StringBuilder ServiceText = new StringBuilder("All Users:\n");
                if (Services != null) {
                    for (Service Service : Services) {
                        ServiceText.append(Service.toString()).append("\n");
                    }
                } else {
                    ServiceText.append("No users found.");
                }
                JOptionPane.showMessageDialog(null, ServiceText.toString());
            }
        });

        // Add Go Back button
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBounds(300, 400, 200, 30);
        add(goBackButton);

        // Add action listener to Go Back button
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate back to the UserView
                //dispose(); // Close the AdminView
                
         Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return; // Exit the application if connection cannot be established
        }
                PatientDao patientDao=new PatientDao.PatientDaoImpl(connection);
                ConsultancyDao consultancyDao=new ConsultancyDao.ConsultancyDaoImpl(connection);
                WardDao wardDao=new WardDao.WardDaoImpl(connection);
                AppointmentDao appointmentDao= new AppointmentDao.AppointmentDaoImpl(connection);
                UserController userController = new UserController(patientDao, consultancyDao, wardDao, appointmentDao); // Instantiate UserController
                //UserView userView = new UserView(userController); // Pass userController to UserView constructor
                //userView.setVisible(true); // Display the UserView
                AuthenticationController authenticationController=new AuthenticationController(connection);
                AuthenticationView authenticationView=new AuthenticationView(authenticationController, adminController, userController);
                authenticationView.setVisible(true);


            }
        });
    }
}

