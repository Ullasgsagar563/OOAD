package src.factory;

import src.DAO.*;
import src.controller.*;
import src.view.*;
import src.DAO.ServiceDao;

import java.sql.Connection;
import java.sql.SQLException;

public class AdminFactory {
    public static AdminController createAdminController() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            UserDao userDao = new UserDao.UserDaoImpl(connection);
            DoctorDao doctorDao = new DoctorDao.DoctorDaoImpl(connection);
            //ServiceDao serviceDao=new ServiceDao(connection);
           ServiceDao serviceDao = new ServiceDao.ServiceDaoImpl(connection);
            //ServiceDao serviceDao=new ServiceDao.ServiceDaoImpl(connection);
            return new AdminController(userDao, doctorDao,serviceDao);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately, maybe log it or show an error message
            return null;
        }
    }

    public static AdminView createAdminView() {
        return new AdminView(createAdminController());
    }
}
