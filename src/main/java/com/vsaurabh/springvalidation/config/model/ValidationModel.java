package com.vsaurabh.springvalidation.config.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A marker interface must be implemented by Validation Model
 * @author Vineet Saurabh
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public interface ValidationModel {

}