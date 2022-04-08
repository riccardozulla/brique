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

    private void placeStone(Square square) {
        square.setStone(new Stone(player.getStoneColor()));
    }
}
