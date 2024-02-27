package models;

import lombok.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Product {
    private String id;
    private String name;
    private double price;
    private LocalDateTime registrationDate;
    /*/private Product createProduct(ResultSet resultSet) throws SQLException {
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
/*/
}

