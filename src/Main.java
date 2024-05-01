package src;

import src.controller.*;

import src.view.*;

import src.DAO.*;
import src.DAO.DoctorDao;
import java.sql.Connection;
import java.sql.SQLException;





public class Main {
    public static void main(String[] args) {
        // Obtain database connection from DatabaseConnection class
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return; // Exit the application if connection cannot be established
        }

        // Create AuthenticationService with the connection
        AuthenticationService authService = new AuthenticationService(connection);
        
       

        // Create AuthenticationView with AuthenticationService
        AuthenticationController AC = new AuthenticationController(connection);
        
        UserDao UD = new UserDao.UserDaoImpl(connection);
        DoctorDao DD = new DoctorDao.DoctorDaoImpl(connection);
        WardDao WD=new WardDao.WardDaoImpl(connection);
        ConsultancyDao CD=new ConsultancyDao.ConsultancyDaoImpl(connection);
        PatientDao PD=new PatientDao.PatientDaoImpl(connection);
        AppointmentDao AD=new AppointmentDao.AppointmentDaoImpl(connection);
        ServiceDao SD=new ServiceDao.ServiceDaoImpl(connection);
        AdminController ADC = new AdminController(UD, DD,SD);
        //PatientDao patientDao, ConsultancyDao consultancyDao, WardDao wardDao
        UserController userController=new UserController(PD,CD,WD,AD);
  
        //UserController USC=new UserController(userDao);
        AuthenticationView authView = new AuthenticationView(AC, ADC,userController);

        // Display the AuthenticationView
        authView.setVisible(true);
    }
}
