package com.vitshevl.spring.security.springsecuritydefaultexample.controllers;

import com.vitshevl.spring.security.springsecuritydefaultexample.JupiterBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@DisplayName("TestMainControllerIT")
public class TestDeveloperControllerIT extends JupiterBase {

  @Test
  @WithMockUser("user")
  @DisplayName("Should return all developers")
  void testGetAllDevelopers() throws Exception {

    mockMvc
        .perform(
            get("/api/v1/developers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[*]", hasSize(3)))
        .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2, 3)))
        .andExpect(jsonPath("$[*].firstName").isNotEmpty())
        .andExpect(jsonPath("$[*].lastName").isNotEmpty());
  }

  @Test
  @WithMockUser("user")
  @DisplayName("Should return developer by id")
  void testGetDeveloperById() throws Exception {

    mockMvc
        .perform(
            get("/api/v1/developers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.firstName", is("Ivan")))
        .andExpect(jsonPath("$.lastName", is("Ivanov")));
  }

  @Test
  @WithMockUser("user")
  @DisplayName("Should return Not found status for get developer by wrong id")
  void testGetDeveloperByWrongId() throws Exception {

    mockMvc
        .perform(
            get("/api/v1/developers/12345")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Should return Unauthorized status for get all developers")
  void testGetAllDevelopersWithUnauthorizedUser() throws Exception {

    mockMvc
        .perform(
            get("/api/v1/developers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized());
  }

  @Test
  @DisplayName("Should return Unauthorized status for developer by id")
  void testGetDeveloperByIdWithUnauthorizedUser() throws Exception {

    mockMvc
        .perform(
            get("/api/v1/developers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized());
  }
}
