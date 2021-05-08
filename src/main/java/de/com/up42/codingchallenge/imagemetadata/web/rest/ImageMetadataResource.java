package de.com.up42.codingchallenge.imagemetadata.web.rest;

import de.com.up42.codingchallenge.imagemetadata.models.enums.AppServiceOperationTypeEnum;
import de.com.up42.codingchallenge.imagemetadata.services.ImageMetadataService;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.FeatureResponseDTO;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.ImageResponseDTO;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.SearchCriteriaRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/features")
@Slf4j
public class ImageMetadataResource {

    private final ImageMetadataService imageMetadataService;

    public ImageMetadataResource(ImageMetadataService pImageMetadataService) {
        this.imageMetadataService = pImageMetadataService;
    }

    @GetMapping
    public ResponseEntity<List<FeatureResponseDTO>> listAllFeatures() {

        log.debug("Listing All FeatureResponseDTO's. ");

        SearchCriteriaRequestDTO searchDTO = SearchCriteriaRequestDTO.builder()
                                                                     .appServiceOperationType(AppServiceOperationTypeEnum.SEARCH_ALL_FEATURES)
                                                                     .build();

        Optional<List<FeatureResponseDTO>> optFeaturesFoundList = this.imageMetadataService.searchFeaturesByCriteria(searchDTO);

        return ResponseEntity.of(optFeaturesFoundList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<FeatureResponseDTO> getFeatureById(@PathVariable("id") String pId) {

        SearchCriteriaRequestDTO searchDTO = SearchCriteriaRequestDTO.builder()
                                                                     .appServiceOperationType(AppServiceOperationTypeEnum.SEARCH_FEATURE_BY_ID)
                                                                     .id(pId)
                                                                     .build();

        FeatureResponseDTO featureFound = this.imageMetadataService.searchFeaturesByCriteria(searchDTO)
                                                                   .get()
                                                                   .get(0);

        return ResponseEntity.ok(featureFound);

    }

    @GetMapping("/{id}/quicklook")
    public ResponseEntity<ImageResponseDTO> getQuicklookImageAsPNG(@PathVariable("id") String pId) {

        SearchCriteriaRequestDTO searchDTO = SearchCriteriaRequestDTO.builder()
                                                                    .appServiceOperationType(AppServiceOperationTypeEnum.GET_IMAGE_AS_PNG)
                                                                    .id(pId)
                                                                    .build();

        Optional<ImageResponseDTO> optImageFound = this.imageMetadataService.searchQuicklookImageByCriteria(searchDTO);

        return ResponseEntity.of(optImageFound);

    }

}
