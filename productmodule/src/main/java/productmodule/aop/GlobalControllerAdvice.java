package productmodule.aop;

import hometasklib.dto.ErrorDto;
import hometasklib.dto.WebResponseDto;
import hometasklib.utils.WebResponseBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import productmodule.exceptions.ProductNotFoundException;
import productmodule.exceptions.UserNotFoundException;
import productmodule.exceptions.WrongBalanceException;

import static hometasklib.enums.ErrorCodes.PRODUCT_NOT_FOUND;
import static hometasklib.enums.ErrorCodes.UNDEFINED_ERROR;
import static hometasklib.enums.ErrorCodes.USER_NOT_FOUND;
import static hometasklib.enums.ErrorCodes.WRONG_BALANCE;

/**
 * @author YStepanov
 */
@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    WebResponseDto processProductNotFoundException(ProductNotFoundException ex){
        return WebResponseBuilder.createFailureDto(new ErrorDto(PRODUCT_NOT_FOUND.toString(), ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    WebResponseDto processUserNotFoundException(UserNotFoundException ex){
        return WebResponseBuilder.createFailureDto(new ErrorDto(USER_NOT_FOUND.toString(), ex.getMessage()));
    }

    @ExceptionHandler(WrongBalanceException.class)
    WebResponseDto processWrongBalanceException(WrongBalanceException ex){
        return WebResponseBuilder.createFailureDto(new ErrorDto(WRONG_BALANCE.toString(), ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    WebResponseDto processException(RuntimeException ex){
        return WebResponseBuilder.createFailureDto(new ErrorDto(UNDEFINED_ERROR.toString(), ex.getMessage()));
    }
}
