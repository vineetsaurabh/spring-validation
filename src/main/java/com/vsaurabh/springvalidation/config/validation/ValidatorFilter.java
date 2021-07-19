package com.vsaurabh.springvalidation.config.validation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Path.Node;
import javax.validation.Validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vsaurabh.springvalidation.config.model.Request;
import com.vsaurabh.springvalidation.config.model.ValidationErrorResponse;
import com.vsaurabh.springvalidation.config.model.ValidationFieldError;
import com.vsaurabh.springvalidation.config.model.ValidationModel;

/**
 * Filter for validating all incoming requests It will validate requests those
 * are configured by instance of Validator
 * 
 * @author Vineet Saurabh
 *
 */
@Component
public class ValidatorFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(ValidatorFilter.class);
	
	private ValidationErrorResponse validationErrorResponse;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Map<Request, Class<? extends ValidationModel>> requestDataMap = RequestData.getRequestDataMap();

		AntPathMatcher pathMatcher = new AntPathMatcher();
		Request requestData = requestDataMap.keySet().stream()
				.filter(key -> pathMatcher.match(key.getUrlPattern(), request.getRequestURI())
						&& key.getMethod() == RequestMethod.valueOf(request.getMethod()))
				.findFirst().orElse(null);
		
		if (requestData != null) {
			Class<? extends ValidationModel> model = requestDataMap.get(requestData);
			logger.info("Validating {} for request {}", model.getSimpleName(), requestData.getUrlPattern());
			MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest(request);
			ValidationModel object = new Gson().fromJson(multiReadRequest.getReader(), model);
			if (validate(object)) {
                logger.info("{} is validated successfully", model.getSimpleName());
                filterChain.doFilter(multiReadRequest, response);
            } else {
                logger.info("{} is not validated. request {} will not be processed.", model.getSimpleName(), requestData.getUrlPattern());
                String responseToSend =
                        new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                                          .writeValueAsString(validationErrorResponse);

                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter()
                        .write(responseToSend);
                return;
            }
		} else {
			filterChain.doFilter(request, response);
		}
	}

	/**
	 * @param object to be validated
	 * @return true if object is valid
	 */
	private boolean validate(ValidationModel model) {
        javax.validation.Validator validator = Validation.buildDefaultValidatorFactory()
                                                         .getValidator();
        Set<ConstraintViolation<ValidationModel>> constraintViolations = validator.validate(model);
        if (constraintViolations.size() > 0) {
            List<ValidationFieldError> errors = new ArrayList<>();
            for(ConstraintViolation<ValidationModel> violation : constraintViolations) {
            	String field = null;
            	for (Node node : violation.getPropertyPath()) {
            	    field = node.getName();
            	}
            	String message = violation.getMessage();
            	Object value = violation.getInvalidValue();
            	errors.add(new ValidationFieldError(field, message, value));
            }
            validationErrorResponse = new ValidationErrorResponse(errors);
            logger.debug("Validation Errors - ", errors);
            return false;
        }
        return true;
    }

}