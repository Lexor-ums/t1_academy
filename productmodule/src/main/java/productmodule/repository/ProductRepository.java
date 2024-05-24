package productmodule.repository;


import productmodule.model.Product;

import java.util.List;

/**
 * @author YStepanov
 */
public interface ProductRepository {
    Product addProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllUserProducts(Long userId);
}
