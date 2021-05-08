package de.com.up42.codingchallenge.imagemetadata.exceptions;

public class FeatureNotFoundException extends ApplicationGenericServiceException {

    public FeatureNotFoundException(String id) {
        super("Feature Not Found - Id :: [" + id + "]");
    }
}
