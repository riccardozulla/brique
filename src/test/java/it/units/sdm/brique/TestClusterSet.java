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

    @ParameterizedTest
    @CsvSource({"1,", "3", "5"})
    void initializeSingleSquareClusters(int size) {
        ClusterSet<T> givenClusterSet = new ClusterSet<>(generateElementsSet(size));
        assertEquals(size, givenClusterSet.numberOfSets());
    }

    protected abstract Set<T> generateElementsSet(int elementsNumber);

    @ParameterizedTest
    @CsvSource({"1,", "3", "5"})
    void composeClustersCreatesOneSingleClusterSetWithAlwaysTrueBiPredicate(int elementsNumber) {
        ClusterSet<T> givenClusterSet = new ClusterSet<>(generateElementsSet(elementsNumber));
        givenClusterSet.composeClusters(alwaysTrue);
        assertEquals(1, givenClusterSet.numberOfSets());
    }

    @ParameterizedTest
    @CsvSource({"1,", "3", "5"})
    void composeClustersCreatesTwoDisjointClusterSetsWithAlwaysFalseBiPredicate(int elementsNumber) {
        ClusterSet<T> givenClusterSet = new ClusterSet<>(generateElementsSet(elementsNumber));
        givenClusterSet.composeClusters(alwaysFalse);
        assertEquals(elementsNumber, givenClusterSet.numberOfSets());
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
