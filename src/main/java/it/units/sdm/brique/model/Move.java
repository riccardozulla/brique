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
            gameBoard.getDownLeftSquare(chosenSquare).getStone().ifPresent(stone -> {
                if (stoneBelongsToPlayer(stone)) {
                    if (chosenSquare.getColor() == Color.WHITE) {
                        placeStone(gameBoard.getLeftSquare(chosenSquare)); //left square always exits
                    } else {
                        placeStone(gameBoard.getDownSquare(chosenSquare));
                    }
                }
            });
        }
        if (!chosenSquare.isTopEdge() && !chosenSquare.isRightEdge()) {
            gameBoard.getUpRightSquare(chosenSquare).getStone().ifPresent(stone -> {
                if (stoneBelongsToPlayer(stone)) {
                    if (chosenSquare.getColor() == Color.WHITE) {
                        placeStone(gameBoard.getUpSquare(chosenSquare));
                    } else {
                        placeStone(gameBoard.getRightSquare(chosenSquare));
                    }
                }
            });
        }
    }

    private boolean stoneBelongsToPlayer(Stone stone) {
        return stone.getColor() == player.getStoneColor();
    }
}
