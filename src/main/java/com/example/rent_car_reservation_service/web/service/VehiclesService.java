package com.example.rent_car_reservation_service.web.service;

import com.example.rent_car_reservation_service.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiclesService {
	@Autowired
	private RestTemplate restTemplate;

	public ResponseEntity<Vehicle[]> getAllVehicles() {
		// Remplacez l'adresse IP et port par celle de l'ordinateur distant
		String ipAddress = "192.168.1.206";
		int port = 8082;

		// Construire l'URL de l'API à appeler
		String apiUrl = "http://" + ipAddress + ":" + port + "/vehicles";
		return restTemplate.getForEntity(apiUrl, Vehicle[].class);
	}

//	public List<Vehicle> getAvailableVehicles() {
//		// Remplacez l'adresse IP et port par celle de l'ordinateur distant
//		String ipAddress = "192.168.1.206";
//		int port = 8082;
//
//		// Construire l'URL de l'API à appeler
//		String apiUrl = "http://" + ipAddress + ":" + port + "/vehicles";
//		Vehicle[] response = restTemplate.getForEntity(apiUrl, Vehicle[].class).getBody();
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
//	}
}
