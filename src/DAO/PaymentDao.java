package src.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.model.Payment;

public interface PaymentDao {
    List<Payment> getAllPayments() throws SQLException;
    Payment getPaymentById(int id) throws SQLException;
    void addPayment(Payment payment) throws SQLException;
    void updatePayment(Payment payment) throws SQLException;
    void deletePayment(int id) throws SQLException;
}

class PaymentDaoImpl implements PaymentDao {
    private Connection connection;
    private static final String SELECT_ALL_PAYMENTS = "SELECT * FROM payments";
    private static final String SELECT_PAYMENT_BY_ID = "SELECT * FROM payments WHERE id = ?";
    private static final String INSERT_PAYMENT = "INSERT INTO payments (patient_id, amount, paymentMethod, paymentDate) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_PAYMENT = "UPDATE payments SET patient_id = ?, amount = ?, paymentMethod = ?, paymentDate = ? WHERE id = ?";
    private static final String DELETE_PAYMENT = "DELETE FROM payments WHERE id = ?";

    public PaymentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Payment> getAllPayments() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAYMENTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int patientId = resultSet.getInt("patient_id");
                double amount = resultSet.getDouble("amount");
                String paymentMethod = resultSet.getString("paymentMethod");
                String paymentDate = resultSet.getString("paymentDate");
                Payment payment = new Payment(id, patientId, amount, paymentMethod, paymentDate);
                payments.add(payment);
            }
        }
        return payments;
    }

    @Override
    public Payment getPaymentById(int id) throws SQLException {
        Payment payment = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYMENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int patientId = resultSet.getInt("patient_id");
                    double amount = resultSet.getDouble("amount");
                    String paymentMethod = resultSet.getString("paymentMethod");
                    String paymentDate = resultSet.getString("paymentDate");
                    payment = new Payment(id, patientId, amount, paymentMethod, paymentDate);
                }
            }
        }
        return payment;
    }

    @Override
    public void addPayment(Payment payment) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PAYMENT)) {
            preparedStatement.setInt(1, payment.getPatientId());
            preparedStatement.setDouble(2, payment.getAmount());
            preparedStatement.setString(3, payment.getPaymentMethod());
            preparedStatement.setString(4, payment.getPaymentDate());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updatePayment(Payment payment) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PAYMENT)) {
            preparedStatement.setInt(1, payment.getPatientId());
            preparedStatement.setDouble(2, payment.getAmount());
            preparedStatement.setString(3, payment.getPaymentMethod());
            preparedStatement.setString(4, payment.getPaymentDate());
            preparedStatement.setInt(5, payment.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deletePayment(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PAYMENT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
