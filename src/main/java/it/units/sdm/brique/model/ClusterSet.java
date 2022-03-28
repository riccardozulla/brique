package it.units.sdm.brique.model;

import com.google.common.collect.Sets;
import org.jgrapht.alg.util.UnionFind;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        var clusters = getParentMap().keySet().stream().collect(Collectors.groupingBy(this::find)).values();
        return clusters.stream().anyMatch(this::isEdgeToEdge);
    }

    private boolean isEdgeToEdge(List<Square> cluster) {
        return switch (color) {
            case BLACK -> cluster.stream().anyMatch(Square::isTopEdge) && cluster.stream().anyMatch(Square::isBottomEdge);
            case WHITE -> cluster.stream().anyMatch(Square::isLeftEdge) && cluster.stream().anyMatch(Square::isRightEdge);
        };
    }
}