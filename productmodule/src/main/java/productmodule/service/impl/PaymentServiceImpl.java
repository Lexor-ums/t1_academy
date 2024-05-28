package productmodule.service.impl;

import hometasklib.dto.request.PaymentRequest;
import hometasklib.dto.response.ProductResponseDto;
import hometasklib.enums.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import productmodule.exceptions.UserNotFoundException;
import productmodule.repository.ProductRepository;
import productmodule.service.PaymentService;

import java.util.List;

/**
 * @author YStepanov
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final ProductRepository productRepository;
    @Override
    public void processPayment(PaymentRequest paymentRequest) {
        Integer rowsModified = productRepository.processPayment(paymentRequest.userId(), paymentRequest.productId(), paymentRequest.amount());
        if (rowsModified == 0) {
            throw new UserNotFoundException(paymentRequest.userId());
        }
    }
}
