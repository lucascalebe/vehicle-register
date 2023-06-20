package com.tinnova.vehicleregister.api.controller;

import com.tinnova.vehicleregister.api.assembler.VehicleInputDisassembler;
import com.tinnova.vehicleregister.api.assembler.VehicleResponseModelAssembler;
import com.tinnova.vehicleregister.api.model.VehicleResponseModel;
import com.tinnova.vehicleregister.api.model.input.VehicleInputModel;
import com.tinnova.vehicleregister.domain.model.Vehicle;
import com.tinnova.vehicleregister.domain.repository.VehicleRepository;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class VehicleController {

  private final VehicleService vehicleService;
  private final VehicleRepository vehicleRepository;
  private final VehicleResponseModelAssembler vehicleAssembler;
  private final VehicleInputDisassembler vehicleDisassembler;

  @GetMapping
  public Page<VehicleResponseModel> findAllVehicles(VehicleFilter filter, @PageableDefault(size = 10) Pageable pageable) {
    Page<Vehicle> vehiclePage = vehicleRepository.findAll(VehicleSpecs.usingFilter(filter), pageable);

    List<VehicleResponseModel> vehicleResponseModels = vehicleAssembler.toCollectionModel(vehiclePage.getContent());

    return new PageImpl<>(vehicleResponseModels, pageable, vehiclePage.getTotalElements());
  }

  @GetMapping("/{vehicleId}")
  public VehicleResponseModel findVehicle(@PathVariable Long vehicleId) {
    return vehicleAssembler.toModel(vehicleService.findById(vehicleId));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public VehicleResponseModel addVehicle(@RequestBody @Valid VehicleInputModel vehicleInput) {
    Vehicle vehicle = vehicleDisassembler.toDomain(vehicleInput);
    return vehicleAssembler.toModel(vehicleService.save(vehicle));
  }

  @PutMapping("/{vehicleId}")
  public VehicleResponseModel updateVehicle(@PathVariable Long vehicleId, @RequestBody @Valid VehicleInputModel vehicleInput) {
    Vehicle vehicleFromDb = vehicleService.findById(vehicleId);

    vehicleDisassembler.toDomainObject(vehicleInput, vehicleFromDb);

    return vehicleAssembler.toModel(vehicleService.save(vehicleFromDb));
  }

  @DeleteMapping("/{vehicleId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteVehicle(@PathVariable Long vehicleId) {
    vehicleService.deleteById(vehicleId);
  }
}
