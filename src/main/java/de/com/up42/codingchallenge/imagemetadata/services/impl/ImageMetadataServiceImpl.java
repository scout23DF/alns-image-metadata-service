package de.com.up42.codingchallenge.imagemetadata.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.com.up42.codingchallenge.imagemetadata.config.AppImgMetadataServiceProperties;
import de.com.up42.codingchallenge.imagemetadata.exceptions.ApplicationGenericServiceException;
import de.com.up42.codingchallenge.imagemetadata.models.enums.AppServiceOperationTypeEnum;
import de.com.up42.codingchallenge.imagemetadata.repositories.ImageMetadataRepository;
import de.com.up42.codingchallenge.imagemetadata.services.ImageMetadataService;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.FeatureResponseDTO;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.ImageResponseDTO;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.SearchCriteriaRequestDTO;
import de.com.up42.codingchallenge.imagemetadata.services.mappers.FeatureMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ImageMetadataServiceImpl implements ImageMetadataService {

    private final AppImgMetadataServiceProperties appImgMetadataServiceProperties;
    private final ImageMetadataRepository imageMetadataRepository;
    private final FeatureMapper featureMapper;

    public ImageMetadataServiceImpl(AppImgMetadataServiceProperties appImgMetadataServiceProperties,
                                    ImageMetadataRepository imageMetadataRepository,
                                    FeatureMapper featureMapper) {

        this.appImgMetadataServiceProperties = appImgMetadataServiceProperties;
        this.imageMetadataRepository = imageMetadataRepository;
        this.featureMapper = featureMapper;
    }


    @Override
    public Optional<List<FeatureResponseDTO>> searchFeaturesByCriteria(SearchCriteriaRequestDTO searchDTO) {
        List<FeatureResponseDTO> resultList = new ArrayList<>();

        try {

            log.debug("Searching Features By Criteria :: {} ", searchDTO);

            if (searchDTO.getAppServiceOperationType().equals(AppServiceOperationTypeEnum.SEARCH_ALL_FEATURES)) {

                resultList = this.imageMetadataRepository.findAllFeatures()
                                                         .map(featureMapper::toDTO)
                                                         .collect(Collectors.toList());
            }

        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new ApplicationGenericServiceException(e);
        }


        return Optional.of(resultList);
    }

    @Override
    public Optional<ImageResponseDTO> searchQuicklookImageByCriteria(SearchCriteriaRequestDTO searchDTO) {
        return Optional.empty();
    }

}
