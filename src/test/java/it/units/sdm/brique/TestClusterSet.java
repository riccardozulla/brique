package it.units.sdm.brique;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestClusterSet {

  Square s1 = new Square(1, 1);
  Square s2 = new Square(1, 2);

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
    Square s1 = new Square(1, 0);
    Square s2 = new Square(1,1);
    Square s3 = new Square(1,2);
    Square s4 = new Square(1,3);
    Square s5 = new Square(1,4);
    Square s6 = new Square(1,5);
    Square s7 = new Square(1,6);
    Square s8 = new Square(1,7);
    Square s9 = new Square(1,8);
    Square s10 = new Square(1,9);
    Square s12 = new Square(1,10);
    Square s13 = new Square(1,11);
    Square s14 = new Square(1,12);
    Square s15 = new Square(1,13);
    Square s11 = new Square(1,14);
    ClusterSet clusterSet = new ClusterSet(Set.of(s1, s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15), Color.WHITE);
    clusterSet.composeClusters();
    assertEquals(1, clusterSet.numberOfSets());
    assertTrue(clusterSet.winningPath());
  }
}
