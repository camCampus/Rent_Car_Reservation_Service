package com.example.rent_car_reservation_service.web.Repository;

import com.example.rent_car_reservation_service.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
//    @Query(value = "FROM Vehicle v WHERE v.registration NOT IN :vehicleList")
//    public List<Vehicle> getVehicleNotInResa(@Param("vehicleList") List<Vehicle> vehicles);
}
