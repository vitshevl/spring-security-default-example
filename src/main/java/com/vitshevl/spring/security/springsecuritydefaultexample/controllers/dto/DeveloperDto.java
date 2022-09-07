package com.vitshevl.spring.security.springsecuritydefaultexample.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Developer DTO.
 */

@Data
@Accessors(chain = true)
@Schema(description = "Developer DTO")
public class DeveloperDto {

  @Schema(description = "User's id", example = "123")
  private Long id;

  @Schema(description = "User's first name ", example = "Nikola")
  private String firstName;

  @Schema(description = "User's first name ", example = "Tesla")
  private String lastName;
}
