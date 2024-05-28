package productmodule.service;

import hometasklib.dto.request.PaymentRequest;

/**
 * @author YStepanov
 */
public interface PaymentService {
    void processPayment(PaymentRequest paymentRequest);
}
