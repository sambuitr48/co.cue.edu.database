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
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("nombre"));
        product.setPrice(resultSet.getDouble("precio"));
        product.setRegistrationDate(
                resultSet.getObject("fecha_registro") != null ?
                        resultSet.getDate("fecha_registro")
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime() : null);
        return product;
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
