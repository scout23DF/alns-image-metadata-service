package de.com.up42.codingchallenge.imagemetadata.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.com.up42.codingchallenge.imagemetadata.models.generated.Feature;

import java.util.Optional;
import java.util.stream.Stream;

public interface ImageMetadataRepository {

    Stream<Feature> findAllFeatures() throws JsonProcessingException;

    Optional<Feature> findOneFeatureById(String id) throws JsonProcessingException;

}
