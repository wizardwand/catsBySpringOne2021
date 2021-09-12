package com.example.cats;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
class CatsControllerTest {
  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private CatRepository repository;

  @Test
  void getCatWithNameShouldReturnCat() throws Exception {
    final String expectedTobyValue = this.objectMapper.writeValueAsString(new Cat("Toby"));
    BDDMockito.given(repository.findByName("Toby")).willReturn(new Cat("Toby"));
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/cats/{name}", "Toby"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(content().string(expectedTobyValue));
  }
}
