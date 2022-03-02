package it.units.sdm.brique;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStone {
  @ParameterizedTest
  @EnumSource(value = Color.class)
  void testNewStoneColor(Color color) {
    Stone stone = new Stone(color);
    assertEquals(color, stone.getColor());
  }

  @Test
  void testSetBlackColor() {
    Stone stone = new Stone(Color.WHITE);
    stone.setColor(Color.BLACK);
    assertEquals(Color.BLACK, stone.getColor());
  }

  @Test
  void testSetWhiteColor() {
    Stone stone = new Stone(Color.BLACK);
    stone.setColor(Color.WHITE);
    assertEquals(Color.WHITE, stone.getColor());
  }
}
