package service;
import models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Service {
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
}
