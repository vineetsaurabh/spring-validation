package com.vsaurabh.springvalidation.config.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMethod;

import com.vsaurabh.springvalidation.config.model.Request;
import com.vsaurabh.springvalidation.config.model.ValidationModel;

/**
 * Builder for RequestData
 * @author Vineet Saurabh
 *
 */
public class RequestDataBuilder {

	private Map<Request, Class<? extends ValidationModel>> map = new HashMap<>();

	private Class<? extends ValidationModel> clazz;
	private String urlPattern;

	private RequestDataBuilder(Class<? extends ValidationModel> clazz, String urlPattern) {
		this.clazz = clazz;
	}

	/**
	 *
	 * @param clazz {@link com.strands.common.validation.config.ValidationModel} class
	 * @return RequestValidatorDataBuilder
	 */
	public static RequestDataBuilder of(Class<? extends ValidationModel> clazz) {
		return new RequestDataBuilder(clazz, null);
	}

	/**
	 *
	 * @param urlPattern common url pattern for all Http methods
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder url(String urlPattern) {
		this.urlPattern = urlPattern;
		return this;
	}

	/**
	 * Add RequestMethod.DELETE method in the map for the common url
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder delete() {
		Request request = new Request(urlPattern, RequestMethod.DELETE);
		map.put(request, clazz);
		return this;
	}

	/**
	 * Add RequestMethod.DELETE method in the map for the provided url
	 *
	 * @param urlPattern the url pattern
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder delete(String urlPattern) {
		Request request = new Request(urlPattern, RequestMethod.DELETE);
		map.put(request, clazz);
		return this;
	}

	/**
	 * Add RequestMethod.GET method in the map for the common url
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder get() {
		Request request = new Request(urlPattern, RequestMethod.GET);
		map.put(request, clazz);
		return this;
	}

	/**
	 * Add RequestMethod.GET method in the map for the provided url
	 *
	 * @param urlPattern the url pattern
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder get(String urlPattern) {
		Request request = new Request(urlPattern, RequestMethod.GET);
		map.put(request, clazz);
		return this;
	}

	/**
	 * Add RequestMethod.HEAD method in the map for the common url
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder head() {
		Request request = new Request(urlPattern, RequestMethod.HEAD);
		map.put(request, clazz);
		return this;
	}

	/**
	 * Add RequestMethod.HEAD method in the map for the provided url
	 *
	 * @param urlPattern the url pattern
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder head(String urlPattern) {
		Request request = new Request(urlPattern, RequestMethod.HEAD);
		map.put(request, clazz);
		return this;
	}

	/**
	 * Add RequestMethod.OPTIONS method in the map for the common url
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder options() {
		Request request = new Request(urlPattern, RequestMethod.OPTIONS);
		map.put(request, clazz);
		return this;
	}

	/**
	 * Add RequestMethod.OPTIONS method in the map for the provided url
	 *
	 * @param urlPattern the url pattern
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder options(String urlPattern) {
		Request request = new Request(urlPattern, RequestMethod.OPTIONS);
		map.put(request, clazz);
		return this;
	}

	/**
	 * Add RequestMethod.PATCH method in the map for the common url
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder patch() {
		Request request = new Request(urlPattern, RequestMethod.PATCH);
		map.put(request, clazz);
		return this;
	}

	/**
	 * Add RequestMethod.PATCH method in the map for the provided url
	 *
	 * @param urlPattern the url pattern
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder patch(String urlPattern) {
		Request request = new Request(urlPattern, RequestMethod.PATCH);
		map.put(request, clazz);
		return this;
	}

	/**
	 * Add RequestMethod.POST method in the map for the common url
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder post() {
		Request request = new Request(urlPattern, RequestMethod.POST);
		map.put(request, clazz);
		return this;
	}

	/**
	 * Add RequestMethod.POST method in the map for the provided url
	 *
	 * @param urlPattern the url pattern
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder post(String urlPattern) {
		Request request = new Request(urlPattern, RequestMethod.POST);
		map.put(request, clazz);
		return this;
	}

	/**
	 * Add RequestMethod.PUT method in the map for the common url
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder put() {
		Request request = new Request(urlPattern, RequestMethod.PUT);
		map.put(request, clazz);
		return this;
	}

	/**
	 * Add RequestMethod.PUT method in the map for the provided url
	 *
	 * @param urlPattern the url pattern
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder put(String urlPattern) {
		Request request = new Request(urlPattern, RequestMethod.POST);
		map.put(request, clazz);
		return this;
	}

	/**
	 * Add RequestMethod.TRACE method in the map for the common url
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder trace() {
		Request request = new Request(urlPattern, RequestMethod.TRACE);
		map.put(request, clazz);
		return this;
	}

	/**
	 * Add RequestMethod.TRACE method in the map for the provided url
	 *
	 * @param urlPattern the url pattern
	 * @return RequestValidatorDataBuilder
	 */
	public RequestDataBuilder trace(String urlPattern) {
		Request request = new Request(urlPattern, RequestMethod.TRACE);
		map.put(request, clazz);
		return this;
	}

	/**
	 *
	 * @return Map of {@link com.strands.common.validation.model.RequestData} and its corresponding
	 *  {@link com.strands.common.validation.config.ValidationModel}
	 */
	public Map<Request, Class<? extends ValidationModel>> build() {
		return map;
	}
}
