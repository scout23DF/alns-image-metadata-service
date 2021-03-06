package de.com.up42.codingchallenge.imagemetadata.services.dtos;

import de.com.up42.codingchallenge.imagemetadata.models.enums.MessageProcessingTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageProcessingDTO implements IGenericBaseDTO {

    private MessageProcessingTypeEnum messageProcessingType;
    private String title;
    private String description;
    private Throwable exceptionOccurred;

}
