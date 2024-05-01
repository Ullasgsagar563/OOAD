package src.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.model.Service;

public interface ServiceDao {
    List<Service> getAllServices() throws SQLException;
    Service getServiceById(int id) throws SQLException;
    void addService(Service service) throws SQLException;
    void updateService(Service service) throws SQLException;
    void deleteService(int id) throws SQLException;


class ServiceDaoImpl implements ServiceDao {
    private Connection connection;
    private static final String SELECT_ALL_SERVICES = "SELECT * FROM services";
    private static final String SELECT_SERVICE_BY_ID = "SELECT * FROM services WHERE id = ?";
    private static final String INSERT_SERVICE = "INSERT INTO services (name, price) VALUES (?, ?)";
    private static final String UPDATE_SERVICE = "UPDATE services SET name = ?, price = ? WHERE id = ?";
    private static final String DELETE_SERVICE = "DELETE FROM services WHERE id = ?";

    public ServiceDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Service> getAllServices() throws SQLException {
        List<Service> services = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SERVICES);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                //int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Service service = new Service(name, price);
                services.add(service);
            }
        }
        return services;
    }

    @Override
    public Service getServiceById(int id) throws SQLException {
        Service service = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SERVICE_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    service = new Service( name, price);
                }
            }
        }
        return service;
    }

    @Override
    public void addService(Service service) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SERVICE)) {
            preparedStatement.setString(1, service.getName());
            preparedStatement.setDouble(2, service.getPrice());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateService(Service service) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERVICE)) {
            preparedStatement.setString(1, service.getName());
            preparedStatement.setDouble(2, service.getPrice());
            preparedStatement.setInt(3, service.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteService(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SERVICE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
}