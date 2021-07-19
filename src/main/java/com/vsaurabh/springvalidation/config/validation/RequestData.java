package com.vsaurabh.springvalidation.config.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vsaurabh.springvalidation.config.model.Request;
import com.vsaurabh.springvalidation.config.model.ValidationModel;

/**
 * 
 * A placeholder for all requests that is needed to be validated
 * 
 * @author Vineet Saurabh
 *
 */
@Component
public final class RequestData {
	private static final Logger logger = LoggerFactory.getLogger(RequestData.class);
	
	/**
	 * List of {@link com.vsaurabh.springvalidation.config.validation.Validator} implementations
	 */
	@Autowired
	private List<Validator> validators;

	private static final Map<Request, Class<? extends ValidationModel>> REQUEST_DATA_MAP = new HashMap<>();
	
	/**
	 * initialize Map of request and its corresponding {@link com.vsaurabh.springvalidation.config.validation.ValidationModel}
	 */
	@PostConstruct
	public void init() {
		for (Validator validator : validators) {
			Map<Request, Class<? extends ValidationModel>> requestData = validator.getRequestData();
			if(requestData != null) {
				REQUEST_DATA_MAP.putAll(requestData);
			}
		}
		logger.info("These requests are loaded for validation \n{}", REQUEST_DATA_MAP.keySet());
	}
	
	/**
	 * @return Map of request and its corresponding {@link com.vsaurabh.springvalidation.config.validation.ValidationModel}
	 */
	public static Map<Request, Class<? extends ValidationModel>> getRequestDataMap() {
		return REQUEST_DATA_MAP;
	}

}
