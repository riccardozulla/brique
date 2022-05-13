package it.units.sdm.brique.model;

import it.units.sdm.brique.model.exceptions.StoneAlreadyPresentException;

import java.util.List;

public class Placement {
    private static final int MAX_ESCORTS_NUMBER = 2;
    private final Player player;
    private Square chosenSquare;

    public Placement(Player player) {
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
            if (areBothEscortsOccupiedByFriendlyStones(escortSquare)) {
                placeStone(escortSquare);
            }
        });
    }

    private boolean areBothEscortsOccupiedByFriendlyStones(Square square) {
        List<Square> escortList = square.getEscorts();
        if (escortList.size() != MAX_ESCORTS_NUMBER) return false;
        return escortList.stream().allMatch(escort -> escort.getStone().isPresent() && stoneBelongsToPlayer(escort.getStone().get()));
    }

    private boolean stoneBelongsToPlayer(Stone stone) {
        return stone.getColor().equals(player.getStoneColor());
    }
}
