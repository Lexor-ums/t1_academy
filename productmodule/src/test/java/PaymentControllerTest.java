import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import hometasklib.dto.request.PaymentRequest;
import hometasklib.dto.response.ProductResponseDto;
import hometasklib.enums.ProductType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import productmodule.exceptions.WrongBalanceException;
import productmodule.mapping.ProductResponseDtoMapper;
import productmodule.repository.ProductRepository;
import productmodule.service.PaymentService;
import productmodule.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

import static hometasklib.enums.ErrorCodes.WRONG_BALANCE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    ProductResponseDtoMapper productResponseDtoMapper;
    @Autowired
    PaymentService paymentService;
    @MockBean
    ProductRepository productRepository;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void processPaymentTest() throws Exception {
        PaymentRequest paymentRequest = new PaymentRequest(1L, 1L, BigDecimal.valueOf(235));
        Mockito.when(productRepository.processPayment(paymentRequest)).thenReturn(1);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(paymentRequest);
        mvc.perform(post("/payment", paymentRequest).contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void processPaymentWrongBalanceTest() throws Exception {
        PaymentRequest paymentRequest = new PaymentRequest(1L, 1L, BigDecimal.valueOf(235));
        Mockito.when(productRepository.processPayment(paymentRequest))
                .thenThrow(new WrongBalanceException(paymentRequest.userId(), paymentRequest.productId()));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(paymentRequest);
        mvc.perform(post("/payment", paymentRequest).contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error.code").value(WRONG_BALANCE.toString()));
    }

    private List<ProductResponseDto> getProducts() {
        ProductResponseDto productOne = new ProductResponseDto(
                1L,
                "1234",
                12,
                ProductType.ACCOUNT);
        ProductResponseDto productTwo = new ProductResponseDto(
                2L,
                "12343",
                12,
                ProductType.CARD);
        return List.of(productOne, productTwo);
    }

    private ProductResponseDto getProduct() {
        return new ProductResponseDto(
                1L,
                "1234",
                12,
                ProductType.ACCOUNT);
    }

}
