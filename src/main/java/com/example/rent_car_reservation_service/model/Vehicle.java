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
	private String status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
				", status=" + status +
				", kilometer=" + kilometer +
				", taxHorses" + taxHorses +
				", color=" + color +
				'}';
	}
}
