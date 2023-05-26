package com.example.rent_car_reservation_service.web.controller;

import com.example.rent_car_reservation_service.Exception.DateOnPastException;
import com.example.rent_car_reservation_service.model.Reservation;
import com.example.rent_car_reservation_service.model.Vehicle;
import com.example.rent_car_reservation_service.web.Repository.ReservationRepository;
import com.example.rent_car_reservation_service.web.service.VehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	private final ReservationRepository reservationRepository;
	@Autowired
	private VehiclesService vehiclesService;
	@Autowired
	public ReservationController(ReservationRepository Reservation) {
		this.reservationRepository = Reservation;
	}

	@GetMapping
	public List<Reservation> getAllReservations() {
		return reservationRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Reservation> getReservationById(@PathVariable Integer id) {
		return reservationRepository.findById(id);
	}

	@PostMapping
	public Reservation createReservation(@RequestBody Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@PutMapping("/{id}")
	public Reservation updateReservation(@PathVariable Integer id, @RequestBody Reservation updatedReservation) {
		Optional<Reservation> existingReservation = reservationRepository.findById(id);
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
			return reservationRepository.save(reservation);
		} else {
			throw new RuntimeException("Reservation not found with ID: " + id);
		}
	}

	@DeleteMapping("/{id}")
	public void deleteReservation(@PathVariable Integer id) {
		reservationRepository.deleteById(id);
	}

	@GetMapping("/algo")
	public ResponseEntity<Vehicle[]> getAllForDate(@RequestParam(name = "locationStart")  String locationStart, @RequestParam(name = "locationEnd") String locationEnd) throws DateOnPastException {

		 LocalDate start = LocalDate.parse(locationStart);
		 LocalDate end = LocalDate.parse(locationEnd);

		if (!checkDate(start) || !checkDate(end))
		{
			throw new DateOnPastException("Ooops it seems that the date provided is in the past... ");
		}

		return vehiclesService.getVehicleForDate( start, end);
	}

	private Boolean checkDate(LocalDate date)
	{
		LocalDate current = LocalDate.now();

		return date.isEqual(current) || date.isAfter(current);
	}

	@ExceptionHandler(DateOnPastException.class)
	public ResponseEntity<String> DateIsPastException(DateOnPastException exception)
	{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}

	@GetMapping("/test")
	public List<String> test()
	{
		LocalDate start = LocalDate.parse("2023-10-10");
		LocalDate end = LocalDate.parse("2023-10-11");
		return vehiclesService.test(start, end);
	}
}


