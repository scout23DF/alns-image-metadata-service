package de.com.up42.codingchallenge.imagemetadata.services.dtos;

import de.com.up42.codingchallenge.imagemetadata.models.enums.AppServiceOperationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
public class SearchCriteriaRequestDTO<TId> implements IGenericBaseDTO {

    @NonNull
    private AppServiceOperationTypeEnum appServiceOperationType;

    private TId id;

}
