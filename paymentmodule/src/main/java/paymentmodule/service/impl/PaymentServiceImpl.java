package paymentmodule.service.impl;

import hometasklib.dto.WebResponseDto;
import hometasklib.dto.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import paymentmodule.config.client.ProductServiceClient;
import paymentmodule.service.PaymentService;

/**
 * @author YStepanov
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final ProductServiceClient productServiceClient;

    @Override
    public WebResponseDto processPayment(PaymentRequest paymentRequest) {
        return productServiceClient.processPayment(paymentRequest);
    }
}
