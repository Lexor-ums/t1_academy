package paymentmodule.config.client;

import hometasklib.dto.WebResponseDto;
import hometasklib.dto.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import paymentmodule.config.properties.ProductServiceProperties;

/**
 * @author YStepanov
 */
@Service
@RequiredArgsConstructor
public class ProductServiceClient {
    private final RestTemplate productServiceRestTemplate;
    private final ProductServiceProperties properties;

    public WebResponseDto processPayment(PaymentRequest paymentRequest) {
        HttpEntity<PaymentRequest> entity = new HttpEntity<>(paymentRequest);
        return productServiceRestTemplate.postForObject(properties.paymentPath(),
                entity, WebResponseDto.class);
    }
}
