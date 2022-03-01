package it.units.sdm.brique;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStone {
  @Test
  void testNewBlackStone() {
    Stone stone = new Stone(Color.BLACK);
    assertEquals(Color.BLACK, stone.getColor());
  }

  @Test
  void testNewWhiteStone() {
    Stone stone = new Stone(Color.WHITE);
    assertEquals(Color.WHITE, stone.getColor());
  }

  @Test
  void testSetBlackColor() {
    Stone stone = new Stone(Color.WHITE);
    stone.setColor(Color.BLACK);
    assertEquals(Color.BLACK,stone.getColor());
  }

  @Test
  void testSetWhiteColor() {
    Stone stone = new Stone(Color.BLACK);
    stone.setColor(Color.WHITE);
    assertEquals(Color.WHITE, stone.getColor());
  }
}
