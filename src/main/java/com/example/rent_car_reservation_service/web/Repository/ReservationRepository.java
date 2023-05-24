package com.example.rent_car_reservation_service.web.dao;

import com.example.rent_car_reservation_service.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDao extends JpaRepository<Reservation, Integer> {

}
