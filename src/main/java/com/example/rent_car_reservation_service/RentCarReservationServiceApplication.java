package com.example.rent_car_reservation_service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableSwagger2
public class RentCarReservationServiceApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(RentCarReservationServiceApplication.class, args);
	}

}
