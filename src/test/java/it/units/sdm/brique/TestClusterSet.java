package it.units.sdm.brique;

import it.units.sdm.brique.model.ClusterSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;
import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.*;

public abstract class TestClusterSet<T> {

    private final ClusterSet<T> clusterSet = new ClusterSet<>(generateElementsSet(3));

    protected abstract Set<T> generateElementsSet(int size);

    @ParameterizedTest
    @CsvSource({"1,", "3", "5"})
    void isComposedOfSingleElementClustersBeforeComposition(int size) {
        ClusterSet<T> givenClusterSet = new ClusterSet<>(generateElementsSet(size));
        assertTrue(givenClusterSet.getClusters().stream().mapToInt(Set::size).allMatch(clusterSize -> clusterSize == 1));
    }

    @ParameterizedTest
    @CsvSource({"1,", "3", "5"})
    void composeClustersCreatesOneSingleClusterSetWithAlwaysTrueBiPredicate(int size) {
        BiPredicate<T, T> alwaysTrue = (element1, element2) -> true;
        ClusterSet<T> givenClusterSet = new ClusterSet<>(generateElementsSet(size));
        givenClusterSet.composeClusters(alwaysTrue);
        assertEquals(1, givenClusterSet.numberOfSets());
    }

    @ParameterizedTest
    @CsvSource({"1,", "3", "5"})
    void composeClustersCreatesTwoDisjointClusterSetsWithAlwaysFalseBiPredicate(int size) {
        BiPredicate<T, T> alwaysFalse = (element1, element2) -> false;
        ClusterSet<T> givenClusterSet = new ClusterSet<>(generateElementsSet(size));
        givenClusterSet.composeClusters(alwaysFalse);
        assertEquals(size, givenClusterSet.numberOfSets());
    }

    @Test
    void anyClusterMatchesReturnsTrueWithAlwaysTruePredicate() {
        assertTrue(clusterSet.anyClusterMatches(cluster -> true));
    }

    @Test
    void anyClusterMatchesReturnsFalseWithAlwaysFalsePredicate() {
        assertFalse(clusterSet.anyClusterMatches(cluster -> false));
    }
}
