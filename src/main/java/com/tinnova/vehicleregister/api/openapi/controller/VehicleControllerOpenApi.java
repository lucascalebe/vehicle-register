package com.tinnova.vehicleregister.api.openapi.controller;

import com.tinnova.vehicleregister.api.model.VehicleResponseModel;
import com.tinnova.vehicleregister.api.model.input.VehicleInputModel;
import com.tinnova.vehicleregister.domain.repository.filter.VehicleFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

import java.util.Map;

@Tag(name = "Vehicles")
public interface VehicleControllerOpenApi {

  @Operation(summary = "List all vehicles with page and filters")
  Page<VehicleResponseModel> findAllVehicles(VehicleFilter filter, Pageable pageable);

  @Operation(summary = "Find a vehicle by id")
  VehicleResponseModel findVehicle(@Parameter(example = "1") Long vehicleId);

  @Operation(summary = "Add a new Vehicle",
  responses = {
          @ApiResponse(responseCode = "201"),
          @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(ref = "Problem"))),
  })
  VehicleResponseModel addVehicle(@RequestBody(description = "Representation of a new vehicle", required = true) VehicleInputModel vehicleInput);

  @Operation(summary = "Entire updating of a vehicle")
  VehicleResponseModel updateVehicle(@Parameter(example = "1") Long vehicleId,
                                     @RequestBody(description = "Representation of a new vehicle", required = true) VehicleInputModel vehicleInput);

  @Operation(summary = "Partial updating of a vehicle")
  VehicleResponseModel partialUpdateVehicle(@Parameter(example = "1") Long vehicleId,
                                            @RequestBody(description = "fields to be updated",
                                                    required = true,
                                                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                            examples = @ExampleObject(value = "{\"sold\": true}")))
                                            Map<String, Object> fields);

  @Operation(summary = "Delete a vehicle")
  void deleteVehicle(@Parameter(example = "1") Long vehicleId);
}
