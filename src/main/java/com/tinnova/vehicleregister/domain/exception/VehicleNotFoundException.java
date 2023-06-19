package com.tinnova.vehicleregister.domain.exception;

public class VehicleNotFoundException extends EntityNotFoundException {

  public VehicleNotFoundException(String message) {
    super(message);
  }

  public VehicleNotFoundException(Long vehicleId) {
    this(String.format("Vehicle with id %d does not exist", vehicleId));
  }
}
