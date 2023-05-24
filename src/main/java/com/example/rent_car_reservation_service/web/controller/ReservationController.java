package com.example.rent_car_reservation_service.web.controller;

import com.example.rent_car_reservation_service.model.Reservation;
import com.example.rent_car_reservation_service.model.Vehicle;
import com.example.rent_car_reservation_service.web.dao.ReservationDao;
import com.example.rent_car_reservation_service.web.service.VehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	private final ReservationDao reservationDao;
	@Autowired
	private VehiclesService vehiclesService;
	@Autowired
	public ReservationController(ReservationDao Reservation) {
		this.reservationDao = Reservation;
	}

	@GetMapping
	public List<Reservation> getAllReservations() {
		return reservationDao.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Reservation> getReservationById(@PathVariable Integer id) {
		return reservationDao.findById(id);
	}

	@PostMapping
	public Reservation createReservation(@RequestBody Reservation reservation) {
		return reservationDao.save(reservation);
	}

	@PutMapping("/{id}")
	public Reservation updateReservation(@PathVariable Integer id, @RequestBody Reservation updatedReservation) {
		Optional<Reservation> existingReservation = reservationDao.findById(id);
		if (existingReservation.isPresent()) {
			Reservation reservation = existingReservation.get();
			reservation.setLicenseId(updatedReservation.getLicenseId());
			reservation.setVehicleId(updatedReservation.getVehicleId());
			reservation.setLocationStart(updatedReservation.getLocationStart());
			reservation.setLocationEnd(updatedReservation.getLocationEnd());
			reservation.setEstimateKm(updatedReservation.getEstimateKm());
			reservation.setActualKm(updatedReservation.getActualKm());
			reservation.setDeposit(updatedReservation.getDeposit());
			reservation.setStatus(updatedReservation.getStatus());
			return reservationDao.save(reservation);
		} else {
			throw new RuntimeException("Reservation not found with ID: " + id);
		}
	}

	@DeleteMapping("/{id}")
	public void deleteReservation(@PathVariable Integer id) {
		reservationDao.deleteById(id);
	}

	@GetMapping("/testAll")
	public ResponseEntity<Vehicle[]> getAllV() {
		return this.vehiclesService.getAllVehicles();
	}

	@GetMapping("/testAviaible")
	public Vehicle[] getAvailableV() {
		return this.vehiclesService.getAvailableVehicles();
	}
}


