package it.units.sdm.brique;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStone {

  Stone stone = new Stone(0,0, Color.WHITE);

  @ParameterizedTest
  @EnumSource(value = Color.class)
  void testSetColor(Color color) {
    stone.setColor(color);
    assertEquals(color, stone.getColor());
  }

  @Test
  void testDistanceMethodBetweenTwoStonesPlacedOnDiagonal(){
    assertEquals(2, stone.distance(new Stone(1,1,Color.WHITE)));
  }

  @Test
  void testDistanceMethodBetweenTwoOrthogonalStones() {
    assertEquals(1, stone.distance(new Stone(0,1,Color.WHITE)));
  }

  @Test
  void testDistanceStoneBetweenTwoDistantStones() {
    assertEquals(5, stone.distance(new Stone(2,3,Color.WHITE)));
  }
}
