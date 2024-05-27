package hometasklib.dto;

import lombok.Data;

/**
 * @author YStepanov
 */
public record ErrorDto(
        String code,
        String message
){
}
