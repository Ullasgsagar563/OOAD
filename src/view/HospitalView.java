package src.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import src.model.*;

public class HospitalView {
    private JFrame frame;
    private JList<String> hospitalList;
    private DefaultListModel<String> listModel;

    public HospitalView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Hospital Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        listModel = new DefaultListModel<>();
        hospitalList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(hospitalList);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
    }

    public void displayHospitals(List<Hospital> hospitals) {
        listModel.clear();
        for (Hospital hospital : hospitals) {
            // Extract necessary information from Hospital object and format it as a String
            String hospitalInfo = "ID: " + hospital.getId() + " | Name: " + hospital.getName() + " | Address: " + hospital.getAddress();
            listModel.addElement(hospitalInfo);
        }
        frame.setVisible(true);
    }

    // Other methods for updating and interacting with the view
}
