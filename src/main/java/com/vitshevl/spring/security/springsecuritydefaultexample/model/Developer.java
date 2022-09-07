package com.vitshevl.spring.security.springsecuritydefaultexample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Developer model.
 */

@Data
@Builder
@AllArgsConstructor
public class Developer {
  private Long id;
  private String firstName;
  private String lastName;
}
