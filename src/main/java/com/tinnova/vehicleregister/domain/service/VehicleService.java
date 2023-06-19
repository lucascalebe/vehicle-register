package com.tinnova.vehicleregister.domain.service;

import com.tinnova.vehicleregister.domain.model.Vehicle;

public interface VehicleService {

  Vehicle findById(final Long vehicleId);

  Vehicle save(Vehicle vehicle);

  void deleteById(final Long vehicleId);
}
