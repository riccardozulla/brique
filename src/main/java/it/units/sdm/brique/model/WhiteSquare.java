package it.units.sdm.brique.model;

import java.util.ArrayList;
import java.util.List;

public class WhiteSquare extends Square {
    public WhiteSquare(int row, int column) {
        super(row, column);
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }

    @Override
    public List<Square> getEscorts() {
        List<Square> escorts = new ArrayList<>(2);
        if (!this.isTopEdge()) escorts.add(Board.getBoard().getUpSquare(this));
        if (!this.isLeftEdge()) escorts.add(Board.getBoard().getLeftSquare(this));
        return escorts;
    }

}
