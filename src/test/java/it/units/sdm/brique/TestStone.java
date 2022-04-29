package it.units.sdm.brique;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Stone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStone {

  Stone stone = new Stone(Color.WHITE);
  Stone otherStone = new Stone(Color.BLACK);

  @ParameterizedTest
  @EnumSource(value = Color.class)
  void testNewStoneColor(Color color) {
    Stone stone = new Stone(color);
    assertEquals(color, stone.getColor());
  }

  @ParameterizedTest
  @EnumSource(value = Color.class)
  void testSetColor(Color color) {
    stone.setColor(color);
    assertEquals(color, stone.getColor());
  }

  @Test
  void testSwitchColorTurnsWhiteIntoBlack() {
    stone.switchColor();
    assertEquals(Color.BLACK, stone.getColor());
  }

  @Test
  void testSwitchColorTurnsBlackIntoWhite() {
    otherStone.switchColor();
    assertEquals(Color.WHITE, otherStone.getColor());
  }
}
