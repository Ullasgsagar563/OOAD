package src.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.model.Hospital;

public interface HospitalDao {
    List<Hospital> getAllHospitals() throws SQLException;
    Hospital getHospitalById(int id) throws SQLException;
    void addHospital(Hospital hospital) throws SQLException;
    void updateHospital(Hospital hospital) throws SQLException;
    void deleteHospital(int id) throws SQLException;
}

class HospitalDaoImpl implements HospitalDao {
    private Connection connection;
    private static final String SELECT_ALL_HOSPITALS = "SELECT * FROM hospital";
    private static final String SELECT_HOSPITAL_BY_ID = "SELECT * FROM hospital WHERE id = ?";
    private static final String INSERT_HOSPITAL = "INSERT INTO hospital (name, address, phoneNumber) VALUES (?, ?, ?)";
    private static final String UPDATE_HOSPITAL = "UPDATE hospital SET name = ?, address = ?, phoneNumber = ? WHERE id = ?";
    private static final String DELETE_HOSPITAL = "DELETE FROM hospital WHERE id = ?";

    public HospitalDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Hospital> getAllHospitals() throws SQLException {
        List<Hospital> hospitals = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_HOSPITALS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                Hospital hospital = new Hospital(id, name, address, phoneNumber);
                hospitals.add(hospital);
            }
        }
        return hospitals;
    }

    @Override
    public Hospital getHospitalById(int id) throws SQLException {
        Hospital hospital = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOSPITAL_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    hospital = new Hospital(id, name, address, phoneNumber);
                }
            }
        }
        return hospital;
    }

    @Override
    public void addHospital(Hospital hospital) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HOSPITAL)) {
            preparedStatement.setString(1, hospital.getName());
            preparedStatement.setString(2, hospital.getAddress());
            preparedStatement.setString(3, hospital.getPhoneNumber());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateHospital(Hospital hospital) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_HOSPITAL)) {
            preparedStatement.setString(1, hospital.getName());
            preparedStatement.setString(2, hospital.getAddress());
            preparedStatement.setString(3, hospital.getPhoneNumber());
            preparedStatement.setInt(4, hospital.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteHospital(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_HOSPITAL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
