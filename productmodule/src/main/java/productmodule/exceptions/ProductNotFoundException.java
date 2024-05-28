package productmodule.exceptions;

/**
 * @author YStepanov
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super(String.format("Продукт с id = %s не найден",id));
    }
}
