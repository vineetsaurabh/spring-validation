package com.vsaurabh.springvalidation.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

public class Employee {
	
	@NotNull
	private String name;
	
	@DecimalMax("20000")
	private Double salary;
	
	public Employee(String name, Double salary) {
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

}
