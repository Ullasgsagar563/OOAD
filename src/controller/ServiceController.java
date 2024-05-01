package src.controller;

import java.util.List;
import java.sql.SQLException;
import src.DAO.*;
import src.view.*;
import src.model.*;

public class ServiceController {
    private ServiceView serviceView;
    private ServiceDao serviceDao;

    public ServiceController(ServiceView serviceView, ServiceDao serviceDao) {
        this.serviceView = serviceView;
        this.serviceDao = serviceDao;
    }

    public void displayServices() {
        try {
            List<Service> services = serviceDao.getAllServices();
            serviceView.displayServices(services);
        } catch (SQLException e) {
            // Handle the exception (e.g., log it, show an error message)
            e.printStackTrace();
        }
    }

    // Other controller methods can be added here based on the application requirements
}

