package productmodule.service.impl;

import hometasklib.dto.response.ProductResponseDto;
import productmodule.exceptions.ProductNotFoundException;
import productmodule.mapping.ProductMapper;
import productmodule.mapping.ProductResponseDtoMapper;
import productmodule.model.Product;
import productmodule.repository.ProductRepository;
import productmodule.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author YStepanov
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductResponseDtoMapper productResponseDtoMapper;
    private final ProductMapper productMapper;
    @Override
    public ProductResponseDto getProduct(Long id) {
        ResultSet res = productRepository.getProductById(id);
        List<Product> userList;
        try {
            userList = productMapper.mapRow(res, 0);
        } catch (SQLException e) {
            throw new RuntimeException("Request return more then one row");
        }
        if (userList.size() == 1) {
            return productResponseDtoMapper.toSingleDto(userList.get(0));
        }
        else {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public List<ProductResponseDto> getAllProductsByUser(Long userId) {
        ResultSet res = productRepository.getAllUserProducts(userId);
        try {
            List<Product> userList = productMapper.mapRow(res, 0);
            return productResponseDtoMapper.toListDto(userList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
