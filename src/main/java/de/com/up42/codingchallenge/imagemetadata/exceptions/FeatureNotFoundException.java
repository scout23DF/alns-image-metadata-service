package de.com.up42.codingchallenge.imagemetadata.exceptions;

public class FeatureNotFoundException extends RuntimeException {

    public FeatureNotFoundException(Long id) {
        super("Could not find staff member with ID " + id);
    }
}
