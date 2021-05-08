package de.com.up42.codingchallenge.imagemetadata.web.rest;

import de.com.up42.codingchallenge.imagemetadata.config.AppImgMetadataServiceProperties;
import de.com.up42.codingchallenge.imagemetadata.models.enums.AppServiceOperationTypeEnum;
import de.com.up42.codingchallenge.imagemetadata.services.ImageMetadataService;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.FeatureResponseDTO;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.ImageResponseDTO;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.SearchCriteriaRequestDTO;
import de.com.up42.codingchallenge.imagemetadata.utils.ImageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/features")
@Slf4j
public class ImageMetadataResource {

    private final AppImgMetadataServiceProperties appImgMetadataServiceProperties;
    private final ImageMetadataService imageMetadataService;

    public ImageMetadataResource(AppImgMetadataServiceProperties appImgMetadataServiceProperties,
                                 ImageMetadataService pImageMetadataService) {
        this.appImgMetadataServiceProperties = appImgMetadataServiceProperties;
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

        Optional<FeatureResponseDTO> optFeatureFound = this.imageMetadataService.searchOneFeatureByCriteria(searchDTO);

        return ResponseEntity.of(optFeatureFound);

    }

    @GetMapping(value = "/{id}/quicklook", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> showQuicklookImageAsPNG(@PathVariable("id") String pId) throws IOException {
        Optional<byte[]> optImageAsByteArray = Optional.empty();
        Optional<ImageResponseDTO> optImageFound = Optional.empty();

        SearchCriteriaRequestDTO searchDTO = SearchCriteriaRequestDTO.builder()
                                                                    .appServiceOperationType(AppServiceOperationTypeEnum.GET_IMAGE_AS_PNG)
                                                                    .id(pId)
                                                                    .build();

        optImageFound = this.imageMetadataService.searchQuicklookImageByCriteria(searchDTO);

        if (optImageFound.isPresent()) {

            if (appImgMetadataServiceProperties.isGenerateEasterEggEnabled()) {
                optImageAsByteArray = Optional.ofNullable(ImageUtils.generateEasterEgg(optImageFound.get().getQuickLookImageAsByteArray()));
            } else {
                optImageAsByteArray = Optional.ofNullable(optImageFound.get().getQuickLookImageAsByteArray());
            }
        }

        return ResponseEntity.of(optImageAsByteArray);

    }

}
