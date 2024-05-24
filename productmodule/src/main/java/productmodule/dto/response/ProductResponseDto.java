package productmodule.dto.response;


import productmodule.enums.ProductType;

/**
 * @author YStepanov
 */
public record ProductResponseDto(
        Long id,
        String accountNumber,
        Integer balance,
        ProductType productType
) {
}
