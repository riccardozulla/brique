package it.units.sdm.brique;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestClusterSet {

  Square s1 = new Square(new Coordinate(1, 1), Color.WHITE);
  Square s2 = new Square(new Coordinate(1, 2), Color.BLACK);

  ClusterSet clusterSet = new ClusterSet(Set.of(s1, s2), Color.BLACK);


  @Test
  void initializeSingleSquareClusters() {
    assertEquals(2, clusterSet.numberOfSets());
  }

  @Test
  void composeClusters() {
    clusterSet.composeClusters();
    assertEquals(1, clusterSet.numberOfSets());
  }

  @Test
  void winningPath(){
    Square s1 = new Square(new Coordinate(1, 0), Color.WHITE);
    Square s2 = new Square(new Coordinate(1, 1), Color.WHITE);
    Square s3 = new Square(new Coordinate(1, 2), Color.BLACK);
    Square s4 = new Square(new Coordinate(1, 3), Color.WHITE);
    Square s5 = new Square(new Coordinate(1, 4), Color.BLACK);
    Square s6 = new Square(new Coordinate(1, 5), Color.WHITE);
    Square s7 = new Square(new Coordinate(1, 6), Color.BLACK);
    Square s8 = new Square(new Coordinate(1, 7), Color.WHITE);
    Square s9 = new Square(new Coordinate(1, 8), Color.BLACK);
    Square s10 = new Square(new Coordinate(1, 9), Color.BLACK);
    Square s11 = new Square(new Coordinate(1, 10), Color.BLACK);
    Square s12 = new Square(new Coordinate(1, 11), Color.BLACK);
    Square s13 = new Square(new Coordinate(1, 12), Color.BLACK);
    Square s14 = new Square(new Coordinate(1, 13), Color.BLACK);
    Square s15 = new Square(new Coordinate(1, 14), Color.BLACK);
    ClusterSet clusterSet = new ClusterSet(Set.of(s1, s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15), Color.WHITE);
    clusterSet.composeClusters();
    assertEquals(1, clusterSet.numberOfSets());
    assertTrue(clusterSet.winningPath());
    //a
  }
}
