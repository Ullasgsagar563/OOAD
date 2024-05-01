package src.controller;

import java.sql.SQLException; // Import SQLException
import java.util.List;
import src.DAO.*;
import src.view.*;
import src.model.*;

public class HospitalController {
    private HospitalView hospitalView;
    private HospitalDao hospitalDao;

    public HospitalController(HospitalView hospitalView, HospitalDao hospitalDao) {
        this.hospitalView = hospitalView;
        this.hospitalDao = hospitalDao;
    }

    public void displayHospitals() {
        try {
            List<Hospital> hospitals = hospitalDao.getAllHospitals();
            hospitalView.displayHospitals(hospitals);
        } catch (SQLException e) {
            // Handle the exception (e.g., log it, show an error message)
            e.printStackTrace();
        }
    }

    // Other controller methods can be added here based on the application requirements
}
