package src.view;

import src.controller.*;
import src.DAO.*;
import src.model.*;
import src.factory.*;
import java.sql.Connection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class UserView extends JFrame {

    private UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;

        // Initialize frame
        setTitle("Hospital Management System - User Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Example size
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(null);

        // Add heading label
        JLabel headingLabel = new JLabel("Hospital Management System");
        headingLabel.setBounds(250, 50, 300, 30);
        add(headingLabel);

        // Add button for adding patient
        JButton addPatientButton = new JButton("Add Patient");
        addPatientButton.setBounds(300, 100, 200, 30);
        add(addPatientButton);

        // Add action listener to add patient button
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt user for patient details
                String idstr=JOptionPane.showInputDialog("Enter patient id:");
                int id = Integer.parseInt(idstr);
                String phoneNumber=JOptionPane.showInputDialog("Enter patient phoneno:");
                String address=JOptionPane.showInputDialog("Enter patient address");
                String medicalHistory=JOptionPane.showInputDialog("Enter patient medicalhistory:");
                String name = JOptionPane.showInputDialog("Enter patient name:");
                String ageString = JOptionPane.showInputDialog("Enter patient age:");
                int age = Integer.parseInt(ageString);
                String gender = JOptionPane.showInputDialog("Enter patient gender:");
                userController.addPatient(id,name, age, gender,phoneNumber, address, medicalHistory);
                // Show confirmation message
                JOptionPane.showMessageDialog(null, "Patient added successfully");
            }
        });

        // Add button for making appointment
        JButton makeAppointmentButton = new JButton("Make Appointment");
        makeAppointmentButton.setBounds(300, 150, 200, 30);
        add(makeAppointmentButton);

        // Add action listener to make appointment button
        makeAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt user for appointment details
                //String idstr = JOptionPane.showInputDialog("Enter patient id:");
                //int id =Integer.parseInt(idstr);
                String patientName = JOptionPane.showInputDialog("Enter patient name:");
                String doctorName = JOptionPane.showInputDialog("Enter doctor name:");
                String dateTime = JOptionPane.showInputDialog("Enter appointment date and time:");
                userController.makeAppointment( doctorName,patientName,dateTime);
                // Show confirmation message
                JOptionPane.showMessageDialog(null, "Appointment made successfully");
            }
        });

        // Add button for seeing consultancy details
        JButton seeAppointmentDetailsButton = new JButton("See appointment Details");
        seeAppointmentDetailsButton.setBounds(300, 200, 200, 30);
        add(seeAppointmentDetailsButton);

        // Add action listener to see consultancy details button
        seeAppointmentDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display all consultancy details in a dialog
                List<Appointment> appointments = userController.getAllAppointment();
                StringBuilder appointmentText = new StringBuilder("Appointment Details:\n");
                if (appointments != null) {
                    for (Appointment appointment :appointments) {
                        appointmentText.append(appointment.toString()).append("\n");
                    }
                } else {
                    appointmentText.append("No Appointment details found.");
                }
                JOptionPane.showMessageDialog(null, appointmentText.toString());
            }
        });

        // Add button for displaying consultancy details
        JButton displayConsultancyDetailsButton = new JButton("Display Consultancy Details");
        displayConsultancyDetailsButton.setBounds(300, 250, 200, 30);
        add(displayConsultancyDetailsButton);

        // Add action listener to display consultancy details button
        displayConsultancyDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display all consultancy details in a dialog
                List<Consultancy> consultancies = userController.getAllConsultancies();
                StringBuilder consultanciesText = new StringBuilder("Consultancy Details:\n");
                if (consultancies != null) {
                    for (Consultancy consultancy : consultancies) {
                        consultanciesText.append(consultancy.toString()).append("\n");
                    }
                } else {
                    consultanciesText.append("No consultancy details found.");
                }
                JOptionPane.showMessageDialog(null, consultanciesText.toString());
            }
        });

        JButton addConsultancyButton = new JButton("Add Consultancy");
        addConsultancyButton .setBounds(300, 300, 200, 30);
        add( addConsultancyButton );

        // Add action listener to make appointment button
        addConsultancyButton .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String doctorName = JOptionPane.showInputDialog("Enter doctor name:");
                String doctorIdstr = JOptionPane.showInputDialog("Enter docter id:");
                String patientName = JOptionPane.showInputDialog("Enter patient name:");
                String date = JOptionPane.showInputDialog("Enter date (YYYY-MM-DD):");
                String time = JOptionPane.showInputDialog("Enter time (HH:MM:SS):");
                String service = JOptionPane.showInputDialog("Enter service:");
                JOptionPane.showMessageDialog(null, "Appointment made successfully");
                //int doctorId = userController.getDoctorIdByName(doctorName);
                int doctorId =Integer.parseInt(doctorIdstr);

                userController.addConsultancy(doctorId, doctorName, patientName, date, time, service);
                // Show confirmation message
                JOptionPane.showMessageDialog(null, "Appointment made successfully");
            }
        });


        // Add button for displaying wards
        JButton displayWardsButton = new JButton("Display Wards");
        displayWardsButton.setBounds(300, 350, 200, 30);
        add(displayWardsButton);

        // Add action listener to display wards button
        displayWardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display all wards in a dialog
                List<Ward> wards = userController.getAllWards();
                StringBuilder wardsText = new StringBuilder("Wards:\n");
                if (wards != null) {
                    for (Ward w : wards) {
                        wardsText.append(w).append("\n");
                    }
                } else {
                    wardsText.append("No wards found.");
                }
                JOptionPane.showMessageDialog(null, wardsText.toString());
            }
        });


        //Patient

        JButton displayPatientButton = new JButton("Display Patient Details");
        displayPatientButton.setBounds(300, 400, 200, 30);
        add(displayPatientButton);

        // Add action listener to display consultancy details button
        displayPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display all consultancy details in a dialog
                List<Patient> patients = userController.getAllPatient();
                StringBuilder PatientsText = new StringBuilder("Patient Details:\n");
                if (patients != null) {
                    for (Patient patient : patients) {
                        PatientsText.append(patient.toString()).append("\n");
                    }
                } else {
                    PatientsText.append("No Patient details found.");
                }
                JOptionPane.showMessageDialog(null, PatientsText.toString());
            }
        });
        // Add button for generating patient report
        // JButton generateReportButton = new JButton("Generate Patient Report");
        // generateReportButton.setBounds(300, 450, 200, 30);
        // add(generateReportButton);

        // // Add action listener to generate report button
        // generateReportButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // Prompt user for patient ID
        //         String patientIdInput = JOptionPane.showInputDialog("Enter patient ID:");
        //         int patientId;
        //         try {
        //             patientId = Integer.parseInt(patientIdInput);
        //         } catch (NumberFormatException ex) {
        //             JOptionPane.showMessageDialog(null, "Invalid patient ID. Please enter a valid number.");
        //             return;
        //         }

        //         // Generate patient report
        //        // userController.generatePatientReport(patientId);

        //         // Show confirmation message
        //         JOptionPane.showMessageDialog(null, "Patient report generated successfully.");
        //     }
        // });

        // Add Go Back button
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBounds(300, 450, 200, 30);
        add(goBackButton);
        

        // Add action listener to Go Back button
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                 Connection connection = null;
         try {
             connection = DatabaseConnection.getConnection();
         } catch (SQLException ex) {
            ex.printStackTrace();
            return; // Exit the application if connection cannot be established
         }
         UserDao userDao=new UserDao.UserDaoImpl(connection);
         DoctorDao doctorDao=new DoctorDao.DoctorDaoImpl(connection);
         ServiceDao serviceDao=new ServiceDao.ServiceDaoImpl(connection);
         AdminController adminController =new AdminController(userDao, doctorDao, serviceDao);
         
                 AuthenticationController authenticationController=new AuthenticationController(connection);
                 AuthenticationView authenticationView=new AuthenticationView(authenticationController, adminController, userController);
                 authenticationView.setVisible(true);


            }
        });
    }
}
