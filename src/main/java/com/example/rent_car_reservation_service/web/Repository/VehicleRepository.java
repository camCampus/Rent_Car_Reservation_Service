package com.example.rent_car_reservation_service.web.dao;

import com.example.rent_car_reservation_service.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDao extends JpaRepository<Vehicle, String> {
}
