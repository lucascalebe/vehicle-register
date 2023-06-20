package com.tinnova.vehicleregister.api.assembler;

import com.tinnova.vehicleregister.api.model.VehicleResponseModel;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.tinnova.vehicleregister.api.model.input.VehicleInputModel;
import com.tinnova.vehicleregister.domain.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VehicleResponseModelAssemblerTest {

  @InjectMocks
  private VehicleResponseModelAssembler assembler;

  @Mock
  private ModelMapper modelMapper;

  @Test
  void shouldConvertToModel() {
    var vehicle = new Vehicle();
    assembler.toModel(vehicle);

    verify(modelMapper).map(vehicle, VehicleResponseModel.class);
  }

  @Test
  void shouldConvertToCollectionModel() {
    var vehicle = new Vehicle();

    assembler.toCollectionModel(List.of(vehicle));

    verify(modelMapper).map(vehicle, VehicleResponseModel.class);
  }
}
