package productmodule.mapping;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import productmodule.enums.ProductType;
import productmodule.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YStepanov
 */
@Component
public class ProductMapper implements RowMapper<List<Product>> {
    private static final String ID_COLUMN = "id";
    private static final String ACCOUNT_COLUMN = "account";
    private static final String BALANCE_COLUMN = "balance";
    private static final String PRODUCT_TYPE_COLUMN = "product_type";
    @Override
    public List<Product> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<Product> productList = new ArrayList<>();
        while (rs.next()) {
            productList.add(Product.builder()
                    .id(rs.getLong(ID_COLUMN))
                    .accountNumber(rs.getString(ACCOUNT_COLUMN))
                    .balance(rs.getInt(BALANCE_COLUMN))
                    .productType(ProductType.valueOf(rs.getString(PRODUCT_TYPE_COLUMN)))
                    .build());
        }
        return productList;
    }
}
