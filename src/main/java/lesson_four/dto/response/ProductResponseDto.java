package lesson_four.dto.response;

import lesson_four.ProductType;
import lombok.Builder;
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
