package de.com.up42.codingchallenge.imagemetadata.web.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class APIValidationErrorDTO extends AbstractAPISubErrorDTO {

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    APIValidationErrorDTO(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
