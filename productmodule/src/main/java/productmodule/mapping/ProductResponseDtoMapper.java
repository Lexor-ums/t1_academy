package productmodule.mapping;

import org.mapstruct.Mapper;
import productmodule.dto.response.ProductResponseDto;
import productmodule.model.Product;

import java.util.List;

/**
 * @author YStepanov
 */
@Mapper(componentModel = "spring")
public interface ProductResponseDtoMapper {
    ProductResponseDto toSingleDto(Product product);
    List<ProductResponseDto> toListDto(List<Product> product);
}
