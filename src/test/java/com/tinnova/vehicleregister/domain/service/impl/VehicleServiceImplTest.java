package com.tinnova.vehicleregister.domain.service.impl;

import com.tinnova.vehicleregister.domain.exception.VehicleNotFoundException;
import com.tinnova.vehicleregister.domain.model.Vehicle;
import com.tinnova.vehicleregister.domain.repository.VehicleRepository;
import com.tinnova.vehicleregister.domain.repository.filter.VehicleFilter;
import com.tinnova.vehicleregister.infrastructure.repository.spec.VehicleSpecs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {

  @InjectMocks
  private VehicleServiceImpl service;

  @Mock
  private VehicleRepository repository;


  @Test
  void shouldCallFindAll() {
    var pageable = PageRequest.of(1, 1, Sort.by(Sort.Direction.DESC, "captureDate"));

    service.findAll(VehicleSpecs.usingFilter(new VehicleFilter()), pageable);

    verify(repository).findAll(VehicleSpecs.usingFilter(any()), (Pageable) any());
  }

  @Test
  void shouldCallFindById() {
    when(repository.findById(1L)).thenReturn(Optional.of(new Vehicle()));

    service.findById(1L);

    verify(repository).findById(1L);
  }

  @Test
  void shouldThrowExceptionWhenNotFound() {
    assertThatThrownBy(() -> service.findById(1L))
            .isInstanceOf(VehicleNotFoundException.class)
            .hasMessage("Vehicle with id 1 does not exist");
  }

  @Test
  void shouldCallSave() {
    service.save(new Vehicle());

    verify(repository).save(any());
  }

  @Test
  void shouldCallDelete() {
    service.deleteById(1L);

    verify(repository).deleteById(any());
  }

  @Test
  void shouldThrowExceptionWhenDelete() {
    doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(1L);

    assertThatThrownBy(() -> service.deleteById(1L))
            .isInstanceOf(VehicleNotFoundException.class)
            .hasMessage("Vehicle with id 1 does not exist");
  }
}
