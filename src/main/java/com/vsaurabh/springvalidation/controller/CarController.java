package com.vsaurabh.springvalidation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(CarController.class);
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Car> create(@RequestBody Car car) {
		logger.debug("CarController.create method called.");
		Car newCar = new Car(car.getCompany(), car.getModel(), car.getYear(), car.getPrice());
		logger.debug("Car created = " + car);
		return ResponseEntity.ok(newCar);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Car> update(@RequestBody Car car) {
		logger.debug("CarController.create method called.");
		Car newCar = new Car(car.getCompany(), car.getModel(), car.getYear(), car.getPrice());
		logger.debug("Car created = " + car);
		return ResponseEntity.ok(newCar);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Car> get() {
		logger.debug("CarController.get method called.");
		Car car = new Car("Honda", "City", 2012, 12.5);
		return ResponseEntity.ok(car);
	}

}
