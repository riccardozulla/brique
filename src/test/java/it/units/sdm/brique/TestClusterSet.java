package it.units.sdm.brique;

import it.units.sdm.brique.model.ClusterSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;
import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.*;

public abstract class TestClusterSet<T> {

    private final BiPredicate<T, T> alwaysTrue = (element1, element2) -> true;
    private final BiPredicate<T, T> alwaysFalse = (element1, element2) -> false;
    private final ClusterSet<T> clusterSet = new ClusterSet<>(generateElementsSet(3));

    protected abstract Set<T> generateElementsSet(int size);

    @ParameterizedTest
    @CsvSource({"1,", "3", "5"})
    void initializeSingleElementClusters(int size) {
        ClusterSet<T> givenClusterSet = new ClusterSet<>(generateElementsSet(size));
        assertEquals(size, givenClusterSet.numberOfSets());
    }

    @ParameterizedTest
    @CsvSource({"1,", "3", "5"})
    void composeClustersCreatesOneSingleClusterSetWithAlwaysTrueBiPredicate(int size) {
        ClusterSet<T> givenClusterSet = new ClusterSet<>(generateElementsSet(size));
        givenClusterSet.composeClusters(alwaysTrue);
        assertEquals(1, givenClusterSet.numberOfSets());
    }

    @ParameterizedTest
    @CsvSource({"1,", "3", "5"})
    void composeClustersCreatesTwoDisjointClusterSetsWithAlwaysFalseBiPredicate(int size) {
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
