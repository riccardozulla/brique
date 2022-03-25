package it.units.sdm.brique;

import org.jgrapht.alg.util.UnionFind;

import java.util.Set;

public class ClusterSet extends UnionFind<Square> {

  Color color;

  public ClusterSet(Set<Square> elements, Color stonesColor) {
    super(elements);
    this.color = stonesColor;
  }

  public void composeClusters() {
  }
}