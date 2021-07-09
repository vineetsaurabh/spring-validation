package com.vsaurabh.springvalidation.config.validation;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;

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

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Map<RequestData, Class<? extends ValidationModel>> requestDataMap = RequestValidatorData.getRequestDataMap();

		AntPathMatcher pathMatcher = new AntPathMatcher();
		RequestData requestData = requestDataMap.keySet().stream()
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
				logger.info("{} is not validated. request {} will not be processed.", model.getSimpleName(),
						requestData.getUrlPattern());
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}

	/**
	 * @param object to be validated
	 * @return true if object is valid
	 */
	private boolean validate(Object object) {
		javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(object);
		if (violations.size() > 0) {
			violations.forEach(v -> logger.info("{}", v));
			return false;
		}
		return true;
	}

}