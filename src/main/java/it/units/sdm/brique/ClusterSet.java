package it.units.sdm.brique;

import com.google.common.collect.Sets;
import org.jgrapht.alg.util.UnionFind;

import java.util.Set;

public class ClusterSet extends UnionFind<Square> {

    Color color;

    public ClusterSet(Set<Square> elements, Color stonesColor) {
        super(elements);
        this.color = stonesColor;
    }

    public void composeClusters() {
        Set<Square> elements = getParentMap().keySet();
        Sets.cartesianProduct(elements, elements).stream().
                filter(e -> Square.manhattanSquareDistance(e.get(0), e.get(1)) == 1).
                forEach(e -> union(e.get(0), e.get(1)));
    }

    public boolean winningPath() {
        return false;
    }
}