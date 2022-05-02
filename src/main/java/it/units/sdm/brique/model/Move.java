package it.units.sdm.brique.model;

import it.units.sdm.brique.model.exceptions.StoneAlreadyPresentException;

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
        if (chosenSquare.getStone().isPresent())
            throw new StoneAlreadyPresentException("Stone already present in the given square!");
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
        if (!chosenSquare.isBottomEdge() && !chosenSquare.isLeftEdge()) {
            gameBoard.getDownLeft(chosenSquare).getStone().ifPresent(stone -> {
                if (stoneBelongsToPlayer(stone)) {
                    if (chosenSquare.getColor() == Color.WHITE) {
                        placeStone(gameBoard.getLeft(chosenSquare)); //left square always exits
                    } else {
                        placeStone(gameBoard.getDown(chosenSquare));
                    }
                }
            });
        }
        if (!chosenSquare.isTopEdge() && !chosenSquare.isRightEdge()) {
            gameBoard.getUpRight(chosenSquare).getStone().ifPresent(stone -> {
                if (stoneBelongsToPlayer(stone)) {
                    if (chosenSquare.getColor() == Color.WHITE) {
                        placeStone(gameBoard.getUp(chosenSquare));
                    } else {
                        placeStone(gameBoard.getRight(chosenSquare));
                    }
                }
            });
        }
    }

    private boolean stoneBelongsToPlayer(Stone stone) {
        return stone.getColor() == player.getStoneColor();
    }
}
