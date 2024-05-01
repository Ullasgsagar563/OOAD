package src.controller;

import java.util.List;
import java.sql.SQLException;
import src.DAO.*;
import src.view.*;
import src.model.*;

public class DoctorController {
    private DoctorView doctorView;
    private DoctorDao doctorDao;

    public DoctorController(DoctorView doctorView, DoctorDao doctorDao) {
        this.doctorView = doctorView;
        this.doctorDao = doctorDao;
    }

    public void displayDoctors() {
        try {
            List<Doctor> doctors = doctorDao.getAllDoctors();
            doctorView.displayDoctors(doctors);
        } catch (SQLException e) {
            // Handle the SQL exception, for example, by showing an error message to the user
            System.out.println("An error occurred while fetching doctors: " + e.getMessage());
        }
    }

    // Other controller methods can be added here based on the application requirements
}
