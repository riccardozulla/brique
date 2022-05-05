package it.units.sdm.brique.model;

import java.util.ArrayList;
import java.util.List;

public class BlackSquare extends Square {
    public BlackSquare(int row, int column) {
        super(row, column);
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public List<Square> getEscorts() {
        List<Square> escorts = new ArrayList<>(2);
        if (!this.isBottomEdge()) escorts.add(Board.getBoard().getDownSquare(this));
        if (!this.isRightEdge()) escorts.add(Board.getBoard().getRightSquare(this));
        return escorts;
    }
}
