package it.units.sdm.brique.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Board {

    public static final int BOARD_SIZE = 15;
    public static final int FIRST_INDEX = 0;
    public static final int LAST_INDEX = BOARD_SIZE - 1;
    private static Board boardInstance;
    private final Square[][] squares;

    private Board() {
        this.squares = new Square[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                squares[i][j] = generateNewSquare(i, j);
            }
        }
    }

    private Square generateNewSquare(int row, int column) {
        return (row + column) % 2 == 0 ? new WhiteSquare(row, column) : new BlackSquare(row, column);
    }

    public static Board getBoard() {
        if (boardInstance == null) boardInstance = new Board();
        return boardInstance;
    }

    public void reset() {
        getSquaresStream().forEach(Square::removeStone);
    }

    public Square[][] getSquares() {
        return squares;
    }

    public Stream<Square> getSquaresStream() {
        return Arrays.stream(squares).flatMap(Arrays::stream);
    }

    public List<Square> getOccupiedSquares() {
        return getSquaresStream().filter(Square::isOccupied).toList();
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
