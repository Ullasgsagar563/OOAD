package src.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import src.model.Patient;

public class PatientView {
    private JFrame frame;
    private JList<String> patientList;
    private DefaultListModel<String> listModel;
    private JButton refreshButton;

    public PatientView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Patient Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        listModel = new DefaultListModel<>();
        patientList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(patientList);

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call method to refresh patient list
                // For example: refreshPatientList();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(refreshButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
    }

    public void displayPatients(List<Patient> patients) {
        listModel.clear();
        for (Patient patient : patients) {
            listModel.addElement(patient.toString());
        }
        frame.setVisible(true);
    }
}

