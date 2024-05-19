package lesson_four.model;

import lesson_four.ProductType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author YStepanov
 */
@Getter
@Builder
@EqualsAndHashCode
public class Product {
    private Long id;
    private String accountNumber;
    private Integer balance;
    private ProductType productType;
    private Long userId;
}
