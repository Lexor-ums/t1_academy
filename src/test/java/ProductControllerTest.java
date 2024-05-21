import lesson_four.ProductType;
import lesson_four.controller.ProductController;
import lesson_four.dto.response.ProductResponseDto;
import lesson_four.mapping.ProductResponseDtoMapper;
import lesson_four.service.ProductService;
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
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.accountNumber").value("1234"))
                .andExpect(jsonPath("$.balance").value(12))
                .andExpect(jsonPath("$.productType").value(ProductType.ACCOUNT.toString()));
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
