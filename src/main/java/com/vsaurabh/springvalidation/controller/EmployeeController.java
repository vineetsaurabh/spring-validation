package com.vsaurabh.springvalidation.controller;

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
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> create(@RequestBody Employee employee) {
		System.out.println("EmployeeController.create method called.");
		Employee newEmployee = new Employee(employee.getName(), employee.getSalary());
		return ResponseEntity.ok(newEmployee);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> update(@RequestBody Employee Employee) {
		System.out.println("EmployeeController.update method called.");

		return ResponseEntity.ok(new Employee("A", 200.0));
	}
	

}
