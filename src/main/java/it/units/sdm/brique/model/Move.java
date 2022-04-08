package it.units.sdm.brique.model;

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
}
