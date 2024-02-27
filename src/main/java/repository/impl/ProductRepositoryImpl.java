package repository.impl;

import application.DatabaseConnection;
import models.Product;
import repository.Repository;
import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements Repository <Product>{
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }
    private Product createProduct(ResultSet resultSet) throws SQLException {
        Product producto = new Product();
        producto.setId(resultSet.getString("id"));
        producto.setName(resultSet.getString("name"));
        producto.setPrice(resultSet.getDouble("price"));
        producto.setRegistrationDate(
                resultSet.getObject("registrationDate") != null ?
                        resultSet.getDate("registrationDate")
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime() : null);
        return producto;
    }

    @Override
    public List<Product> list() {
        List<Product> productList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from productos")) {
            while (resultSet.next()) {
                Product product = createProduct(resultSet);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product byId(Long id) {
        Product product = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * from productos WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = createProduct(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(Long id) {

    }
}
