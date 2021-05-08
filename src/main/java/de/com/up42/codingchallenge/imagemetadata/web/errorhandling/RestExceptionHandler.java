package de.com.up42.codingchallenge.imagemetadata.web.errorhandling;

import de.com.up42.codingchallenge.imagemetadata.exceptions.ApplicationGenericServiceException;
import de.com.up42.codingchallenge.imagemetadata.exceptions.FeatureNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new APIMessageErrorDTO(BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(APIMessageErrorDTO apiError) {
        // return new ResponseEntity<>(apiError, apiError.getStatus());

        return ResponseEntity.status(apiError.getStatus().value())
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(apiError);
    }

    /**
     * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
     *
     * @param ex the ConstraintViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        APIMessageErrorDTO apiError = new APIMessageErrorDTO(BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getConstraintViolations());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({FeatureNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFound(FeatureNotFoundException ex) {
        APIMessageErrorDTO apiError = new APIMessageErrorDTO(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ApplicationGenericServiceException.class)
    protected ResponseEntity<Object> handleAppGenericException(ApplicationGenericServiceException ex) {
        APIMessageErrorDTO apiError = new APIMessageErrorDTO(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

}
