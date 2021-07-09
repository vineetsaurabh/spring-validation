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
public class CarValidator implements Validator {
	
	@Override
	public Map<RequestData, Class<? extends ValidationModel>> getRequestData() {
		RequestData urlPattern1 = new RequestData("/car", RequestMethod.POST);
		RequestData urlPattern2 = new RequestData("/car", RequestMethod.PUT);
		Map<RequestData, Class<? extends ValidationModel>> map =  new HashMap<>();
		map.put(urlPattern1, Car.class);
		map.put(urlPattern2, Car.class);
		return map;
	}
	
	class Car implements ValidationModel {
		
		@NotNull
		@Size(max=6)
		private String company;
		
		@NotNull
	    @DecimalMax("5.5")
	    private Double price;

		public void setCompany(String company) {
			this.company = company;
		}

		public void setPrice(Double price) {
			this.price = price;
		}
	}

}
