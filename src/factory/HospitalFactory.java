package src.factory;

import src.model.*;

public class HospitalFactory {

    public static Hospital createHospital(int id, String name, String address, String phoneNumber) {
        // Create a new Hospital object with the given parameters
        return new Hospital(id, name, address, phoneNumber);
    }
}
