package de.com.up42.codingchallenge.imagemetadata.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.com.up42.codingchallenge.imagemetadata.exceptions.ApplicationGenericServiceException;
import de.com.up42.codingchallenge.imagemetadata.models.generated.Feature;
import de.com.up42.codingchallenge.imagemetadata.repositories.ImageMetadataRepository;
import de.com.up42.codingchallenge.imagemetadata.services.ImageMetadataService;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.FeatureResponseDTO;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.ImageResponseDTO;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.SearchCriteriaRequestDTO;
import de.com.up42.codingchallenge.imagemetadata.services.mappers.FeatureMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ImageMetadataServiceImpl implements ImageMetadataService {

    private final ImageMetadataRepository imageMetadataRepository;
    private final FeatureMapper featureMapper;

    public ImageMetadataServiceImpl(ImageMetadataRepository imageMetadataRepository,
                                    FeatureMapper featureMapper) {

        this.imageMetadataRepository = imageMetadataRepository;
        this.featureMapper = featureMapper;
    }


    @Override
    public Optional<List<FeatureResponseDTO>> searchFeaturesByCriteria(SearchCriteriaRequestDTO<String> searchDTO) {
        List<FeatureResponseDTO> resultList;

        try {

            log.debug("Searching Features By Criteria :: {} ", searchDTO);

            resultList = this.imageMetadataRepository.findAllFeatures()
                                                     .map(featureMapper::toDTO)
                                                     .collect(Collectors.toList());

        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new ApplicationGenericServiceException(e);
        }

        return Optional.of(resultList);
    }

    @Override
    public Optional<FeatureResponseDTO> searchOneFeatureByCriteria(SearchCriteriaRequestDTO<String> searchDTO) {
        AtomicReference<Optional<FeatureResponseDTO>> optionalFeatureResponseDTO = new AtomicReference<>(Optional.empty());

        try {
            log.debug("Searching One Feature By Id  :: {} ", searchDTO.getId());

            this.imageMetadataRepository.findOneFeatureById(searchDTO.getId())
                                        .ifPresentOrElse(
                    theFeatureFound -> optionalFeatureResponseDTO.set(Optional.of(featureMapper.toDTO(theFeatureFound))),
                    () -> log.debug("Feature Not Found - Id :: {}", searchDTO.getId())
            );

        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new ApplicationGenericServiceException(e);
        }

        return optionalFeatureResponseDTO.get();

    }

    @Override
    public Optional<ImageResponseDTO> searchQuicklookImageByCriteria(SearchCriteriaRequestDTO<String> searchDTO) {
        Optional<ImageResponseDTO> optImageResponseDTO = Optional.empty();
        Optional<Feature> optFeature = Optional.empty();
        Feature featureFound;

        try {
            log.debug("Searching Quick Look Base64 Image by Feature Id # :: {} ", searchDTO.getId());

            optFeature = this.imageMetadataRepository.findOneFeatureById(searchDTO.getId());

            if (optFeature.isPresent()) {

                featureFound = optFeature.get();

                optImageResponseDTO = Optional.of(ImageResponseDTO.builder()
                                                                  .featureId(featureFound.getProperties().getId())
                                                                  .quickLookImageAsBase64(featureFound.getProperties().getQuicklook())
                                                                  .build()
                                                 );

            }

        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new ApplicationGenericServiceException(e);
        }

        return optImageResponseDTO;
    }

}
