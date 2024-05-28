package productmodule.mapping;

import hometasklib.dto.response.ProductResponseDto;
import org.mapstruct.Mapper;
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
