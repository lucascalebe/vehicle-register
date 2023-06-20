package com.tinnova.vehicleregister.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinnova.vehicleregister.api.assembler.VehicleInputDisassembler;
import com.tinnova.vehicleregister.api.assembler.VehicleResponseModelAssembler;
import com.tinnova.vehicleregister.api.model.VehicleResponseModel;
import com.tinnova.vehicleregister.api.model.input.VehicleInputModel;
import com.tinnova.vehicleregister.api.openapi.controller.VehicleControllerOpenApi;
import com.tinnova.vehicleregister.domain.exception.BusinessException;
import com.tinnova.vehicleregister.domain.model.Vehicle;
import com.tinnova.vehicleregister.domain.repository.filter.VehicleFilter;
import com.tinnova.vehicleregister.domain.service.VehicleService;
import com.tinnova.vehicleregister.infrastructure.repository.spec.VehicleSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class VehicleController implements VehicleControllerOpenApi {

  private final VehicleService vehicleService;
  private final VehicleResponseModelAssembler vehicleAssembler;
  private final VehicleInputDisassembler vehicleDisassembler;

  @Override
  @GetMapping
  public Page<VehicleResponseModel> findAllVehicles(VehicleFilter filter, @PageableDefault(size = 10) Pageable pageable) {
    Page<Vehicle> vehiclePage = vehicleService.findAll(VehicleSpecs.usingFilter(filter), pageable);

    List<VehicleResponseModel> vehicleResponseModels = vehicleAssembler.toCollectionModel(vehiclePage.getContent());

    return new PageImpl<>(vehicleResponseModels, pageable, vehiclePage.getTotalElements());
  }

  @Override
  @GetMapping("/{vehicleId}")
  public VehicleResponseModel findVehicle(@PathVariable Long vehicleId) {
    return vehicleAssembler.toModel(vehicleService.findById(vehicleId));
  }

  @Override
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public VehicleResponseModel addVehicle(@RequestBody @Valid VehicleInputModel vehicleInput) {
    Vehicle vehicle = vehicleDisassembler.toDomain(vehicleInput);
    return vehicleAssembler.toModel(vehicleService.save(vehicle));
  }

  @Override
  @PutMapping("/{vehicleId}")
  public VehicleResponseModel updateVehicle(@PathVariable Long vehicleId, @RequestBody @Valid VehicleInputModel vehicleInput) {
    Vehicle vehicleFromDb = vehicleService.findById(vehicleId);

    vehicleDisassembler.toDomainObject(vehicleInput, vehicleFromDb);

    return vehicleAssembler.toModel(vehicleService.save(vehicleFromDb));
  }

  @Override
  @PatchMapping("/{vehicleId}")
  public VehicleResponseModel partialUpdateVehicle(@PathVariable Long vehicleId, @RequestBody Map<String, Object> fields) {
    Vehicle vehicle = vehicleService.findById(vehicleId);

    mergeFields(fields, vehicle);

    return vehicleAssembler.toModel(vehicleService.save(vehicle));
  }

  @Override
  @DeleteMapping("/{vehicleId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteVehicle(@PathVariable Long vehicleId) {
    vehicleService.deleteById(vehicleId);
  }

  private void mergeFields(Map<String, Object> fieldsOrigin, Vehicle vehicleTarget) {
    ObjectMapper objectMapper = new ObjectMapper();
    Vehicle vehicleOrigin = objectMapper.convertValue(fieldsOrigin, Vehicle.class);

    fieldsOrigin.forEach((propertyName, propertyValue) -> {
      if (propertyName.equals("id")) {
        throw new BusinessException("The id can not be changed.");
      }

      Field field = ReflectionUtils.findField(Vehicle.class, propertyName);
      field.setAccessible(true);

      Object newValue = ReflectionUtils.getField(field, vehicleOrigin);

      ReflectionUtils.setField(field, vehicleTarget, newValue);
    });
  }
}
