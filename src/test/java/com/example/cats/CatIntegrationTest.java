package com.example.cats;

import org.assertj.core.api.Assertions;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(properties = "spring.sql.init.mode=always",webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class CatIntegrationTest {
  @Container static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres");

  @Autowired private TestRestTemplate restTemplate;

  @DynamicPropertySource
  static void dynamicSource(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", () -> db.getJdbcUrl());
    registry.add("spring.datasource.username", () -> db.getUsername());
    registry.add("spring.datasource.password", () -> db.getPassword());
    registry.add("spring.jpa.database-platform.", PostgreSQL9Dialect.class::getName);
  }

  @Test
  void getCatByNameReturnsCat() {
    final Cat toby = this.restTemplate.getForObject("/cats/{name}", Cat.class, "Toby");
    Assertions.assertThat(toby.getName()).isEqualTo("Toby");
  }
}
