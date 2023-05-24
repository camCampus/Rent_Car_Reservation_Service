package com.example.rent_car_reservation_service.web.service;

import com.example.rent_car_reservation_service.model.Reservation;
import com.example.rent_car_reservation_service.model.Vehicle;
import com.example.rent_car_reservation_service.web.Repository.ReservationRepository;
import com.example.rent_car_reservation_service.web.Repository.VehicleRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class VehiclesService {
	@Autowired
	private RestTemplate restTemplate;
	private VehicleRepository vehicleDao;
	private ReservationRepository reservationDao;

	private String vehicleServiceIp = "192.168.1.206";

	private int vehicleServicePort = 8082;


	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private VehicleRepository vehicleRepository;

	public ResponseEntity<Vehicle[]> getVehicleForDate(LocalDate start, LocalDate end) {
		String apiUrl = "http://" + vehicleServiceIp + ":" + vehicleServicePort + "/vehicles/out/resa";

		List<Reservation> resaForRange = reservationRepository.getReservationForDate(start, end);



		List<String> vehicleList = resaForRange.stream()
				.map(Reservation::getVehicleId)
				.collect(Collectors.toList());


		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<List<String>> requestEntity = new HttpEntity<>(vehicleList, headers);


		ResponseEntity<Vehicle[]> res = restTemplate.postForEntity(apiUrl, requestEntity, Vehicle[].class);

		return res;


	}
//	public ResponseEntity<Vehicle[]> getAllVehicles() {
//		// Remplacez l'adresse IP et port par celle de l'ordinateur distant
//		String ipAddress = "192.168.1.206";
//		int port = 8082;
//
//		// Construire l'URL de l'API à appeler
//
//		return restTemplate.getForEntity(apiUrl, Vehicle[].class);
//	}

//	-------------------------------------------------------------
	public Vehicle[] getAvailableVehicles() {
		List<Reservation> reservations = reservationDao.findAll();
//		for (Reservation reservation : reservations) {
//			System.out.println(reservation.getVehicleId());
//		}

		// Remplacez l'adresse IP et port par celle de l'ordinateur distant
		String ipAddress = "192.168.1.206";
		int port = 8082;
		// Construire l'URL de l'API à appeler
		String apiUrl = "http://" + ipAddress + ":" + port + "/vehicles";
		Vehicle[] response = restTemplate.getForEntity(apiUrl, Vehicle[].class).getBody();

//		assert response != null;
//		for (Vehicle vehicle : response) {
//			System.out.println(vehicle.getRegistration());
//		}
//		for (Reservation reservation : reservations){
//			for (Vehicle vehicle : response) {
//				if (Objects.equals(reservation.getVehicleId(), vehicle.getRegistration())) {
//					System.out.println(reservation.getVehicleId());
//				}
//			}
//		}
		return response;
//
//		assert response != null;
//		List<Vehicle> availableVehicles = Arrays.stream(response)
//				.filter(vehicle -> "AVAILABLE".equals(vehicle.getStatus()))
//				.collect(Collectors.toList());
//
//		// Faites ce que vous voulez avec la liste de véhicules disponibles
////		for (Vehicle vehicle : availableVehicles) {
////			System.out.println(vehicle.getRegistration());
////		}
//		return availableVehicles;
	}
}
