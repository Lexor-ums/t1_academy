package productmodule.repository;


import hometasklib.dto.request.PaymentRequest;
import productmodule.model.Product;

import java.sql.ResultSet;

/**
 * @author YStepanov
 */
public interface ProductRepository {
    Product addProduct(Product product);

    ResultSet getProductById(Long id);

    ResultSet getAllUserProducts(Long userId);

    Integer processPayment(PaymentRequest paymentRequest);
}
