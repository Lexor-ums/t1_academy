package productmodule.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import productmodule.enums.ProductType;

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
