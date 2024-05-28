package hometasklib.dto.request;

import java.math.BigDecimal;

/**
 * @author YStepanov
 */
public record PaymentRequest(
        Long userId,
        Long productId,
        BigDecimal amount
) {
}
