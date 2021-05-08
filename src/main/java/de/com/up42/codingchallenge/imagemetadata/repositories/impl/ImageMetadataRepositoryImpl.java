package de.com.up42.codingchallenge.imagemetadata.repositories.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.com.up42.codingchallenge.imagemetadata.config.AppImgMetadataServiceProperties;
import de.com.up42.codingchallenge.imagemetadata.models.generated.Feature;
import de.com.up42.codingchallenge.imagemetadata.models.generated.ImageMetadata;
import de.com.up42.codingchallenge.imagemetadata.repositories.ImageMetadataRepository;
import de.com.up42.codingchallenge.imagemetadata.utils.FileIOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Slf4j
public class ImageMetadataRepositoryImpl implements ImageMetadataRepository {

    private static ImagesMetadataLoadedFromJSONFile imagesMetadataLoadedFromJSONFile;

    private final ObjectMapper jacksonObjectMapper;
    private final AppImgMetadataServiceProperties appImgMetadataServiceProperties;

    public ImageMetadataRepositoryImpl(ObjectMapper jacksonObjectMapper,
                                       AppImgMetadataServiceProperties appImgMetadataServiceProperties) {
        this.jacksonObjectMapper = jacksonObjectMapper;
        this.appImgMetadataServiceProperties = appImgMetadataServiceProperties;
    }

    @Override
    public Stream<Feature> findAllFeatures() throws JsonProcessingException {

        log.debug("Finding all Features.");

        return getOrLoadImageMetadataFromJSONFile().getAllFeaturesFlattenedList()
                                                   .stream()
                                                   .flatMap(oneFeature -> Stream.ofNullable(oneFeature));

    }

    @Override
    public Optional<Feature> findOneFeatureById(String id) throws JsonProcessingException {

        Optional<Feature> optResult = Optional.empty();

        List<Feature> resultList = findAllFeatures().filter(oneFeature -> oneFeature.getProperties().getId().equalsIgnoreCase(id))
                                                    .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(resultList)) {
            optResult = Optional.of(resultList.get(0));
        }

        return optResult;
    }

    private ImagesMetadataLoadedFromJSONFile getOrLoadImageMetadataFromJSONFile() throws JsonProcessingException {

        if (imagesMetadataLoadedFromJSONFile == null) {

            String jsonFileAsString = FileIOUtils.getResourceFileAsString(this.appImgMetadataServiceProperties.getSourceDataFilename());

            List<ImageMetadata> tmpList = List.of(this.jacksonObjectMapper.readValue(jsonFileAsString,
                                                                                     ImageMetadata[].class));

            imagesMetadataLoadedFromJSONFile = ImagesMetadataLoadedFromJSONFile.builder()
                                                                               .imagesMetadataLoadedList(tmpList)
                                                                               .build();

        }

        return imagesMetadataLoadedFromJSONFile;
    }

}
