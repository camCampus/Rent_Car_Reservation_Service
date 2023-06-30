package com.example.rent_car_reservation_service.web.service;

import com.example.rent_car_reservation_service.model.Reservation;
import com.example.rent_car_reservation_service.model.Vehicle;
import com.example.rent_car_reservation_service.web.Repository.ReservationDao;
import com.example.rent_car_reservation_service.web.Repository.VehicleDao;
import com.example.rent_car_reservation_service.web.WrongDateExeception;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class VehiclesService {
	@Autowired
	private RestTemplate restTemplate;
	private VehicleDao vehicleDao;
	private ReservationDao reservationDao;

	@Autowired
	public VehiclesService(VehicleDao vehicleDao, ReservationDao Reservation) {
		this.vehicleDao = vehicleDao;
		this.reservationDao = Reservation;
	}

	public ResponseEntity<Vehicle[]> getAllVehicles() {
		// Remplacez l'adresse IP et port par celle de l'ordinateur distant
		String ipAddress = "172.10.230.20";
		int port = 8082;

		// Construire l'URL de l'API à appeler
		String apiUrl = "http://" + ipAddress + ":" + port + "/vehicles";
		return restTemplate.getForEntity(apiUrl, Vehicle[].class);
	}

//	-------------------------------------------------------------
	public Vehicle[] getAvailableVehicles(LocalDate dateDebut,LocalDate dateFin) throws URISyntaxException, WrongDateExeception {

		if (dateDebut.isBefore(LocalDate.now()))
		{
			throw new WrongDateExeception("We don't use timetravel, please use a valide date");
		}

		List<Reservation> reservations = reservationDao.findByDateBetween(dateDebut, dateFin);
		List<String> vehicleIds = new ArrayList<>();

		for (Reservation reservation : reservations) {
			vehicleIds.add(reservation.getVehicleId());
		}
		if (vehicleIds.size() > 0) {
//		System.out.println(vehicleIds);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String ipAddress = "172.10.230.20";
		int port = 8082;
		URI url = new URI("http://" + ipAddress + ":" + port + "/vehicles/out/resa");
		List<String> objEmp = vehicleIds;

		HttpEntity<List<String>> requestEntity = new HttpEntity<>(objEmp, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Vehicle[]> responseEntity = restTemplate.postForEntity(url, requestEntity, Vehicle[].class);

		System.out.println("Status Code: " + responseEntity.getStatusCode());
		System.out.println("Id: " + Arrays.toString(Objects.requireNonNull(responseEntity.getBody())));
		System.out.println("Location: " + responseEntity.getHeaders().getLocation());

		return responseEntity.getBody();
	}
		String ipAddress = "172.10.230.20";
		int port = 8082;
		String url = "http://" + ipAddress + ":" + port + "/vehicles";

		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForEntity(url, Vehicle[].class).getBody();
		}
}
