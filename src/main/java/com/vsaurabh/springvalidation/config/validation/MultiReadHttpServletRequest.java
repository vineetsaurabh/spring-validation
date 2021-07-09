package com.vsaurabh.springvalidation.config.validation;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

/**
 * 
 * A wrapper for HttpServletRequest in order to read it multiple times
 * 
 * @author Vineet Saurabh
 *
 */
public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
	private ByteArrayOutputStream cachedBytes;

	/**
	 * Wrap HttpServletRequest to MultiReadHttpServletRequest
	 * @param request to be wrapped
	 */
	public MultiReadHttpServletRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if (cachedBytes == null) {
			cacheInputStream();
		}
		return new CachedServletInputStream();
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	/**
	 * Cache the inputstream in order to read it multiple times.
	 * 
	 * @throws IOException
	 */
	private void cacheInputStream() throws IOException {
		cachedBytes = new ByteArrayOutputStream();
		IOUtils.copy(super.getInputStream(), cachedBytes);
	}

	
	/**
	 * An inputstream which reads the cached request body
	 * 
	 * @author saurabh
	 *
	 */
	public class CachedServletInputStream extends ServletInputStream {
		private ByteArrayInputStream input;

		/**
		 * create a new input stream from the cached request body
		 */
		public CachedServletInputStream() {
			input = new ByteArrayInputStream(cachedBytes.toByteArray());
		}

		@Override
        public int read() throws IOException {
            return input.read();
        }

        @Override
        public boolean isFinished() {
            return input.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            throw new RuntimeException("Not implemented");
        }
	}
}
