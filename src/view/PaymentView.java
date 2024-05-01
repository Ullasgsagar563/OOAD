package src.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import src.model.*;

public class PaymentView {
    private JFrame frame;
    private JList<String> paymentList;
    private DefaultListModel<String> listModel;
    private JButton refreshButton;

    public PaymentView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Payment Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        listModel = new DefaultListModel<>();
        paymentList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(paymentList);

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            // Call method to refresh payment list
            // For example: refreshPaymentList();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(refreshButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
    }

    public void displayPayments(List<Payment> payments) {
        listModel.clear();
        for (Payment payment : payments) {
            listModel.addElement(payment.toString());
        }
        frame.setVisible(true);
    }
}

