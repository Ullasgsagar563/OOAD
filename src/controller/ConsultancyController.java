package src.controller;

import java.util.List;
import java.sql.SQLException;
import src.DAO.*;
import src.view.*;
import src.model.*;

public class ConsultancyController {
    private ConsultancyView consultancyView;
    private ConsultancyDao consultancyDao;

    public ConsultancyController(ConsultancyView consultancyView, ConsultancyDao consultancyDao) {
        this.consultancyView = consultancyView;
        this.consultancyDao = consultancyDao;
    }

    public void displayConsultancies() {
        try {
            List<Consultancy> consultancies = consultancyDao.getAllConsultancies();
            consultancyView.displayConsultancies(consultancies);
        } catch (SQLException e) {
            // Handle the SQL exception, for example, by showing an error message to the user
            System.out.println("An error occurred while fetching consultancies: " + e.getMessage());
        }
    }

    // Other controller methods can be added here based on the application requirements
}