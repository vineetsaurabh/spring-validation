package com.vsaurabh.springvalidation.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vsaurabh.springvalidation.model.Car;

@Controller
@RequestMapping(value = "/car")
public class CarController {
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Car> create(@RequestBody Car car) {
		System.out.println("CarController.create method called.");
		Car newCar = new Car(car.getCompany(), car.getModel(), car.getYear(), car.getPrice());
		System.out.println("Car created = " + car);
		return ResponseEntity.ok(newCar);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Car> update(@RequestBody Car car) {
		System.out.println("CarController.create method called.");
		Car newCar = new Car(car.getCompany(), car.getModel(), car.getYear(), car.getPrice());
		System.out.println("Car created = " + car);
		return ResponseEntity.ok(newCar);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Car> get() {
		System.out.println("CarController.get method called.");
		Car car = new Car("Honda", "City", 2012, 12.5);
		return ResponseEntity.ok(car);
	}

}
