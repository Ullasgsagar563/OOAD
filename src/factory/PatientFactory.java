package src.factory;

import src.model.*;

public class PatientFactory {

    public static Patient createPatient(int id, String name, int age, String gender, String phoneNumber, String address, String medicalHistory) {
        // Create a new Patient object with the given parameters
        return new Patient(id, name, age, gender, phoneNumber, address, medicalHistory);
    }
}