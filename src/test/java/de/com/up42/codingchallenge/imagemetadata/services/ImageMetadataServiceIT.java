package de.com.up42.codingchallenge.imagemetadata.services;

import de.com.up42.codingchallenge.imagemetadata.AbstractBaseIntegrationTest;
import de.com.up42.codingchallenge.imagemetadata.models.enums.AppServiceOperationTypeEnum;
import de.com.up42.codingchallenge.imagemetadata.services.ImageMetadataService;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.FeatureResponseDTO;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.SearchCriteriaRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImageMetadataServiceIT extends AbstractBaseIntegrationTest {

    @Autowired
    private ImageMetadataService imageMetadataService;


    @Test
    public void testSearchAllFeatures() {
        SearchCriteriaRequestDTO searchDTO = SearchCriteriaRequestDTO.builder()
                                                                     .appServiceOperationType(AppServiceOperationTypeEnum.SEARCH_ALL_FEATURES)
                                                                     .build();

        Optional<List<FeatureResponseDTO>> optFeatureResponseDTOs = imageMetadataService.searchFeaturesByCriteria(searchDTO);

        assertTrue(optFeatureResponseDTOs.isPresent());

        List<FeatureResponseDTO> resultList = optFeatureResponseDTOs.get();

        assertEquals(14, resultList.size());

    }

}