package src.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.model.Consultancy;

public interface ConsultancyDao {
    List<Consultancy> getAllConsultancies() throws SQLException;
    Consultancy getConsultancyById(int id) throws SQLException;
    void addConsultancy(Consultancy consultancy) throws SQLException;
    void updateConsultancy(Consultancy consultancy) throws SQLException;
    void deleteConsultancy(int id) throws SQLException;

class ConsultancyDaoImpl implements ConsultancyDao {
    private Connection connection;
    private static final String SELECT_ALL_CONSULTANCIES = "SELECT * FROM consultancies";
    private static final String SELECT_CONSULTANCY_BY_ID = "SELECT * FROM consultancies WHERE id = ?";
    private static final String INSERT_CONSULTANCY = "INSERT INTO consultancies (doctor_id, doctorName, patientName, date, time,service) VALUES (?, ?, ?, ?, ?,?)";
    private static final String UPDATE_CONSULTANCY = "UPDATE consultancies SET doctor_id = ?, doctor_name = ?, patient_name = ?, date = ?, time = ? ,service=? WHERE id = ?";
    private static final String DELETE_CONSULTANCY = "DELETE FROM consultancies WHERE id = ?";

    public ConsultancyDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Consultancy> getAllConsultancies() throws SQLException {
        List<Consultancy> consultancies = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CONSULTANCIES);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int doctorId = resultSet.getInt("doctor_id");
                String doctorName = resultSet.getString("doctorName");
                String patientName = resultSet.getString("patientName");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                String service=resultSet.getString("service");
                Consultancy consultancy = new Consultancy(doctorId, doctorName, patientName, date, time,service);
                consultancies.add(consultancy);
            }
        }
        return consultancies;
    }

    @Override
    public Consultancy getConsultancyById(int id) throws SQLException {
        Consultancy consultancy = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONSULTANCY_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int doctorId = resultSet.getInt("doctor_id");
                    String doctorName = resultSet.getString("doctorName");
                    String patientName = resultSet.getString("patientName");
                    String date = resultSet.getString("date");
                    String time = resultSet.getString("time");
                    String service=resultSet.getString("service");
                    consultancy = new Consultancy(doctorId, doctorName, patientName, date, time,service);
                }
            }
        }
        return consultancy;
    }

    @Override
    public void addConsultancy(Consultancy consultancy) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONSULTANCY)) {
            preparedStatement.setInt(1, consultancy.getDoctorId());
            preparedStatement.setString(2, consultancy.getDoctorName());
            preparedStatement.setString(3, consultancy.getPatientName());
            preparedStatement.setString(4, consultancy.getDate());
            preparedStatement.setString(5, consultancy.getTime());
            preparedStatement.setString(6, consultancy.getService());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateConsultancy(Consultancy consultancy) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONSULTANCY)) {
            preparedStatement.setInt(1, consultancy.getDoctorId());
            preparedStatement.setString(2, consultancy.getDoctorName());
            preparedStatement.setString(3, consultancy.getPatientName());
            preparedStatement.setString(4, consultancy.getDate());
            preparedStatement.setString(5, consultancy.getTime());
            preparedStatement.setInt(6, consultancy.getId());
            preparedStatement.setString(6, consultancy.getService());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteConsultancy(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CONSULTANCY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
}