package com.tinnova.vehicleregister.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
@Schema(name = "Problem")
public class Problem {

  @Schema(example = "400")
  private Integer status;

  @Schema(example = "https://vehicleregister.com.br/invalid-data")
  private String type;

  @Schema(example = "Invalid Data")
  private String title;

  @Schema(example = "One or more fiels are invalid.")
  private String detail;

  @Schema(example = "One or more fiels are invalid.")
  private String userMessage;

  @Schema(example = "2023-01-01T11:00:00.902245498Z")
  private OffsetDateTime timestamp;

  @Schema(description = "List of objects or fields with errors")
  private List<Object> objects;

  @Getter
  @Builder
  public static class Object {

    @Schema(example = "objectProblem")
    private String name;

    @Schema(example = "Brand is invalid")
    private String userMessage;
  }
}
