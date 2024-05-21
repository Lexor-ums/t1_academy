package lesson_four.repository;

import lesson_four.model.Product;

import java.util.List;

/**
 * @author YStepanov
 */
public interface ProductRepository {
    Product addProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllUserProducts(Long userId);
}
