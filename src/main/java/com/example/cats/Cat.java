package com.example.cats;

import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cat {
  @Id @GeneratedValue private Integer id;

  public Cat() {
  }

  private String name;

  public Cat(String name) {
    Assert.isTrue(
        Character.isUpperCase(name.charAt(0)),
        () -> "first character of hte name must be Uppercase");
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
