package de.com.up42.codingchallenge.imagemetadata.services.dtos;

import de.com.up42.codingchallenge.imagemetadata.models.enums.AppServiceOperationTypeEnum;
import de.com.up42.codingchallenge.imagemetadata.models.enums.MessageProcessingTypeEnum;
import de.com.up42.codingchallenge.imagemetadata.models.enums.ServiceLayerStatusProcessingEnum;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class AcknowledgeResultDTO<T> implements IGenericBaseDTO {

    @NonNull
    private AppServiceOperationTypeEnum iamOperationType;
    private ServiceLayerStatusProcessingEnum serviceLayerStatusProcessing = ServiceLayerStatusProcessingEnum.SUCCESS;
    private HttpStatus httpStatusResponse;
    private String responseMessage;
    private List<MessageProcessingDTO> messagesProcessingDTOList = new ArrayList<>();
    private T payloadResponse;

    public void addErrorOccurrence(String pErrorMsg, Throwable pThrowableException) {
        this.messagesProcessingDTOList.add(new MessageProcessingDTO(MessageProcessingTypeEnum.ERROR, pErrorMsg, null, pThrowableException));
    }

    public void addInfoMessage(String pInfoMsg) {
        this.messagesProcessingDTOList.add(new MessageProcessingDTO(MessageProcessingTypeEnum.INFO, pInfoMsg, null, null));
    }
}
