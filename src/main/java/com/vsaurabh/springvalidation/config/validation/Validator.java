package com.vsaurabh.springvalidation.config.validation;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.vsaurabh.springvalidation.config.model.Request;
import com.vsaurabh.springvalidation.config.model.ValidationModel;

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
	Map<Request, Class<? extends ValidationModel>> getRequestData();

}
