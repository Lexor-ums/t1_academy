package productmodule.mapping;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import productmodule.dto.response.ProductResponseDto;
import productmodule.enums.ProductType;
import productmodule.model.Product;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-24T15:34:01+0400",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class ProductResponseDtoMapperImpl implements ProductResponseDtoMapper {

    @Override
    public ProductResponseDto toSingleDto(Product product) {
        if ( product == null ) {
            return null;
        }

        Long id = null;
        String accountNumber = null;
        Integer balance = null;
        ProductType productType = null;

        id = product.getId();
        accountNumber = product.getAccountNumber();
        balance = product.getBalance();
        productType = product.getProductType();

        ProductResponseDto productResponseDto = new ProductResponseDto( id, accountNumber, balance, productType );

        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> toListDto(List<Product> product) {
        if ( product == null ) {
            return null;
        }

        List<ProductResponseDto> list = new ArrayList<ProductResponseDto>( product.size() );
        for ( Product product1 : product ) {
            list.add( toSingleDto( product1 ) );
        }

        return list;
    }
}
