package it.units.sdm.brique.model;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Move {
    private final Player player;
    private Square chosenSquare;

    public Move(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setChosenSquare(Square chosenSquare) {
        this.chosenSquare = chosenSquare;
    }

    public void make() {
        placeStone(chosenSquare);
        applyEscortRule();
    }

    private void placeStone(Square square) {
        square.setStone(new Stone(player.getStoneColor()));
    }

    private void applyEscortRule() {
        Board gameBoard = Board.getBoard();
        gameBoard.getDownLeft(chosenSquare).flatMap(Square::getStone).ifPresent(stone -> {
            if (stoneBelongsToPlayer(stone)) {
                if (chosenSquare.getColor() == Color.WHITE) {
                    placeStone(gameBoard.getLeft(chosenSquare).get()); //left square always exits
                } else {
                    placeStone(gameBoard.getDown(chosenSquare).get());
                }
            }
        });
        gameBoard.getUpRight(chosenSquare).flatMap(Square::getStone).ifPresent(stone -> {
            if (stoneBelongsToPlayer(stone)) {
                if (chosenSquare.getColor() == Color.WHITE) {
                    placeStone(gameBoard.getUp(chosenSquare).get());
                } else {
                    placeStone(gameBoard.getRight(chosenSquare).get());
                }
            }
        });
    }

    private boolean stoneBelongsToPlayer(Stone stone) {
        return stone.getColor() == player.getStoneColor();
    }

    public boolean isWinning() {
        Board gameBoard = Board.getBoard();
        var placedStones = Arrays.stream(gameBoard.getSquares()).flatMap(Arrays::stream).
                filter(square -> square.getStone().isPresent()).
                filter(square -> square.getStone().get().getColor() == player.getStoneColor()).collect(Collectors.toSet());
        ClusterSet activeCluster = new ClusterSet(placedStones, player.getStoneColor());
        activeCluster.composeClusters();
        return activeCluster.winningPath();
    }
}
