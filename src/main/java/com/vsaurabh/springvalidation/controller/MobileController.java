package com.vsaurabh.springvalidation.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vsaurabh.springvalidation.model.Mobile;

@Controller
@RequestMapping(value = "/mobile")
public class MobileController {

	@RequestMapping(value="/{mob_id}/handset", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mobile> create(@RequestBody Mobile mobile) {
		System.out.println("MobileController.create method called.");
		Mobile newMobile = new Mobile(mobile.getCompany(), mobile.getModel(), mobile.getYear(), mobile.getPrice());
		System.out.println("Mobile created = " + mobile);
		return ResponseEntity.ok(newMobile);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mobile> update(@RequestBody Mobile mobile) {
		System.out.println("MobileController.create method called.");
		Mobile newMobile = new Mobile(mobile.getCompany(), mobile.getModel(), mobile.getYear(), mobile.getPrice());
		System.out.println("Mobile created = " + mobile);
		return ResponseEntity.ok(newMobile);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Mobile> get() {
		System.out.println("MobileController.get method called.");
		Mobile mobile = new Mobile("Nokie", "N92", 2012, 8000.0);
		return ResponseEntity.ok(mobile);
	}
}
