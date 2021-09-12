package com.example.cats;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CatTests {
  @Test
  void catWithLowercaseNameShouldThrowException() {
      assertThatIllegalArgumentException().isThrownBy(() -> new Cat("tom"));
  }
}
