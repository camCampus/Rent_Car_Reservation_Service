package com.example.rent_car_reservation_service.web.Repository;

import com.example.rent_car_reservation_service.model.Reservation;
import com.example.rent_car_reservation_service.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {



   @Query(value = "FROM Reservation r WHERE r.status != 'ABANDONED' AND (r.locationStart BETWEEN :startDate AND :endDate OR r.locationEnd BETWEEN :startDate AND :endDate)")
   public List<Reservation> getReservationForDate(@Param("startDate") LocalDate start, @Param("endDate") LocalDate end);


}
