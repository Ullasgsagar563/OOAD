package src.factory;
import src.model.*;

public class ConsultancyFactory {

    public static Consultancy createConsultancy( int doctorId, String doctorName, String patientName, String date, String time,String service) {
        // Create a new Consultancy object with the given parameters
        return new Consultancy(doctorId, doctorName, patientName, date, time,service);
    }
}