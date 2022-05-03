package it.units.sdm.brique.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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
        getSquaresStream().forEach(square -> square.setStone(null));
    }

    public Square[][] getSquares() {
        return squares;
    }

    public Stream<Square> getSquaresStream() {
        return Arrays.stream(squares).flatMap(Arrays::stream);
    }

    public List<Square> getOccupiedSquares() {
        return getSquaresStream().filter(square -> square.getStone().isPresent()).toList();
    }

    public Square getSquare(int i, int j) {
        return squares[i][j];
    }

    public Square getUpSquare(Square square) {
        return getSquare(square.getRow() - 1, square.getColumn());

    }

    public Square getDownSquare(Square square) {
        return getSquare(square.getRow() + 1, square.getColumn());

    }

    public Square getLeftSquare(Square square) {
        return getSquare(square.getRow(), square.getColumn() - 1);

    }

    public Square getRightSquare(Square square) {
        return getSquare(square.getRow(), square.getColumn() + 1);

    }

    public Square getUpRightSquare(Square square) {
        return getSquare(square.getRow() - 1, square.getColumn() + 1);

    }

    public Square getDownLeftSquare(Square square) {
        return getSquare(square.getRow() + 1, square.getColumn() - 1);
    }

}
