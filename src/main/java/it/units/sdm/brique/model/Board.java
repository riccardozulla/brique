package it.units.sdm.brique.model;

import java.util.Arrays;
import java.util.List;

public class Board {

    private static Board boardInstance;

    public static final int BOARD_SIZE = 15;
    public static final int FIRST_INDEX = 0;
    public static final int LAST_INDEX = BOARD_SIZE - 1;
    private final Square[][] squares;

    private Board() {
        this.squares = new Square[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                squares[i][j] = new Square(i, j);
            }
        }
    }

    public static Board getBoard() {
        if (boardInstance == null) boardInstance = new Board();
        return boardInstance;
    }

    public void reset() {
        Arrays.stream(squares).flatMap(Arrays::stream).forEach(square -> square.setStone(null));
    }

    public Square[][] getSquares() {
        return squares;
    }

    public List<Square> getOccupiedSquares() {
        return Arrays.stream(squares).flatMap(Arrays::stream).
                filter(square -> square.getStone().isPresent()).toList();
    }

    public Square getSquare(int i, int j) {
        return squares[i][j];
    }

    public Square getUp(Square square) {
        return getSquare(square.getRow() - 1, square.getColumn());

    }

    public Square getDown(Square square) {
        return getSquare(square.getRow() + 1, square.getColumn());

    }

    public Square getLeft(Square square) {
        return getSquare(square.getRow(), square.getColumn() - 1);

    }

    public Square getRight(Square square) {
        return getSquare(square.getRow(), square.getColumn() + 1);

    }

    public Square getUpRight(Square square) {
        return getSquare(square.getRow() - 1, square.getColumn() + 1);

    }

    public Square getDownLeft(Square square) {
        return getSquare(square.getRow() + 1, square.getColumn() - 1);
    }

    public static boolean isCoordinatesOutOfBounds(int x, int y) { //todo: unused, to be handled
        return x < FIRST_INDEX || y < FIRST_INDEX || x > LAST_INDEX || y > LAST_INDEX;
    }

}
