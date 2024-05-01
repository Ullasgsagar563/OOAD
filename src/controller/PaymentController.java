package src.controller;

import java.sql.SQLException; // Import SQLException
import java.util.List;
import src.DAO.*;
import src.view.*;
import src.model.*;

public class PaymentController {
    private PaymentView paymentView;
    private PaymentDao paymentDao;

    public PaymentController(PaymentView paymentView, PaymentDao paymentDao) {
        this.paymentView = paymentView;
        this.paymentDao = paymentDao;
    }

    public void displayPayments() {
        try {
            List<Payment> payments = paymentDao.getAllPayments();
            paymentView.displayPayments(payments);
        } catch (SQLException e) {
            // Handle the exception (e.g., log it, show an error message)
            e.printStackTrace();
        }
    }

    // Other controller methods can be added here based on the application requirements
}
