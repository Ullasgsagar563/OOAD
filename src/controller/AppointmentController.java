package src.controller;

import java.util.List;
import java.sql.SQLException;
import src.DAO.*;
import src.view.*;
import src.model.*;

public class AppointmentController {
    private AppointmentView appointmentView;
    private AppointmentDao appointmentDao;

    public AppointmentController(AppointmentView appointmentView, AppointmentDao appointmentDao) {
        this.appointmentView = appointmentView;
        this.appointmentDao = appointmentDao;
    }

    public void displayAppointments() {
        try {
            List<Appointment> appointments = appointmentDao.getAllAppointments();
            appointmentView.displayAppointments(appointments);
        } catch (SQLException e) {
            // Handle the SQL exception, for example, by showing an error message to the user
            System.out.println("An error occurred while fetching appointments: " + e.getMessage());
        }
    }

    // Other controller methods can be added here based on the application requirements
}