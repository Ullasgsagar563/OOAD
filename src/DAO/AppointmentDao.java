package src.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.model.*;

public interface AppointmentDao {
    List<Appointment> getAllAppointments() throws SQLException;
    Appointment getAppointmentById(int id) throws SQLException;
    void addAppointment(Appointment appointment) throws SQLException;
    void updateAppointment(Appointment appointment) throws SQLException;
    void deleteAppointment(int id) throws SQLException;


class AppointmentDaoImpl implements AppointmentDao {
    private Connection connection;
    private static final String SELECT_ALL_APPOINTMENTS = "SELECT * FROM appointments";
    private static final String SELECT_APPOINTMENT_BY_ID = "SELECT * FROM appointments WHERE appointmentId = ?";
    private static final String INSERT_APPOINTMENT = "INSERT INTO appointments (doctorName, patientName, appointmentDate) VALUES(?, ?, ?)";
    private static final String UPDATE_APPOINTMENT = "UPDATE appointments SET doctorName = ?, patientName = ?, appointmentDate = ? WHERE appointmentId = ?";
    private static final String DELETE_APPOINTMENT = "DELETE FROM appointments WHERE appointmentId = ?";

    public AppointmentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_APPOINTMENTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                //int id = resultSet.getInt("appointmentId");
                String doctorName = resultSet.getString("doctorName");
                String patientName = resultSet.getString("patientName");
                String dateTime=resultSet.getString("appointmentDate");
                // You can parse date from resultSet as well
                Appointment appointment = new Appointment(doctorName, patientName,dateTime);
                appointments.add(appointment);
            }
        }
        return appointments;
    }

    @Override
    public Appointment getAppointmentById(int id) throws SQLException {
        Appointment appointment = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String doctorName = resultSet.getString("doctorName");
                    String patientName = resultSet.getString("patientName");
                    String dateTime=resultSet.getString("appointmentDate");
                    // You can parse date from resultSet as well
                    appointment = new Appointment( doctorName, patientName,dateTime);
                }
            }
        }
        return appointment;
    }

    @Override
    public void addAppointment(Appointment appointment) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_APPOINTMENT)) {
            preparedStatement.setString(1, appointment.getDoctorName());
            preparedStatement.setString(2, appointment.getPatientName());
            // Set appointment date if needed
            //preparedStatement.setTimestamp(3, Timestamp.valueOf(appointment.getAppointmentDate()));
            preparedStatement.setString(3, appointment.getDateTime());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateAppointment(Appointment appointment) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_APPOINTMENT)) {
            preparedStatement.setString(1, appointment.getDoctorName());
            preparedStatement.setString(2, appointment.getPatientName());
            // Set appointment date if needed
           // preparedStatement.setTimestamp(3, Timestamp.valueOf(appointment.getAppointmentDate()));
            preparedStatement.setInt(4, appointment.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteAppointment(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_APPOINTMENT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

}
}