package it.units.sdm.brique;

import com.google.common.base.Predicates;
import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.ClusterSet;
import it.units.sdm.brique.model.Square;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TestClusterSet {

    private static final BiPredicate<Square, Square> alwaysTrue = (square1, square2) -> true;
    private static final BiPredicate<Square, Square> alwaysFalse = (square1, square2) -> false;
    private final ClusterSet<Square> clusterSet = new ClusterSet<>(generateElementsSet(3));

    @ParameterizedTest
    @CsvSource({"1,", "3", "5"})
    void initializeSingleSquareClusters(int size) {
        ClusterSet<Square> givenClusterSet = new ClusterSet<>(generateElementsSet(size));
        assertEquals(size, givenClusterSet.numberOfSets());
    }

    private Set<Square> generateElementsSet(int elementsNumber) {
        return Board.getInstance().getSquaresStream().limit(elementsNumber).collect(Collectors.toSet());
    }

    @ParameterizedTest
    @CsvSource({"1,", "3", "5"})
    void composeClustersCreatesOneSingleClusterSetWithAlwaysTrueBiPredicate(int elementsNumber) {
        ClusterSet<Square> givenClusterSet = new ClusterSet<>(generateElementsSet(elementsNumber));
        givenClusterSet.composeClusters(alwaysTrue);
        assertEquals(1, givenClusterSet.numberOfSets());
    }

    @ParameterizedTest
    @CsvSource({"1,", "3", "5"})
    void composeClustersCreatesTwoDisjointClusterSetsWithAlwaysFalseBiPredicate(int elementsNumber) {
        ClusterSet<Square> givenClusterSet = new ClusterSet<>(generateElementsSet(elementsNumber));
        givenClusterSet.composeClusters(alwaysFalse);
        assertEquals(elementsNumber, givenClusterSet.numberOfSets());
    }

    @Test
    void anyClusterMatchesReturnsTrueWithAlwaysTruePredicate() {
        assertTrue(clusterSet.anyClusterMatches(Predicates.alwaysTrue()));
    }

    @Test
    void anyClusterMatchesReturnsFalseWithAlwaysFalsePredicate() {
        assertFalse(clusterSet.anyClusterMatches(Predicates.alwaysFalse()));
    }
}
