package com.tinnova.vehicleregister.domain.repository.filter;

import com.tinnova.vehicleregister.domain.model.VehicleBrand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleFilter {

  private VehicleBrand brand;

  private Integer year;
}
