package com.vsaurabh.springvalidation.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vsaurabh.springvalidation.config.model.ValidationErrorResponse;
import com.vsaurabh.springvalidation.config.model.ValidationFieldError;
import com.vsaurabh.springvalidation.config.model.ValidationGlobalError;

/**
 * This class intercept all MethodArgumentNotValidException and customize error response
 * 
 * @author Vineet Saurabh
 *
 */
@ControllerAdvice
public class ValidationExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ValidationExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		logger.error("Validation exception: " + ex.getMessage(), ex);

        BindingResult bindingResult = ex.getBindingResult();
        List<ValidationGlobalError> globalErrors = null;
        List<ValidationFieldError> fieldErrors = null;

        if (bindingResult.hasGlobalErrors()) {
            globalErrors = new ArrayList<>();
            for (ObjectError error : bindingResult.getGlobalErrors()) {
                String objectName = error.getObjectName();
                String message = error.getDefaultMessage();
                globalErrors.add(new ValidationGlobalError(objectName, message));
            }
        }
        if (bindingResult.hasFieldErrors()) {
            fieldErrors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                String field = error.getField();
                String message = error.getDefaultMessage();
                Object value = error.getRejectedValue();
                fieldErrors.add(new ValidationFieldError(field, message, value));
            }
        }
        ValidationErrorResponse validationErrorResponse =
                new ValidationErrorResponse(globalErrors, fieldErrors);
        return ResponseEntity.badRequest()
                             .body(validationErrorResponse);
	}
}
