package src.controller;

import java.util.List;
import java.sql.SQLException;
import src.DAO.*;
import src.view.*;
import src.model.*;

public class WardController {
    private WardView wardView;
    private WardDao wardDao;

    public WardController(WardView wardView, WardDao wardDao) {
        this.wardView = wardView;
        this.wardDao = wardDao;
    }

    public void displayWards() {
        try {
            List<Ward> wards = wardDao.getAllWards();
            wardView.displayWards(wards);
        } catch (SQLException e) {
            // Handle the exception (e.g., log it, show an error message)
            e.printStackTrace();
        }
    }

    // Other controller methods can be added here based on the application requirements
}