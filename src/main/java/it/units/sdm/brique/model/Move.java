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
        if (chosenSquare.isOccupied())
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
        chosenSquare.getEscorts().forEach(escortSquare -> {
            if (escortSquare.getEscorts().stream().allMatch(diagonalSquare ->
                    diagonalSquare.getStone().isPresent() && diagonalSquare.getStone().get().getColor() == player.getStoneColor()
            ) && escortSquare.getEscorts().size() == 2) {
                placeStone(escortSquare);
            }
        });
    }

    private boolean stoneBelongsToPlayer(Stone stone) {
        return stone.getColor() == player.getStoneColor();
    }
}
