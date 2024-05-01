package src.factory;
import src.model.*;


public class AppointmentFactory {

    public static Appointment createAppointment(String doctorname, String patientname,String appointmentDate) {
        // Create a new Appointment object with the given parameters
        return new Appointment(doctorname, patientname,appointmentDate);
    }

    // public static Appointment createAppointment( String dateTime, String doctorname, String patientname) {
    //     // Create a new Appointment object with the given parameters
    //     return new Appointment( dateTime, doctorname, patientname);
    // }
}
