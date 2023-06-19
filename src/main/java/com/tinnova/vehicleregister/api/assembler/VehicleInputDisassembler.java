package com.tinnova.vehicleregister.api.assembler;

import com.tinnova.vehicleregister.api.model.input.VehicleInputModel;
import com.tinnova.vehicleregister.domain.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleInputDisassembler {

	private final ModelMapper modelMapper;

	public Vehicle toDomain(VehicleInputModel vehicleInputModel) {
		return modelMapper.map(vehicleInputModel, Vehicle.class);
	}

	public void toDomainObject(VehicleInputModel vehicleInputModel, Vehicle vehicle) {
		modelMapper.map(vehicleInputModel, vehicle);
	}
}

