package com.tinnova.vehicleregister.domain.repository.filter;

import com.tinnova.vehicleregister.domain.model.VehicleBrand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleFilter {

  private String vehicle;

  private VehicleBrand brand;

  private Integer year;

  private Boolean sold;
}
