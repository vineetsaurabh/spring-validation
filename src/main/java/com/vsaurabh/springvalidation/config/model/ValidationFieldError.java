package com.vsaurabh.springvalidation.config.model;

/**
 * A class to hold Field error after validation
 *
 * @author Vineet Saurabh
 *
 */
public class ValidationFieldError {
    private String field;
    private String defaultMessage;
    private Object invalidValue;

    public ValidationFieldError(String field, String defaultMessage, Object invalidValue) {
        this.field = field;
        this.defaultMessage = defaultMessage;
        this.invalidValue = invalidValue;
    }

    public String getField() {
        return field;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public Object getInvalidValue() {
        return invalidValue;
    }

    @Override
    public String toString() {
        return "ValidationFieldError [field=" + field + ", defaultMessage=" + defaultMessage
                + ", invalidValue=" + invalidValue + "]";
    }
}
