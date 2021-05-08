package de.com.up42.codingchallenge.imagemetadata.services;

import de.com.up42.codingchallenge.imagemetadata.services.dtos.FeatureResponseDTO;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.ImageResponseDTO;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.SearchCriteriaRequestDTO;

import java.util.List;
import java.util.Optional;

public interface ImageMetadataService {


    Optional<List<FeatureResponseDTO>> searchFeaturesByCriteria(SearchCriteriaRequestDTO searchDTO);

    Optional<ImageResponseDTO> searchQuicklookImageByCriteria(SearchCriteriaRequestDTO searchDTO);

}
