package com.example.rent_car_reservation_service.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity

public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reservation_id;
	private Long licenseId;
	private String vehicleId;
	@NotNull
	private LocalDate locationStart;
	@NotNull
	private LocalDate locationEnd;
	private Integer estimateKm;
	private Integer actualKm;
	private Integer deposit;
	private String status;

	public Reservation() {
		// Constructeur par défaut requis par JPA
	}

	public Reservation(Integer id, Long licenseId, String vehicleId, LocalDate locationStart, LocalDate locationEnd,
					   Integer estimateKm, Integer actualKm, Integer deposit, String status) {
		this.reservation_id = id;
		this.licenseId = licenseId;
		this.vehicleId = vehicleId;
		this.locationStart = locationStart;
		this.locationEnd = locationEnd;
		this.estimateKm = estimateKm;
		this.actualKm = actualKm;
		this.deposit = deposit;
		this.status = status;
	}

	public Reservation(LocalDate locationStart, LocalDate locationEnd) {
		this.locationStart = locationStart;
		this.locationEnd = locationEnd;
	}

	public Integer getId() {
		return reservation_id;
	}

	public void setId(Integer id) {
		this.reservation_id = id;
	}

	public Long getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(Long licenseId) {
		this.licenseId = licenseId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public LocalDate getLocationStart() {
		return locationStart;
	}

	public void setLocationStart(LocalDate locationStart) {
		this.locationStart = locationStart;
	}

	public LocalDate getLocationEnd() {
		return locationEnd;
	}

	public void setLocationEnd(LocalDate locationEnd) {
		this.locationEnd = locationEnd;
	}

	public Integer getEstimateKm() {
		return estimateKm;
	}

	public void setEstimateKm(Integer estimateKm) {
		this.estimateKm = estimateKm;
	}

	public Integer getActualKm() {
		return actualKm;
	}

	public void setActualKm(Integer actualKm) {
		this.actualKm = actualKm;
	}

	public Integer getDeposit() {
		return deposit;
	}

	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}

	public String getStatus() { return status; }

	public void setStatus(String status) { this.status = status; }

	@Override
	public String toString() {
		return "Reservation{" +
				"id=" + reservation_id +
				", licenseId=" + licenseId +
				", vehicleId=" + vehicleId +
				", locationStart=" + locationStart +
				", locationEnd=" + locationEnd +
				", estimateKm=" + estimateKm +
				", actualKm=" + actualKm +
				", deposit=" + deposit +
				", status='" + status + '\'' +
				'}';
	}
}