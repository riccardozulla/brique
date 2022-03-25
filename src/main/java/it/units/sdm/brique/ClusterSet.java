package it.units.sdm.brique;

import com.google.common.collect.Sets;
import org.jgrapht.alg.util.UnionFind;

import java.util.List;
import java.util.Map;
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
        var clusters = getParentMap().entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue)).values();
        return clusters.stream().anyMatch(cluster -> isEdgeToEdge(cluster));
    }

    private boolean isEdgeToEdge(List<Map.Entry<Square, Square>> cluster) {
        return switch (color) {
            case BLACK -> cluster.stream().map(Map.Entry::getKey).anyMatch(Square::isTopEdge) && cluster.stream().map(Map.Entry::getKey).anyMatch(Square::isBottomEdge);
            case WHITE -> cluster.stream().map(Map.Entry::getKey).anyMatch(Square::isLeftEdge) && cluster.stream().map(Map.Entry::getKey).anyMatch(Square::isRightEdge);
        };
    }
}