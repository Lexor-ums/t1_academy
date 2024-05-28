package productmodule.exceptions;

/**
 * @author YStepanov
 */
public class WrongBalanceException extends RuntimeException{
    public WrongBalanceException(Long userId, Long productId) {
        super(String.format("У пользователя с id = %s недостаточно средств на карте %s", userId, productId));
    }
}
