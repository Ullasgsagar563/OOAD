package src.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import src.model.Doctor;

public class DoctorView {
    private JFrame frame;
    private JList<String> doctorList;
    private DefaultListModel<String> listModel;
    private JButton refreshButton;

    public DoctorView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Doctor Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        listModel = new DefaultListModel<>();
        doctorList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(doctorList);

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            // Call method to refresh doctor list
            // For example: refreshDoctorList();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(refreshButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
    }

    public void displayDoctors(List<Doctor> doctors) {
        listModel.clear();
        for (Doctor doctor : doctors) {
            listModel.addElement(doctor.toString());
        }
        frame.setVisible(true);
    }
}

