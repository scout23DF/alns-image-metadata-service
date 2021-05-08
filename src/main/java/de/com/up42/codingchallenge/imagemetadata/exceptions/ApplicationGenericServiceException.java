package de.com.up42.codingchallenge.imagemetadata.exceptions;

public class ApplicationGenericServiceException extends RuntimeException {

    public ApplicationGenericServiceException(Throwable exception) {
        super(exception);
    }
}
