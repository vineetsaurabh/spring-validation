package com.vsaurabh.springvalidation.config.model;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A class to hold URL Pattern and RequestMethod
 * to be used as key
 * 
 * @author Vineet Saurabh
 *
 */
public final class Request {
	private static final Logger logger = LoggerFactory.getLogger(Request.class);
	private String urlPattern;
	private RequestMethod method;
	
	/**
	 * The only constructor for RequestData
	 * 
	 * @param urlPattern URL pattern
	 * @param method RequestMethod
	 * @throws IllegalArgumentException if any of argument is not valid
	 */
	public Request(String urlPattern, RequestMethod method) throws IllegalArgumentException {
		if(!StringUtils.hasText(urlPattern) || method == null) {
			logger.error("Fail to create RequestData for urlPattern={} and method={}", urlPattern, method);
			throw new IllegalArgumentException();
		}
		this.urlPattern = urlPattern;
		this.method = method;
	}

	/**
	 * @return URL Pattern of the request
	 */
	public String getUrlPattern() {
		return urlPattern;
	}

	/**
	 * @return method of the request
	 */
	public RequestMethod getMethod() {
		return method;
	}

	@Override
	public int hashCode() {
		return Objects.hash(method, urlPattern);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return method == other.method && Objects.equals(urlPattern, other.urlPattern);
	}

	@Override
	public String toString() {
		return "Request [urlPattern=" + urlPattern + ", method=" + method + "]";
	}
}
