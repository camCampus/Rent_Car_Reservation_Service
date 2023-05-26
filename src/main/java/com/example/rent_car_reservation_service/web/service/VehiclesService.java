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
		/**
		 * Fonction pour récupérer les réservations en fonction des dates envoyées par le client.
		 *
		 */
		List<Reservation> resaForRange = reservationRepository.getReservationForDate(start, end);

		/**
		 * Fonction pour récupérer les id des véhicules dans la liste des réservations [resaForRange]
		 */
		List<String> vehicleList = resaForRange.stream()
				.map(Reservation::getVehicleId)
				.collect(Collectors.toList());

		if (vehicleList.size() > 0)
		{
			/**
			 * Création d'un header et d'une httpEntity pour former l'objet requestEntity qui prend en paramètre la list des vehicules
			 * id et le header.
			 */
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<List<String>> requestEntity = new HttpEntity<>(vehicleList, headers);

			/**
			 * Requete du service vehicule pour renvoyer tous les vécules qui ne sont pas dans la liste envoyée en parametre.
			 */
			ResponseEntity<Vehicle[]> res = restTemplate.postForEntity(apiUrl, requestEntity, Vehicle[].class);

			return res;
		}
		String getAll = "http://" + vehicleServiceIp + ":" + vehicleServicePort + "/vehicles";
		return restTemplate.getForEntity(getAll, Vehicle[].class);

	}

	public List<String> test(LocalDate s, LocalDate e
	)
	{
		List<Reservation> resaForRange = reservationRepository.getReservationForDate(s, e);

		return  resaForRange.stream()
				.map(Reservation::getVehicleId)
				.collect(Collectors.toList());
	}
}
