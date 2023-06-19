package com.tinnova.vehicleregister.api.model.input;

import com.tinnova.vehicleregister.domain.model.VehicleBrand;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class VehicleInputModel {

  @NotBlank
  private String vehicle;

  @NotNull
  private VehicleBrand brand;

  @NotNull
  @Positive
  private Integer year;

  private String description;

  @NotNull
  private Boolean sold;
}
