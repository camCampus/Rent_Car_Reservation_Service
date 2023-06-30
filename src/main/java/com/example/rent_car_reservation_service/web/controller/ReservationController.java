package com.example.rent_car_reservation_service.web.controller;

import com.example.rent_car_reservation_service.model.Reservation;
import com.example.rent_car_reservation_service.model.Vehicle;
import com.example.rent_car_reservation_service.web.Repository.ReservationDao;
import com.example.rent_car_reservation_service.web.WrongDateExeception;
import com.example.rent_car_reservation_service.web.service.ReservationService;
import com.example.rent_car_reservation_service.web.service.VehiclesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@Api("Api pour les opérations sur les reservations")
@RequestMapping("/reservations")
public class ReservationController {

	private final ReservationDao reservationDao;
	@Autowired
	private VehiclesService vehiclesService;
	@Autowired
	private ReservationService reservationService;

	@Autowired
	public ReservationController(ReservationDao Reservation) {
		this.reservationDao = Reservation;
	}

	@ApiOperation(value = "Return all reservations")
	@GetMapping
	public List<Reservation> getAllReservations() {
		return reservationDao.findAll();
	}
	@ApiOperation(value = "Return reservation from ID in path")
	@GetMapping("/{id}")
	public Optional<Reservation> getReservationById(@PathVariable Integer id) {
		return reservationDao.findById(id);
	}
	@ApiOperation(value = "Create a new reservation")
	@PostMapping
	public Reservation createReservation(@RequestBody Reservation reservation) {
		return reservationDao.save(reservation);
	}
	@ApiOperation(value = "Update an existing reservation")
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
			reservation.setPrice(updatedReservation.getPrice());
			reservation.setStatus(updatedReservation.getStatus());
			return reservationDao.save(reservation);
		} else {
			throw new RuntimeException("Reservation not found with ID: " + id);
		}
	}
	@ApiOperation(value = "Delete a reservation from ID in path")
	@DeleteMapping("/{id}")
	public void deleteReservation(@PathVariable Integer id) {
		reservationDao.deleteById(id);
	}

//	@GetMapping("/testAll")
//	public ResponseEntity<Vehicle[]> getAllV() {
//		return this.vehiclesService.getAllVehicles();
//	}
@ApiOperation(value = "Return all aviaibles vehicles for dates pass in parameter")
	@GetMapping("/AviaibleVehicles")
	public Vehicle[] getAvailableV(@RequestParam String dateDebut, String dateFin) throws URISyntaxException, WrongDateExeception {
		LocalDate start = LocalDate.parse(dateDebut);
		LocalDate end = LocalDate.parse(dateFin);
		return this.vehiclesService.getAvailableVehicles(start, end);
	}

	@ApiOperation(value = "Return updated reservation price")
	@PostMapping("/price")
	public Float priceAndDeposit(@RequestParam int estimatedKm, int reservationId){
		reservationService.updatedReservationPriceAndDeposit(estimatedKm, reservationId);
		return reservationDao.findById(reservationId).get().getPrice();
	}

	@ExceptionHandler(WrongDateExeception.class)
	public ResponseEntity<Object> handleWrongDateException(WrongDateExeception ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("{\"Invalide date\":\"" + ex.getMessage() + "\"}");
	}
}


