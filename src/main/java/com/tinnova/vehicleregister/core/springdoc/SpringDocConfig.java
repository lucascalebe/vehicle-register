package com.tinnova.vehicleregister.core.springdoc;

import com.tinnova.vehicleregister.api.exceptionhandler.Problem;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Configuration
public class SpringDocConfig {

  private static final String BAD_REQUEST_RESPONSE = "BadRequestResponse";
  private static final String NOT_FOUND_RESPONSE = "NotFoundResponse";
  private static final String NOT_ACCEPTABLE_RESPONSE = "NotAcceptableResponse";
  private static final String INTERNAL_SERVER_ERROR_RESPONSE = "InternalServerErrorResponse";

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
            .info(new Info()
                    .title("Vehicle Register API")
                    .version("v1")
                    .description("REST API to register vehicles")
                    .license(new License()
                            .name("Apache 2.0")
                            .url("http://springdoc.com")))
            .tags(
                    Collections.singletonList(
                            new Tag().name("Vehicles").description("Handle vehicles registration")
                    ))
            .components(
                    new Components()
                            .schemas(generateSchemas())
                            .responses(generateResponses()));
  }


  @Bean
  public OpenApiCustomiser openApiCustomiser() {
    return openApi -> openApi.getPaths()
            .values()
            .forEach(pathItem -> pathItem.readOperationsMap()
                    .forEach((httpMethod, operation) -> {
                      ApiResponses responses = operation.getResponses();
                      switch (httpMethod) {
                        case GET:
                          responses.addApiResponse("406", new ApiResponse().$ref(NOT_ACCEPTABLE_RESPONSE));
                          responses.addApiResponse("500", new ApiResponse().$ref(INTERNAL_SERVER_ERROR_RESPONSE));
                          break;
                        case POST:
                          responses.addApiResponse("400", new ApiResponse().$ref(BAD_REQUEST_RESPONSE));
                          responses.addApiResponse("500", new ApiResponse().$ref(INTERNAL_SERVER_ERROR_RESPONSE));
                          break;
                        case PUT:
                          responses.addApiResponse("400", new ApiResponse().$ref(BAD_REQUEST_RESPONSE));
                          responses.addApiResponse("500", new ApiResponse().$ref(INTERNAL_SERVER_ERROR_RESPONSE));
                          break;
                        case DELETE:
                          responses.addApiResponse("500", new ApiResponse().$ref(INTERNAL_SERVER_ERROR_RESPONSE));
                          break;
                        default:
                          responses.addApiResponse("500", new ApiResponse().$ref(INTERNAL_SERVER_ERROR_RESPONSE));
                          break;
                      }
                    })
            );
  }

  private Map<String, Schema> generateSchemas() {
    final Map<String, Schema> schemaMap = new HashMap<>();

    Map<String, Schema> problemSchema = ModelConverters.getInstance().read(Problem.class);
    Map<String, Schema> problemObjectSchema = ModelConverters.getInstance().read(Problem.Object.class);

    schemaMap.putAll(problemSchema);
    schemaMap.putAll(problemObjectSchema);

    return schemaMap;
  }

  private Map<String, ApiResponse> generateResponses() {
    final Map<String, ApiResponse> apiResponseMap = new HashMap<>();

    Content content = new Content()
            .addMediaType(APPLICATION_JSON_VALUE,
                    new MediaType().schema(new Schema<Problem>().$ref("Problem")));

    apiResponseMap.put(BAD_REQUEST_RESPONSE, new ApiResponse()
            .description("Invalid Request")
            .content(content));

    apiResponseMap.put(NOT_FOUND_RESPONSE, new ApiResponse()
            .description("Resource not found")
            .content(content));

    apiResponseMap.put(NOT_ACCEPTABLE_RESPONSE, new ApiResponse()
            .description("Resource there's no presentation accepted by consumer")
            .content(content));

    apiResponseMap.put(INTERNAL_SERVER_ERROR_RESPONSE, new ApiResponse()
            .description("Internal Server Errorr")
            .content(content));

    return apiResponseMap;
  }

}
