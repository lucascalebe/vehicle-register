package com.tinnova.vehicleregister.api.model;

import com.tinnova.vehicleregister.domain.model.VehicleBrand;
import io.swagger.v3.oas.annotations.media.Schema;
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
