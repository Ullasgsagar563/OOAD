package src.model;

// Appointment.java

public class Appointment {
    private int id;
    private String dateTime;
    private String doctorName;
    private String patientName;
    public Appointment(String doctorName, String patientName,String dateTime) {
        //this.id = id;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.dateTime = dateTime;
    }

    public Appointment(int id, String doctorName, String patientName, String dateTime) {
        this.id = id;
        this.dateTime = dateTime;
        this.doctorName = doctorName;
        this.patientName = patientName;
    }

    // Getter and setter methods for each field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDoctorId() {
        return doctorName;
    }

    public void setDoctorId(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientId() {
        return patientName;
    }

    public void setPatientId(String patientName) {
        this.patientName = patientName;
    }
    public String getDoctorName() {
        return doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", doctorName=" + doctorName +
                ", patientName=" + patientName +
                '}';
    }
}
