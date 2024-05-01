package src.model;

// Consultancy.java
public class Consultancy {
    private int id;
    private int doctor_id;
    private String doctorName;
    private String patientName;
    private String date;
    private String time;
    private String service;

    public Consultancy(int doctor_id, String doctorName, String patientName, String date, String time,String service) {
        //this.id = id;
        this.doctorName = doctorName;
        this.doctor_id=doctor_id;
        this.patientName = patientName;
        this.date = date;
        this.time = time;
        this.service=service;
    }

    // Getter and setter methods for each field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public void setDoctorId(int doctorId) {
        this.doctor_id = doctorId;
    }
    public int getDoctorId() {
        return doctor_id;
    }
    public String getService(){
        return service;
    }
    public void setService(String service){
        this.service=service;
    }


    @Override
    public String toString() {
        return "Consultancy{" +
                //"id=" + id +
                ", doctorName='" + doctorName + '\'' +
                ", patientName='" + patientName + '\'' +
                ", doctor_id='" + doctor_id + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
