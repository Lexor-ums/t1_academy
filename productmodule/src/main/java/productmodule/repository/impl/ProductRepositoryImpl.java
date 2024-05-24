package productmodule.repository.impl;

import productmodule.mapping.ProductMapper;
import productmodule.model.Product;
import productmodule.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author YStepanov
 */
@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final DataSource dataSource;
    private final ProductMapper productMapper;

    private static final String ADD_PRODUCT_QUERY = "INSERT INTO products VALUES (?, ?, ?, ?)";
    private static final String GET_PRODUCT_BY_ID_QUERY = "SELECT * FROM products WHERE id = ?";
    private static final String GET_ALL_PRODUCTS_BY_USER_QUERY = "SELECT * FROM products WHERE user_id = ?";
    @Override
    public Product addProduct(Product product) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_QUERY);
            statement.setLong(1, product.getId());
            statement.setString(2, product.getAccountNumber());
            statement.setInt(3, product.getBalance());
            statement.setString(4, product.getProductType().toString());
            statement.setLong(5, product.getUserId());
            statement.executeUpdate();
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProductById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet res = statement.executeQuery();
            List<Product> userList = productMapper.mapRow(res, 0);
            if (userList.size() > 1) {
                throw new RuntimeException("Request return more then one row");
            }
            return userList.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAllUserProducts(Long userId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_PRODUCTS_BY_USER_QUERY);
            statement.setLong(1, userId);
            ResultSet res = statement.executeQuery();
            return productMapper.mapRow(res, 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
