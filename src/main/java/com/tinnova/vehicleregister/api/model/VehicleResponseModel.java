package com.tinnova.vehicleregister.api.model;

import com.tinnova.vehicleregister.domain.model.VehicleBrand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleResponseModel {

  private Long id;

  private String vehicle;

  private VehicleBrand brand;

  private Integer year;

  private String description;

  private boolean sold;
}
