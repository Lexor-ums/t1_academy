package productmodule.controller;

import hometasklib.dto.WebResponseDto;
import hometasklib.dto.request.PaymentRequest;
import hometasklib.utils.WebResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import productmodule.service.PaymentService;

/**
 * @author YStepanov
 */
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    WebResponseDto processPayment(@RequestBody PaymentRequest paymentRequest) {
        paymentService.processPayment(paymentRequest);
        return WebResponseBuilder.createSuccessDto(null);
    }
}
