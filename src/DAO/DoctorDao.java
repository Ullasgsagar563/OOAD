package src.DAO;

import src.model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DoctorDao {
    List<Doctor> getAllDoctors() throws SQLException;
    Doctor getDoctorById(int id) throws SQLException;
    void addDoctor(Doctor doctor) throws SQLException;
    void updateDoctor(Doctor doctor) throws SQLException;
    void deleteDoctor(int id) throws SQLException;

    class DoctorDaoImpl implements DoctorDao {
        private Connection connection;
        private static final String SELECT_ALL_DOCTORS = "SELECT * FROM doctors";
        private static final String SELECT_DOCTOR_BY_ID = "SELECT * FROM doctors WHERE id = ?";
        private static final String INSERT_DOCTOR = "INSERT INTO doctors (name, specialization, email,phoneNumber) VALUES (?, ?, ?, ?)";
        private static final String UPDATE_DOCTOR = "UPDATE doctors SET name = ?, specialization = ?, email = ?,phoneNumber = ? WHERE id = ?";
        private static final String DELETE_DOCTOR = "DELETE FROM doctors WHERE id = ?";

        public DoctorDaoImpl(Connection connection) {
            this.connection = connection;
        }

        @Override
        public List<Doctor> getAllDoctors() throws SQLException {
            List<Doctor> doctors = new ArrayList<>();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DOCTORS);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    //int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String specialization = resultSet.getString("specialization");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    String email = resultSet.getString("email");
                    Doctor doctor = new Doctor(name, specialization, email, phoneNumber);
                    doctors.add(doctor);
                }
            }
            return doctors;
        }

        @Override
        public Doctor getDoctorById(int id) throws SQLException {
            Doctor doctor = null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DOCTOR_BY_ID)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String name = resultSet.getString("name");
                        String specialization = resultSet.getString("specialization");
                        String phoneNumber = resultSet.getString("phoneNumber");
                        String email = resultSet.getString("email");
                        doctor = new Doctor(name, specialization, email, phoneNumber);
                    }
                }
            }
            return doctor;
        }

        @Override
        public void addDoctor(Doctor doctor) throws SQLException {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DOCTOR)) {
                preparedStatement.setString(1, doctor.getName());
                preparedStatement.setString(2, doctor.getSpecialization());
                preparedStatement.setString(3, doctor.getPhoneNumber());
                preparedStatement.setString(4, doctor.getEmail());
                preparedStatement.executeUpdate();
            }
        }

        @Override
        public void updateDoctor(Doctor doctor) throws SQLException {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DOCTOR)) {
                preparedStatement.setString(1, doctor.getName());
                preparedStatement.setString(2, doctor.getSpecialization());
                preparedStatement.setString(3, doctor.getPhoneNumber());
                preparedStatement.setString(4, doctor.getEmail());
                preparedStatement.setInt(5, doctor.getId());
                preparedStatement.executeUpdate();
            }
        }

        @Override
        public void deleteDoctor(int id) throws SQLException {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DOCTOR)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        }
    }
}
