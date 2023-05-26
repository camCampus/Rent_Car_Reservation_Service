package com.example.rent_car_reservation_service.web.service;

import com.example.rent_car_reservation_service.model.Reservation;
import com.example.rent_car_reservation_service.model.Type;
import com.example.rent_car_reservation_service.model.Vehicle;
import com.example.rent_car_reservation_service.web.Repository.ReservationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ReservationService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ReservationDao reservationDao;

	@Transactional
	public void updatedReservationPriceAndDeposit(int estimedKM, int idReservation){
		Optional<Reservation> reservation = reservationDao.findById(idReservation);
//		System.out.println(reservation.get().getVehicleId());
		String idVehicle = reservation.get().getVehicleId();
		System.out.println(idVehicle);

		String url = "http://localhost:8082/vehicles/" + idVehicle;
		Vehicle response = restTemplate.getForObject(url, Vehicle.class);
		assert response != null;
		System.out.println(response.getType());

		String url2 = "http://localhost:8082/type/string/" + response.getType();
		Type responseType = restTemplate.getForObject(url2, Type.class);
		assert responseType != null;
		int basePrice = responseType.getBasePrice();
		float kilometerPrice = responseType.getKilometerPrice();
		float price = basePrice + (estimedKM * kilometerPrice);

		reservationDao.updateEstimatedPrice(idReservation, estimedKM, price);
	}
}
