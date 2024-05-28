package productmodule.controller;

import hometasklib.dto.WebResponseDto;
import hometasklib.dto.response.ProductResponseDto;
import hometasklib.utils.WebResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import productmodule.service.ProductService;

import java.util.List;

/**
 * @author YStepanov
 */
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto getProductById(@PathVariable Long id) {
        return WebResponseBuilder.createSuccessDto(productService.getProduct(id));
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponseDto getProductByUserId(@PathVariable Long id) {
        return WebResponseBuilder.createSuccessDto(productService.getAllProductsByUser(id));
    }
}
