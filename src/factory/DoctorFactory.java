package src.factory;

import src.model.*;

public class DoctorFactory {

    public static Doctor createDoctor(String name, String specialization, String email, String phoneNumber) {
        // Create a new Doctor object with the given parameters
        return new Doctor( name, specialization, email, phoneNumber);
    }
}