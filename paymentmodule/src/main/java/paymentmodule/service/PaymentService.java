package paymentmodule.service;

import hometasklib.dto.WebResponseDto;
import hometasklib.dto.request.PaymentRequest;

/**
 * @author YStepanov
 */
public interface PaymentService {
    WebResponseDto processPayment(PaymentRequest paymentRequest);
}
