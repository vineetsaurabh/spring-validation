package com.vsaurabh.springvalidation.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vsaurabh.springvalidation.model.Employee;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> create(@RequestBody @Valid Employee employee) {
		logger.debug("EmployeeController.create method called.");
		Employee newEmployee = new Employee(employee.getName(), employee.getSalary());
		return ResponseEntity.ok(newEmployee);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> update(@RequestBody Employee Employee) {
		logger.debug("EmployeeController.update method called.");

		return ResponseEntity.ok(new Employee("A", 200.0));
	}

}
