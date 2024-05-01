// UserController.java
package src.controller;

import src.DAO.ConsultancyDao;
import src.DAO.PatientDao;
import src.DAO.*;
import src.model.Consultancy;
import src.model.*;
import java.time.LocalDateTime;

import java.sql.SQLException;
import java.util.List;

public class UserController {
    private PatientDao patientDao;
    private ConsultancyDao consultancyDao;
    private WardDao wardDao;
    private AppointmentDao appointmentDao;


    public UserController(PatientDao patientDao, ConsultancyDao consultancyDao, WardDao wardDao,AppointmentDao appointmentDao) {
        this.patientDao = patientDao;
        this.consultancyDao = consultancyDao;
        this.wardDao = wardDao;
        this.appointmentDao=appointmentDao;
    }

    public void addPatient(int id, String name, int age, String gender, String phoneNumber, String address, String medicalHistory) {
        try {
            if (patientDao != null) {
                Patient newPatient = new Patient(id,name, age, gender,phoneNumber,address,medicalHistory);
                patientDao.addPatient(newPatient);
                System.out.println("Patient added successfully.");
            } else {
                System.err.println("PatientDao is not initialized.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe log it or show an error message
        }
    }
    public void addConsultancy(int d_id,String d_name,String p_name,String date,String time,String service)
    {
        try {
            if (consultancyDao != null) {
                Consultancy newConsultancy = new Consultancy(d_id, d_name, p_name, date, time, service);
               consultancyDao.addConsultancy(newConsultancy);
                System.out.println("Consultancy added successfully.");
            } else {
                System.err.println("Consultancy is not initialized.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe log it or show an error message
        }

    }

    public void makeAppointment(String doctorName,String patientName, String dateTime) {
        // Logic to make appointment
        try {
            if (appointmentDao != null) {
                Appointment newaAppointment = new Appointment(doctorName,patientName,dateTime);
                appointmentDao.addAppointment(newaAppointment);
                System.out.println("appointment added successfully.");
            } else {
                System.err.println("appointmentDao is not initialized.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe log it or show an error message
        }
    }

    public List<Consultancy> getAllConsultancies() {
        List<Consultancy> consultancies = null;
    try {
        consultancies = consultancyDao.getAllConsultancies();
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exception appropriately, maybe log it or show an error message
    }
    return consultancies;
    }

    // public void assignWard(String patientName, String wardName) {
    //     // Logic to assign ward
    //     try {
    //         //Assuming you have a method in the WardDao to assign a ward to a patient by name
    //         wardDao.assignWardToPatient(patientName, wardName);
    //         System.out.println("Ward assigned successfully.");
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         // Handle exception appropriately, maybe log it or show an error message
    //     }
    // }
    public List<Ward> getAllWards() {
        List<Ward> wards = null;
        try {
            // Assuming you have a method in the WardDao to get all ward names
            wards = wardDao.getAllWards();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe log it or show an error message
        }
        return wards;
    }
    
    public List<Appointment> getAllAppointment() {
        List<Appointment> appointments = null;
        try {
            appointments = appointmentDao.getAllAppointments();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe log it or show an error message
        }
        return appointments;
    }
    
    public List<Patient> getAllPatient() {
        List<Patient> patients = null;
        try {
            //appointments = appointmentDao.getAllAppointments();
            patients=patientDao.getAllPatients();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe log it or show an error message
        }
        return patients;
    }
    
}
