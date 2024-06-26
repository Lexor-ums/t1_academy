package productmodule.service;


import hometasklib.dto.response.ProductResponseDto;

import java.util.List;

/**
 * @author YStepanov
 */
public interface ProductService {
    ProductResponseDto getProduct(Long id);

    List<ProductResponseDto> getAllProductsByUser(Long userId);
}
