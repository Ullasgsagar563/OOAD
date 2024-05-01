package src.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.model.Patient;

public interface PatientDao {
    List<Patient> getAllPatients() throws SQLException;
    Patient getPatientById(int id) throws SQLException;
    void addPatient(Patient patient) throws SQLException;
    void updatePatient(Patient patient) throws SQLException;
    void deletePatient(int id) throws SQLException;

class PatientDaoImpl implements PatientDao {
    private Connection connection;
    private static final String SELECT_ALL_PATIENTS = "SELECT * FROM patients";
    private static final String SELECT_PATIENT_BY_ID = "SELECT * FROM patients WHERE id = ?";
    private static final String INSERT_PATIENT = "INSERT INTO patients (name, age, gender, phoneNumber, address, medicalHistory) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PATIENT = "UPDATE patients SET name = ?, age = ?, gender = ?, phoneNumber = ?, address = ?, medicalHistory = ? WHERE id = ?";
    private static final String DELETE_PATIENT = "DELETE FROM patients WHERE id = ?";

    public PatientDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PATIENTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                String medicalHistory = resultSet.getString("medicalHistory");
                Patient patient = new Patient(id, name, age, gender, phoneNumber, address, medicalHistory);
                patients.add(patient);
            }
        }
        return patients;
    }

    @Override
    public Patient getPatientById(int id) throws SQLException {
        Patient patient = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PATIENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String gender = resultSet.getString("gender");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    String address = resultSet.getString("address");
                    String medicalHistory = resultSet.getString("medicalHistory");
                    patient = new Patient(id, name, age, gender, phoneNumber, address, medicalHistory);
                }
            }
        }
        return patient;
    }

    @Override
    public void addPatient(Patient patient) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT)) {
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setInt(2, patient.getAge());
            preparedStatement.setString(3, patient.getGender());
            preparedStatement.setString(4, patient.getPhoneNumber());
            preparedStatement.setString(5, patient.getAddress());
            preparedStatement.setString(6, patient.getMedicalHistory());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updatePatient(Patient patient) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PATIENT)) {
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setInt(2, patient.getAge());
            preparedStatement.setString(3, patient.getGender());
            preparedStatement.setString(4, patient.getPhoneNumber());
            preparedStatement.setString(5, patient.getAddress());
            preparedStatement.setString(6, patient.getMedicalHistory());
            preparedStatement.setInt(7, patient.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deletePatient(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PATIENT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
}