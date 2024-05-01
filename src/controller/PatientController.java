package src.controller;

import java.sql.SQLException; // Import SQLException
import java.util.List;
import src.DAO.*;
import src.view.*;
import src.model.*;

public class PatientController {
    private PatientView patientView;
    private PatientDao patientDao;

    public PatientController(PatientView patientView, PatientDao patientDao) {
        this.patientView = patientView;
        this.patientDao = patientDao;
    }

    public void displayPatients() {
        try {
            List<Patient> patients = patientDao.getAllPatients();
            patientView.displayPatients(patients);
        } catch (SQLException e) {
            // Handle the exception (e.g., log it, show an error message)
            e.printStackTrace();
        }
    }

    // Other controller methods can be added here based on the application requirements
}
