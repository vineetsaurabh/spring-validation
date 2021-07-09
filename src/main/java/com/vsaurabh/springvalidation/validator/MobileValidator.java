package com.vsaurabh.springvalidation.validator;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vsaurabh.springvalidation.config.validation.RequestData;
import com.vsaurabh.springvalidation.config.validation.ValidationModel;
import com.vsaurabh.springvalidation.config.validation.Validator;

@Component
public class MobileValidator implements Validator {

	@Override
	public Map<RequestData, Class<? extends ValidationModel>> getRequestData() {
		RequestData urlPattern = new RequestData("/mobile/{mob_id}/handset", RequestMethod.POST);
		Map<RequestData, Class<? extends ValidationModel>> map =  new HashMap<>();
		map.put(urlPattern, Mobile.class);
		return map;
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
