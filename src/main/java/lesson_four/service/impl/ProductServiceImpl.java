package lesson_four.service.impl;

import lesson_four.dto.response.ProductResponseDto;
import lesson_four.mapping.ProductResponseDtoMapper;
import lesson_four.repository.ProductRepository;
import lesson_four.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YStepanov
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductResponseDtoMapper productResponseDtoMapper;

    @Override
    public ProductResponseDto getProduct(Long id) {
        return productResponseDtoMapper.toSingleDto(productRepository.getProductById(id));
    }

    @Override
    public List<ProductResponseDto> getAllProductsByUser(Long userId) {
        return productResponseDtoMapper.toListDto(productRepository.getAllUserProducts(userId));
    }
}
