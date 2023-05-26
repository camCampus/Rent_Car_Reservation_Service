package com.example.rent_car_reservation_service.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Type {
    private int id;
    private String vehicleType;
    private int basePrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public float getKilometerPrice() {
        return kilometerPrice;
    }

    public void setKilometerPrice(float kilometerPrice) {
        this.kilometerPrice = kilometerPrice;
    }

    private float kilometerPrice;
    public Type() {}

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", vehicleType='" + vehicleType + '\'' +
                ", basePrice=" + basePrice +
                ", kilometerPrice=" + kilometerPrice +
                '}';
    }
}
