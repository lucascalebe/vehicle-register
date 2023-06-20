package com.tinnova.vehicleregister.api.assembler;

import com.tinnova.vehicleregister.api.model.input.VehicleInputModel;
import com.tinnova.vehicleregister.domain.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VehicleInputDisassemblerTest {

  @InjectMocks
  private VehicleInputDisassembler disassembler;

  @Mock
  private ModelMapper modelMapper;

  @Test
  void shouldConvertToDomain() {
    var vehicleInputModel = new VehicleInputModel();
    disassembler.toDomain(vehicleInputModel);

    verify(modelMapper).map(vehicleInputModel, Vehicle.class);
  }

  @Test
  void shouldConvertToDomainObject() {
    var vehicleInputModel = new VehicleInputModel();
    var vehicle = new Vehicle();

    disassembler.toDomainObject(vehicleInputModel, vehicle);

    verify(modelMapper).map(vehicleInputModel, vehicle);
  }
}
