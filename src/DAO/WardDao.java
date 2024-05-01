package src.DAO;

import src.model.Ward;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface WardDao {
    List<Ward> getAllWards() throws SQLException;
    Ward getWardById(int id) throws SQLException;
    void addWard(Ward ward) throws SQLException;
    void updateWard(Ward ward) throws SQLException;
    void deleteWard(int id) throws SQLException;
    //void assignWardToPatient(String patientName, String wardName) throws SQLException;

 class WardDaoImpl implements WardDao {
    private Connection connection;
    private static final String SELECT_ALL_WARDS = "SELECT * FROM wards";
    private static final String SELECT_WARD_BY_ID = "SELECT * FROM wards WHERE id = ?";
    private static final String INSERT_WARD = "INSERT INTO wards (name, capacity, availableBeds) VALUES (?, ?, ?)";
    private static final String UPDATE_WARD = "UPDATE wards SET name = ?, capacity = ?, availableBeds = ? WHERE id = ?";
    private static final String DELETE_WARD = "DELETE FROM wards WHERE id = ?";

    public WardDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Ward> getAllWards() throws SQLException {
        List<Ward> wards = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_WARDS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int capacity = resultSet.getInt("capacity");
                int availableBeds = resultSet.getInt("availableBeds");
                Ward ward = new Ward(id, name, capacity, availableBeds);
                wards.add(ward);
            }
        }
        return wards;
    }

    @Override
    public Ward getWardById(int id) throws SQLException {
        Ward ward = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WARD_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int capacity = resultSet.getInt("capacity");
                    int availableBeds = resultSet.getInt("availableBeds");
                    ward = new Ward(id, name, capacity, availableBeds);
                }
            }
        }
        return ward;
    }

    @Override
    public void addWard(Ward ward) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WARD)) {
            preparedStatement.setString(1, ward.getName());
            preparedStatement.setInt(2, ward.getCapacity());
            preparedStatement.setInt(3, ward.getAvailableBeds());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateWard(Ward ward) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_WARD)) {
            preparedStatement.setString(1, ward.getName());
            preparedStatement.setInt(2, ward.getCapacity());
            preparedStatement.setInt(3, ward.getAvailableBeds());
            preparedStatement.setInt(4, ward.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteWard(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_WARD)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }

    }
    //@Override
// public void assignWardToPatient(String patientName, String wardName) throws SQLException {
//     // Retrieve the ward ID by name
//     String selectWardIdQuery = "SELECT id FROM wards WHERE name = ?";
//     int wardId;
//     try (PreparedStatement selectStatement = connection.prepareStatement(selectWardIdQuery)) {
//         selectStatement.setString(1, wardName);
//         try (ResultSet resultSet = selectStatement.executeQuery()) {
//             if (resultSet.next()) {
//                 wardId = resultSet.getInt("id");
//             } else {
//                 throw new SQLException("Ward not found with name: " + wardName);
//             }
//         }
//     }

//     // Update patient record with assigned ward ID
//     String updatePatientQuery = "UPDATE patients SET ward_id = ? WHERE name = ?";
//     try (PreparedStatement updateStatement = connection.prepareStatement(updatePatientQuery)) {
//         updateStatement.setInt(1, wardId);
//         updateStatement.setString(2, patientName);
//         int rowsAffected = updateStatement.executeUpdate();
//         if (rowsAffected == 0) {
//             throw new SQLException("Patient not found with name: " + patientName);
//         }
//     }
// }

}
}