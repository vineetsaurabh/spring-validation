package com.vsaurabh.springvalidation.config.validation;

import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * an interface must be implemented by Validators
 * 
 * @author Vineet Saurabh
 *
 */
@Component
public interface Validator {
	
	/**
	 * @return map of RequestData
	 * and its corresponding model
	 */
	Map<RequestData, Class<? extends ValidationModel>> getRequestData();

}
