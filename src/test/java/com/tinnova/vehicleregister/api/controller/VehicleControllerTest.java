package com.tinnova.vehicleregister.api.controller;

import com.tinnova.vehicleregister.api.assembler.VehicleInputDisassembler;
import com.tinnova.vehicleregister.api.assembler.VehicleResponseModelAssembler;
import com.tinnova.vehicleregister.api.model.VehicleResponseModel;
import com.tinnova.vehicleregister.domain.model.Vehicle;
import com.tinnova.vehicleregister.domain.model.VehicleBrand;
import com.tinnova.vehicleregister.domain.service.VehicleService;
import com.tinnova.vehicleregister.util.FileUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VehicleController.class)
class VehicleControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private VehicleService vehicleService;

  @MockBean
  private VehicleResponseModelAssembler assembler;

  @MockBean
  private VehicleInputDisassembler disassembler;

  public static final String URI_VEHICLES = "/vehicles/";
  public static final String URI_VEHICLES_ID = "/vehicles/1";


  @BeforeEach
  void setup() {
    when(vehicleService.findAll(any(), any())).thenReturn(vehiclesPage());
    when(vehicleService.findById(any())).thenReturn(vehicle());
    when(assembler.toModel(vehicle())).thenReturn(vehicleResponseModel());
    when(assembler.toCollectionModel(List.of(vehicle()))).thenReturn(List.of(vehicleResponseModel()));
  }

  @Test
  void retrieveAllVehicles() throws Exception {
    MvcResult result = mockMvc
            .perform(get
                    (URI_VEHICLES)
                    .accept(MediaType.APPLICATION_JSON)
            ).andReturn();

    String expectedJson = FileUtil.getFileAsString("json/vehicleResponseModelPage.json");
    String outputInJson = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals(expectedJson, outputInJson, false);
  }

  @Test
  void retrieveVehicleById() throws Exception {
    MvcResult result = mockMvc
            .perform(get
                    (URI_VEHICLES_ID)
                    .accept(MediaType.APPLICATION_JSON)
            ).andReturn();

    String expectedJson = FileUtil.getFileAsString("json/vehicleResponseModel.json");
    String outputInJson = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals(expectedJson, outputInJson, false);
  }

  @Test
  void retrieveVehicleCreated() throws Exception {
    String inputJson = FileUtil.getFileAsString("json/vehicleInputModel.json");

    MvcResult result = mockMvc
            .perform(post
                    (URI_VEHICLES)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(inputJson)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().is(HttpStatus.CREATED.value()))
            .andReturn();

    assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
  }

  @Test
  void retrieveVehicleUpdated() throws Exception {
    String inputJson = FileUtil.getFileAsString("json/vehicleInputModel.json");

    MvcResult result = mockMvc
            .perform(put
                    (URI_VEHICLES_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(inputJson)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().is(HttpStatus.OK.value()))
            .andReturn();

    assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
  }

  @Test
  void retrieveVehicleUpdatedPartial() throws Exception {
    String inputJson = FileUtil.getFileAsString("json/vehicleInputModel.json");

    MvcResult result = mockMvc
            .perform(patch
                    (URI_VEHICLES_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(inputJson)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().is(HttpStatus.OK.value()))
            .andReturn();

    assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
  }

  @Test
  void retrieveVhicleDeleted() throws Exception {
    MvcResult result = mockMvc
            .perform(delete
                    (URI_VEHICLES_ID)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
            .andReturn();

    assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
  }

  private Vehicle vehicle() {
    return Vehicle.builder()
            .id(2L)
            .vehicle("ARGO")
            .brand(VehicleBrand.FIAT)
            .year(2023)
            .description("carro novo")
            .sold(false)
            .build();
  }

  private VehicleResponseModel vehicleResponseModel() {
    return VehicleResponseModel.builder()
            .id(2L)
            .vehicle("ARGO")
            .brand(VehicleBrand.FIAT)
            .year(2023)
            .description("carro novo")
            .sold(false)
            .build();
  }

  private Page<Vehicle> vehiclesPage() {
    return new PageImpl<>(List.of(vehicle()), Pageable.ofSize(20), 1);
  }
}
