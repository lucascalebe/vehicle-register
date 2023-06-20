package com.tinnova.vehicleregister.domain.service;

import com.tinnova.vehicleregister.domain.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface VehicleService {

  Page<Vehicle> findAll(Specification<Vehicle> spec, Pageable pageable);

  Vehicle findById(final Long vehicleId);

  Vehicle save(Vehicle vehicle);

  void deleteById(final Long vehicleId);
}
