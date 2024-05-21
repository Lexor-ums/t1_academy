package lesson_four.mapping;

import lesson_four.dto.response.ProductResponseDto;
import lesson_four.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author YStepanov
 */
@Mapper(componentModel = "spring")
public interface ProductResponseDtoMapper {
    ProductResponseDto toSingleDto(Product product);
    List<ProductResponseDto> toListDto(List<Product> product);
}
