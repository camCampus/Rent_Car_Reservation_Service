package com.example.rent_car_reservation_service.model;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation {
	@Id
	@GeneratedValue
	private Integer id;

	private Long licenseId;
	private Integer vehicleId;
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

	public Reservation(Integer id, Long licenseId, Integer vehicleId, LocalDate locationStart, LocalDate locationEnd,
					   Integer estimateKm, Integer actualKm, Integer deposit, String status) {
		this.id = id;
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
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(Long licenseId) {
		this.licenseId = licenseId;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
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
				"id=" + id +
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