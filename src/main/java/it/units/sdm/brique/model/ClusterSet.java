package it.units.sdm.brique.model;

import com.google.common.collect.Sets;
import org.jgrapht.alg.util.UnionFind;

import java.util.Collection;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ClusterSet<T> extends UnionFind<T> {

    public ClusterSet(Set<T> elements) {
        super(elements);
    }

    public void composeClusters(BiPredicate<T, T> belongToSameCluster) {
        var elements = getParentMap().keySet();
        Sets.cartesianProduct(elements, elements).stream().
                filter(pair -> belongToSameCluster.test(pair.get(0), pair.get(1))).
                forEach(pair -> union(pair.get(0), pair.get(1)));
    }

    public Collection<Set<T>> getClusters() {
        return getParentMap().keySet().stream().collect(Collectors.groupingBy(this::find, Collectors.toSet())).values();
    }

    public boolean anyClusterMatches(Predicate<Set<T>> evaluationPredicate) {
        return getClusters().stream().anyMatch(evaluationPredicate);
    }
}