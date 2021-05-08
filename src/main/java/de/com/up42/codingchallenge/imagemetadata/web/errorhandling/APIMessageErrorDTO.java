package de.com.up42.codingchallenge.imagemetadata.web.errorhandling;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.com.up42.codingchallenge.imagemetadata.services.dtos.IGenericBaseDTO;
import lombok.Data;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class APIMessageErrorDTO implements IGenericBaseDTO {

    private HttpStatus status;

    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonFormat(pattern = "dd.MM.yyyy' 'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp;

    private String message;
    private String debugMessage;
    private List<AbstractAPISubErrorDTO> subErrors;

    private APIMessageErrorDTO() {
        timestamp = LocalDateTime.now();
    }

    APIMessageErrorDTO(HttpStatus status) {
        this();
        this.status = status;
    }

    APIMessageErrorDTO(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    APIMessageErrorDTO(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }


    private void addSubError(AbstractAPISubErrorDTO subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new APIValidationErrorDTO(object, field, rejectedValue, message));
    }

    private void addValidationError(String object, String message) {
        addSubError(new APIValidationErrorDTO(object, message));
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    /**
     * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.
     *
     * @param cv the ConstraintViolation
     */
    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage());
    }

    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }


}
