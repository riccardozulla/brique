package it.units.sdm.brique.model;

import com.google.common.collect.Sets;
import org.jgrapht.alg.util.UnionFind;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ClusterSet extends UnionFind<Square> {

    final Color color;

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

    public Collection<Set<Square>> getClusters() {
        return getParentMap().keySet().stream().collect(Collectors.groupingBy(this::find, Collectors.toSet())).values();
    }

    public boolean anyClusterMatches(Predicate<Set<Square>> evaluationFunction) {
        return getClusters().stream().anyMatch(evaluationFunction);
    }
}