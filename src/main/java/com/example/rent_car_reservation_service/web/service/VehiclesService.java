package com.example.rent_car_reservation_service.web.service;

import com.example.rent_car_reservation_service.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VehiclesService {
	@Autowired
	private RestTemplate restTemplate;
	private final ResponseEntity<Object> vehicles;

	VehiclesService() {
		// Remplacez l'adresse IP et port par celle de l'ordinateur distant
		String ipAddress = "192.168.1.206";
		int port = 8082;

		// Construire l'URL de l'API à appeler
		String apiUrl = "http://" + ipAddress + ":" + port + "/vehicles";

		this.vehicles = restTemplate.getForEntity(apiUrl, Object.class);
	}

	public ResponseEntity<Object> getAllVehicles() {
		return this.vehicles;
	}

	public ResponseEntity<Object> getAviabileVehicles() {
		return this.vehicles;
	}
}
