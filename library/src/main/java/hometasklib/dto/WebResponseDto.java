package hometasklib.dto;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author YStepanov
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record WebResponseDto (
        boolean success,
        Object payload,
        ErrorDto error
){
}
