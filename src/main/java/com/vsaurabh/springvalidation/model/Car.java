package com.vsaurabh.springvalidation.model;

public class Car {
	private String company;
	private String model;
	private int year;
	private Double price;

	public Car(String company, String model, int year, Double price) {
		this.company = company;
		this.model = model;
		this.year = year;
		this.price = price;
	}
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [company=" + company + ", model=" + model + ", year=" + year + ", price=" + price + "]";
	}
}
