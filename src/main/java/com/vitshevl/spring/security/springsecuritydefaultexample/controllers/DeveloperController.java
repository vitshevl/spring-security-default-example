package com.vitshevl.spring.security.springsecuritydefaultexample.controllers;

import com.vitshevl.spring.security.springsecuritydefaultexample.controllers.dto.DeveloperDto;
import com.vitshevl.spring.security.springsecuritydefaultexample.model.Developer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.vitshevl.spring.security.springsecuritydefaultexample.util.PropertyConstants.DEVELOPER_CONTROLLER_ENDPOINTS_TAG;
import static com.vitshevl.spring.security.springsecuritydefaultexample.util.PropertyConstants.ROOT_UI_URL;

/**
 * Controller for working with developers.
 */

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = ROOT_UI_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = DEVELOPER_CONTROLLER_ENDPOINTS_TAG)
public class DeveloperController {

  private final MapperFacade mapper;

  private static final List<Developer> DEVELOPERS =
      Stream.of(
              Developer.builder().id(1L).firstName("Ivan").lastName("Ivanov").build(),
              Developer.builder().id(2L).firstName("Sergey").lastName("Sergeev").build(),
              Developer.builder().id(3L).firstName("Petr").lastName("Petrov").build())
          .collect(Collectors.toList());

  /**
   * Getting developer list
   *
   * @return developer list
   */
  @GetMapping
  @Operation(summary = "Getting developer list")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "401", description = "Access denied"),
        @ApiResponse(responseCode = "500", description = "Internal server error"),
      })
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<DeveloperDto>> getAllDevelopers() {
    return ResponseEntity.ok(mapper.mapAsList(DEVELOPERS, DeveloperDto.class));
  }

  /**
   * Getting developer by id
   *
   * @return developer
   */
  @GetMapping("/{id}")
  @Operation(summary = "Getting developer by id")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "401", description = "Access denied"),
        @ApiResponse(responseCode = "404", description = "Not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error"),
      })
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<DeveloperDto> getDeveloperById(@PathVariable @NonNull Long id) {

    return DEVELOPERS.stream()
        .filter(developer -> developer.getId().equals(id))
        .findFirst()
        .map(developer -> ResponseEntity.ok().body(mapper.map(developer, DeveloperDto.class)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
