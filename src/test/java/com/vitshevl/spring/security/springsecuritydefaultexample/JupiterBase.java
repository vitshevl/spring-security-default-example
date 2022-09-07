package com.vitshevl.spring.security.springsecuritydefaultexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 * Base class for tests. It has common components and logs start and end of tests.
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public abstract class JupiterBase {
  private static final String MDC_ID = "testName";

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper;

  @BeforeEach
  void startUp(TestInfo info) {
    MDC.put(MDC_ID, info.getTestMethod().map(Method::getName).orElseGet(() -> ""));
    logInfo(info, log -> log.info("START: \"{}\"", info.getDisplayName()));
  }

  @AfterEach
  void shutdown(TestInfo info) {
    logInfo(info, log -> log.info("END:  \"{}\"", info.getDisplayName()));
    MDC.remove(MDC_ID);
  }

  private static void logInfo(TestInfo info, Consumer<Logger> logFun) {
    info.getTestClass().map(LoggerFactory::getLogger).ifPresent(logFun);
  }
}
