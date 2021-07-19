package com.vsaurabh.springvalidation.validator;

import java.util.Map;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.vsaurabh.springvalidation.config.model.Request;
import com.vsaurabh.springvalidation.config.model.ValidationModel;
import com.vsaurabh.springvalidation.config.validation.RequestDataBuilder;
import com.vsaurabh.springvalidation.config.validation.Validator;

@Component
public class MobileValidator implements Validator {
	
	private static final String URL_PATTERN = "/**/mobile/{mob_id}/handset";

	@Override
	public Map<Request, Class<? extends ValidationModel>> getRequestData() {
		return RequestDataBuilder.of(Mobile.class).url(URL_PATTERN).post().put().build();
	}

	class Mobile implements ValidationModel {

		@NotNull
		@Size(max = 20)
		private String company;

		@NotNull
		@DecimalMax("10000")
		private Double price;

		public void setCompany(String company) {
			this.company = company;
		}

		public void setPrice(Double price) {
			this.price = price;
		}
	}

}
