package com.vsaurabh.springvalidation.config.model;

/**
 * A class to hold Global error after validation
 *
 * @author Vineet Saurabh
 *
 */
public class ValidationGlobalError {
    private String objectName;
    private String defaultMessage;

    public ValidationGlobalError(String objectName, String defaultMessage) {
        this.objectName = objectName;
        this.defaultMessage = defaultMessage;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    @Override
    public String toString() {
        return "ValidationGlobalError [objectName=" + objectName + ", defaultMessage="
                + defaultMessage + "]";
    }
}
