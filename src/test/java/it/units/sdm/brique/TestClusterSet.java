package it.units.sdm.brique;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClusterSet {

  Square s1 = new Square(new Coordinate(1, 1), Color.WHITE);
  Square s2 = new Square(new Coordinate(1, 2), Color.BLACK);

  ClusterSet clusterSet = new ClusterSet(Set.of(s1, s2), Color.BLACK);


  @Test
  void initializeSingleSquareClusters() {
    assertEquals(2, clusterSet.numberOfSets());
  }
}
