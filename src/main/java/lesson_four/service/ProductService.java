package lesson_four.service;

import lesson_four.dto.response.ProductResponseDto;

import java.util.List;

/**
 * @author YStepanov
 */
public interface ProductService {
    ProductResponseDto getProduct(Long id);

    List<ProductResponseDto> getAllProductsByUser(Long userId);
}
