package com.tinnova.vehicleregister.domain.service.impl;

import com.tinnova.vehicleregister.domain.exception.VehicleNotFoundException;
import com.tinnova.vehicleregister.domain.model.Vehicle;
import com.tinnova.vehicleregister.domain.repository.VehicleRepository;
import com.tinnova.vehicleregister.domain.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

  public final VehicleRepository vehicleRepository;

  @Override
  public Page<Vehicle> findAll(Specification<Vehicle> spec, Pageable pageable) {
    return vehicleRepository.findAll(spec, pageable);
  }

  @Override
  public Vehicle findById(Long vehicleId) {
    return vehicleRepository.findById(vehicleId)
            .orElseThrow(() -> new VehicleNotFoundException(vehicleId));
  }

  @Override
  @Transactional
  public Vehicle save(Vehicle vehicle) {
    return vehicleRepository.save(vehicle);
  }

  @Override
  @Transactional
  public void deleteById(Long vehicleId) {
    try {
      vehicleRepository.deleteById(vehicleId);
      vehicleRepository.flush();

    } catch (EmptyResultDataAccessException e) {
      throw new VehicleNotFoundException(vehicleId);
    }
  }
}
