import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import hometasklib.dto.WebResponseDto;
import hometasklib.dto.request.PaymentRequest;
import hometasklib.utils.WebResponseBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import paymentmodule.service.PaymentService;

import java.math.BigDecimal;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author YStepanov
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
public class PaymentControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    MockMvc mvc;
    @Autowired
    PaymentService paymentService;
    @MockBean
    RestTemplate productServiceRestTemplate;


    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void processPaymentTest() throws Exception {
        PaymentRequest paymentRequest = new PaymentRequest(1L, 1L, BigDecimal.valueOf(235));
        HttpEntity<PaymentRequest> entity = new HttpEntity<>(paymentRequest);

        Mockito.when(productServiceRestTemplate.postForObject(anyString(), eq(entity), eq(WebResponseDto.class)))
                .thenReturn(WebResponseBuilder.createSuccessDto(null));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(paymentRequest);
        mvc.perform(post("/payment", paymentRequest).contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(jsonPath("$.success").value(true));
    }
}
