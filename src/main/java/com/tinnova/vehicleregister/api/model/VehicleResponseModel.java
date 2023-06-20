package com.tinnova.vehicleregister.api.model;

import com.tinnova.vehicleregister.domain.model.VehicleBrand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleResponseModel {

  @Schema(example = "1")
  private Long id;

  @Schema(example = "ARGO")
  private String vehicle;

  @Schema(example = "FIAT")
  private VehicleBrand brand;

  @Schema(example = "2020")
  private Integer year;

  @Schema(example = "Brand new")
  private String description;

  @Schema(example = "false")
  private boolean sold;
}
