package productmodule.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productmodule.model.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author YStepanov
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p FROM Product p WHERE id =:id ")
    Product getProductById(Long id);

    @Query(value = "SELECT p FROM Product p")
    List<Product> getAllUserProducts(Long userId);

    @Modifying
    @Query(value = "UPDATE Product p SET p.balance = p.balance - :amount WHERE p.user.id = :userId AND p.id = :productId")
    Integer processPayment(Long userId, Long productId, BigDecimal amount);
}
