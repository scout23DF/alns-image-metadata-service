package de.com.up42.codingchallenge.imagemetadata.services.impl;

import de.com.up42.codingchallenge.imagemetadata.config.AppImgMetadataServiceProperties;
import de.com.up42.codingchallenge.imagemetadata.repositories.ImageMetadataRepository;
import de.com.up42.codingchallenge.imagemetadata.services.ImageMetadataService;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.FeatureResponseDTO;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.ImageResponseDTO;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.SearchCriteriaRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ImageMetadataServiceImpl implements ImageMetadataService {

    private final AppImgMetadataServiceProperties appImgMetadataServiceProperties;
    private final ImageMetadataRepository imageMetadataRepository;

    public ImageMetadataServiceImpl(AppImgMetadataServiceProperties appImgMetadataServiceProperties,
                                    ImageMetadataRepository imageMetadataRepository) {

        this.appImgMetadataServiceProperties = appImgMetadataServiceProperties;
        this.imageMetadataRepository = imageMetadataRepository;
    }


    @Override
    public Optional<List<FeatureResponseDTO>> searchFeaturesByCriteria(SearchCriteriaRequestDTO searchDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<ImageResponseDTO> searchQuicklookImageByCriteria(SearchCriteriaRequestDTO searchDTO) {
        return Optional.empty();
    }

}
