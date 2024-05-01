package src.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import src.model.*;

public class ServiceView {
    private JFrame frame;
    private JList<String> serviceList;
    private DefaultListModel<String> listModel;
    private JButton refreshButton;

    public ServiceView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Service Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        listModel = new DefaultListModel<>();
        serviceList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(serviceList);

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            // Call method to refresh service list
            // For example: refreshServiceList();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(refreshButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
    }

    public void displayServices(List<Service> services) {
        listModel.clear();
        for (Service service : services) {
            listModel.addElement(service.toString());
        }
        frame.setVisible(true);
    }
}
