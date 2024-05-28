
import hometasklib.dto.response.ProductResponseDto;
import hometasklib.enums.ProductType;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.hamcrest.MatcherGenericTypeExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import productmodule.mapping.ProductResponseDtoMapper;
import productmodule.service.ProductService;


import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author YStepanov
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
public class ProductControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    MockMvc mvc;
    @Autowired
    ProductResponseDtoMapper productResponseDtoMapper;
    @MockBean
    ProductService productService;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @SneakyThrows
    @Test
    public void testGetProductById() {
        Mockito.when(productService.getProduct(1L)).thenReturn(getProduct());
        mvc.perform(get("/product/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.payload.accountNumber").value("1234"))
                .andExpect(jsonPath("$.payload.balance").value(12))
                .andExpect(jsonPath("$.payload.productType").value(ProductType.ACCOUNT.toString()));
    }

    @SneakyThrows
    @Test
    public void testGetProductByUserId() {
        Mockito.when(productService.getAllProductsByUser(1L)).thenReturn(getProducts());
        MatcherGenericTypeExtractor fp;
        mvc.perform(get("/product/user/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
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
