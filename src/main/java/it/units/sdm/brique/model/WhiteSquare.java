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
        List<Square> escorts = new ArrayList<>(MAX_ESCORTS_NUMBER);
        if (!this.isTopEdge()) escorts.add(getUpSquare());
        if (!this.isLeftEdge()) escorts.add(getLeftSquare());
        return escorts;
    }
}
