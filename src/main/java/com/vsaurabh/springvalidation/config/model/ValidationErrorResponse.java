package com.vsaurabh.springvalidation.config.model;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * A class to hold response error after validation
 *
 * @author Vineet Saurabh
 *
 */
public class ValidationErrorResponse {

    private int code = HttpServletResponse.SC_BAD_REQUEST;
    private String error = "Bad Request";
    private List<ValidationGlobalError> globalErrors;
    private List<ValidationFieldError> fieldErrors;

    public ValidationErrorResponse(List<ValidationFieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public ValidationErrorResponse(List<ValidationGlobalError> globalErrors,
            List<ValidationFieldError> fieldErrors) {
        this.globalErrors = globalErrors;
        this.fieldErrors = fieldErrors;
    }

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public List<ValidationGlobalError> getGlobalErrors() {
        return globalErrors;
    }

    public List<ValidationFieldError> getFieldErrors() {
        return fieldErrors;
    }

    @Override
    public String toString() {
        return "ValidationErrorResponse [code=" + code + ", error=" + error + ", fieldErrors="
                + fieldErrors + ", globalErrors=" + globalErrors + "]";
    }
}
