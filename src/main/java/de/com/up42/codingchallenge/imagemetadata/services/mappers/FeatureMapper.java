package de.com.up42.codingchallenge.imagemetadata.services.mappers;

import de.com.up42.codingchallenge.imagemetadata.models.generated.Feature;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.FeatureResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Feature} and its DTO {@link FeatureResponseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FeatureMapper extends BaseMapper<FeatureResponseDTO, Feature> {

    @Mapping(source = "properties.id", target = "id")
    @Mapping(source = "properties.timestamp", target = "timestamp")
    @Mapping(source = "properties.acquisition.beginViewingDate", target = "beginViewingDate")
    @Mapping(source = "properties.acquisition.endViewingDate", target = "endViewingDate")
    @Mapping(source = "properties.acquisition.missionName", target = "missionName")
    FeatureResponseDTO toDTO(Feature featureEntity);

}
