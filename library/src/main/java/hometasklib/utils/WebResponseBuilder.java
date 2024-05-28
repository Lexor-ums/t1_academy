package hometasklib.utils;

import hometasklib.dto.ErrorDto;
import hometasklib.dto.WebResponseDto;

/**
 * @author YStepanov
 */
public class WebResponseBuilder {
    public static WebResponseDto createSuccessDto (Object payload) {
        return new WebResponseDto(true, payload, null);
    }

    public static WebResponseDto createFailureDto (ErrorDto errorDto) {
        return new WebResponseDto(false, null, errorDto);
    }
}
