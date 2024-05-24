package productmodule.service.impl;

import productmodule.dto.response.ProductResponseDto;
import productmodule.mapping.ProductResponseDtoMapper;
import productmodule.repository.ProductRepository;
import productmodule.service.ProductService;
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
