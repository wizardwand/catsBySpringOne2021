package com.example.cats;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat,Integer> {
  Cat findByName(String name);
}
