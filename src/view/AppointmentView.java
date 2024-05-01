package src.view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.util.List;// Import List interface
import src.model.Appointment;  // Import Appointment class

public class AppointmentView {
    private JFrame frame;
    private JList<String> appointmentList;
    private DefaultListModel<String> listModel;

    public AppointmentView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Appointment Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        listModel = new DefaultListModel<>();
        appointmentList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(appointmentList);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(mainPanel);
    }

    public void displayAppointments(List<Appointment> appointments) {
        listModel.clear();
        appointments.forEach(appointment -> {
            String dateTime = appointment.getDateTime().toString();
            String doctorName = appointment.getDoctorName();
            String patientName = appointment.getPatientName();
            String item = dateTime + " - Doctor: " + doctorName + ", Patient: " + patientName;
            listModel.addElement(item);
        });
        frame.setVisible(true);
    }
}
