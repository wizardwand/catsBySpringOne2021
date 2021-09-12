package com.example.cats;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatRestController {

  private final CatRepository repository;

  public CatRestController(CatRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/cats/{name}")
  Cat byName(@PathVariable String name) {
    return this.repository.findByName(name);
  }
}
