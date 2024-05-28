package productmodule.repository.impl;

import hometasklib.dto.request.PaymentRequest;
import productmodule.exceptions.WrongBalanceException;
import productmodule.model.Product;
import productmodule.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author YStepanov
 */
@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final DataSource dataSource;

    private static final String ADD_PRODUCT_QUERY = "INSERT INTO products VALUES (?, ?, ?, ?)";
    private static final String GET_PRODUCT_BY_ID_QUERY = "SELECT * FROM products WHERE id = ?";
    private static final String GET_ALL_PRODUCTS_BY_USER_QUERY = "SELECT * FROM products WHERE user_id = ?";
    private static final String PROCESS_PAYMENT =
            "UPDATE products SET balance = balance - ? WHERE user_id = ? AND id = ? RETURNING *;";
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
    public ResultSet getProductById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_ID_QUERY);
            statement.setLong(1, id);
            return statement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getAllUserProducts(Long userId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_PRODUCTS_BY_USER_QUERY);
            statement.setLong(1, userId);
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer processPayment(PaymentRequest paymentRequest) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(PROCESS_PAYMENT);
            statement.setBigDecimal(1, paymentRequest.amount());
            statement.setLong(2, paymentRequest.userId());
            statement.setLong(3, paymentRequest.productId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new WrongBalanceException(paymentRequest.userId(), paymentRequest.productId());
        }
    }
}
