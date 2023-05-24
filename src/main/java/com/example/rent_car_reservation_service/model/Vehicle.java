package com.example.rent_car_reservation_service.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehicle {

	@Id
	private String registration;

	private String type;
	private String brand;
	private String model;
	private Boolean rentStatus;
	private int kilometer;
	private int taxHorses;
	private String color;


	public Vehicle(String registration) {
		this.registration = registration;
	}

	public Vehicle() {

	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Boolean getRentStatus() {
		return rentStatus;
	}

	public void setRentStatus(Boolean rentStatus) {
		this.rentStatus = rentStatus;
	}

	public int getKilometer() {
		return kilometer;
	}

	public void setKilometer(int kilometer) {
		this.kilometer = kilometer;
	}

	public int getTaxHorses() {
		return taxHorses;
	}

	public void setTaxHorses(int taxHorses) {
		this.taxHorses = taxHorses;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car{" +
				"id" + registration +
				", type=" + type + '\'' +
				", brand=" + brand +
				", model=" + model +
				", status=" + rentStatus +
				", kilometer=" + kilometer +
				", taxHorses" + taxHorses +
				", color=" + color +
				'}';
	}
}
