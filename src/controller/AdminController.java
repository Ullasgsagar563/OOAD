
package src.controller;

import src.DAO.DoctorDao;
import src.DAO.UserDao;
import src.model.Doctor;
import src.model.*;
import src.DAO.*;

import java.sql.SQLException;
import java.util.List;

public class AdminController {
    private UserDao userDao;
    private DoctorDao doctorDao;
    private ServiceDao serviceDao;

    public AdminController(UserDao userDao, DoctorDao doctorDao, ServiceDao serviceDao) {
        this.userDao = userDao;
        this.doctorDao = doctorDao;
        this.serviceDao=serviceDao;
    }

    public void addUser(String username, String password, String role) {
        try {
            if (userDao != null) {
                User newUser = new User(username, password, role);
                userDao.addUser(newUser);
                System.out.println("User added successfully.");
            } else {
                System.err.println("UserDao is not initialized.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe log it or show an error message
        }
    }

    public void addDoctor(String Dname, String spl, String email, String phoneNumber) {
        try {
            if (doctorDao != null) {
                Doctor newDoctor = new Doctor(Dname, spl, email, phoneNumber);
                doctorDao.addDoctor(newDoctor);
                System.out.println("Doctor added successfully.");
            } else {
                System.err.println("DoctorDao is not initialized.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe log it or show an error message
        }
    }
    //addService
    public void addService(String name,double price) {
        try {
            if (serviceDao != null) {
                Service newService = new Service(name,price);
                serviceDao.addService(newService);
                System.out.println("Service added successfully.");
            } else {
                System.err.println(" not initialized.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe log it or show an error message
        }
    }

    public List<User> getAllUsers() {
        try {
            if (userDao != null) {
                return userDao.getAllUsers();
            } else {
                System.err.println("UserDao is not initialized.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe log it or show an error message
            return null;
        }
    }

    public List<Doctor> getAllDoctors() {
        try {
            if (doctorDao != null) {
                return doctorDao.getAllDoctors();
            } else {
                System.err.println("DoctorDao is not initialized.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe log it or show an error message
            return null;
        }
    }
    public List<Service> getAllService() {
        try {
            if (serviceDao != null) {
                return serviceDao.getAllServices();
            } else {
                System.err.println("DoctorDao is not initialized.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe log it or show an error message
            return null;
        }
    }
}
