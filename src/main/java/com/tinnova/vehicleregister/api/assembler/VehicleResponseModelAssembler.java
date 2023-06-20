package com.tinnova.vehicleregister.api.assembler;

import com.tinnova.vehicleregister.api.model.VehicleResponseModel;
import com.tinnova.vehicleregister.domain.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VehicleResponseModelAssembler {

  private final ModelMapper modelMapper;

  public VehicleResponseModel toModel(Vehicle vehicle) {
    return modelMapper.map(vehicle, VehicleResponseModel.class);
  }

  public List<VehicleResponseModel> toCollectionModel(List<Vehicle> vehicles) {
    return vehicles.stream().map(this::toModel).collect(Collectors.toList());
  }
}
