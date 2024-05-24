package productmodule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import productmodule.dto.response.ProductResponseDto;
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
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponseDto> getProductByUserId(@PathVariable Long id) {
        return productService.getAllProductsByUser(id);
    }
}