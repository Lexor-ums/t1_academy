package hometasklib.dto.response;

import hometasklib.enums.ProductType;
import lombok.Data;

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
