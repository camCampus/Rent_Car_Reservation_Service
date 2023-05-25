package com.example.rent_car_reservation_service.web.Repository;

import com.example.rent_car_reservation_service.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDao extends JpaRepository<Reservation, Integer> {
//	@Query("SELECT r FROM Reservation r WHERE r.locationStart < :dateDebut AND :dateDebut < r.locationEnd OR r.locationEnd > :dateFin AND r.locationStart > :dateFin")
//	List<Reservation> findByDateBetween(@Param("dateDebut") LocalDate dateDebut, @Param("dateFin") LocalDate dateFin);

//	@Query("SELECT r FROM Reservation r WHERE r.status = 'ABANDONED' AND r.locationStart BETWEEN :dateDebut AND :dateFin OR r.locationEnd BETWEEN :dateDebut AND :dateFin")
//	List<Reservation> findByDateBetween(@Param("dateDebut") LocalDate dateDebut, @Param("dateFin") LocalDate dateFin);

	@Query("SELECT r FROM Reservation r WHERE r.status != 'ABANDONED' AND (r.locationStart BETWEEN :dateDebut AND :dateFin OR r.locationEnd BETWEEN :dateDebut AND :dateFin)")
	List<Reservation> findByDateBetween(@Param("dateDebut") LocalDate dateDebut, @Param("dateFin") LocalDate dateFin);

}