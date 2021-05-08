package de.com.up42.codingchallenge.imagemetadata.services;

import de.com.up42.codingchallenge.imagemetadata.services.dtos.FeatureResponseDTO;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.ImageResponseDTO;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.SearchCriteriaRequestDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface ImageMetadataService {

    Optional<List<FeatureResponseDTO>> searchFeaturesByCriteria(@Valid @NotNull SearchCriteriaRequestDTO searchDTO);

    Optional<ImageResponseDTO> searchQuicklookImageByCriteria(@Valid @NotNull SearchCriteriaRequestDTO searchDTO);

}
