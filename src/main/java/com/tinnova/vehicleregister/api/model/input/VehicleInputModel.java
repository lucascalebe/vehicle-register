package com.tinnova.vehicleregister.api.model.input;

import com.tinnova.vehicleregister.domain.model.VehicleBrand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleInputModel {

  @Schema(example = "ARGO")
  @NotBlank
  private String vehicle;

  @Schema(example = "FIAT")
  @NotNull
  private VehicleBrand brand;

  @Schema(example = "2020")
  @NotNull
  @Positive
  private Integer year;

  @Schema(example = "Brand new")
  private String description;

  @Schema(example = "false")
  @NotNull
  private Boolean sold;
}
