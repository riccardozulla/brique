package it.units.sdm.brique.model;

import java.util.List;
import java.util.Optional;

public abstract class Square {
    private final int row;
    private final int column;
    private Stone stone;
    private static final Board board = Board.getBoard();

    public Square(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static int manhattanSquareDistance(Square square1, Square square2) {
        return Math.abs(square1.getRow() - square2.getRow()) + Math.abs(square1.getColumn() - square2.getColumn());
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public abstract Color getColor();

    public Optional<Stone> getStone() {
        return Optional.ofNullable(stone);
    }

    public void setStone(Stone stone) {
        this.stone = stone;
    }

    public void removeStone() {
        stone = null;
    }

    public abstract List<Square> getEscorts();

    public boolean isOccupied() {
        return stone != null;
    }

    public boolean isTopEdge() {
        return row == Board.FIRST_INDEX;
    }

    public boolean isBottomEdge() {
        return row == Board.LAST_INDEX;
    }

    public boolean isLeftEdge() {
        return column == Board.FIRST_INDEX;
    }

    public boolean isRightEdge() {
        return column == Board.LAST_INDEX;
    }

    public Square getUpSquare() {
        return Board.getBoard().getSquare(getRow() - 1, getColumn());
    }

    public Square getDownSquare() {
        return Board.getBoard().getSquare(getRow() + 1, getColumn());
    }

    public Square getLeftSquare() {
        return Board.getBoard().getSquare(getRow(), getColumn() - 1);
    }

    public Square getRightSquare() {
        return Board.getBoard().getSquare(getRow(), getColumn() + 1);
    }
}

