package src.factory;
import src.model.*;
public class PaymentFactory {

    public static Payment createPayment(int id, int patientId, double amount, String paymentMethod, String paymentDate) {
        // Create a new Payment object with the given parameters
        return new Payment(id, patientId, amount, paymentMethod, paymentDate);
    }
}
