package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Board;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class GraphicBoard extends Pane {

    public static final int DEFAULT_BORDER_WIDTH = GraphicSquare.SQUARE_SIZE / 4;
    public static final int DEFAULT_OFFSET = GraphicBoard.DEFAULT_BORDER_WIDTH;
    public static final int DEFAULT_BOARD_LENGTH = Board.BOARD_SIZE * GraphicSquare.SQUARE_SIZE + 2 * DEFAULT_OFFSET;
    public static final double PERCENTAGE_PADDING = 0.9;

    public ChangeListener<Bounds> fit = (observable, oldValue, newValue) -> {
        double ratio = Math.min(newValue.getHeight(), newValue.getWidth()) * PERCENTAGE_PADDING / DEFAULT_BOARD_LENGTH;
        this.setScaleX(ratio);
        this.setScaleY(ratio);
    };

    Board board;

    public GraphicBoard() {
        this.board = Board.getBoard();
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                GraphicSquare graphicSquare = new GraphicSquare(board.getSquare(i, j));
                graphicSquare.setLayoutX(i * GraphicSquare.SQUARE_SIZE + DEFAULT_OFFSET);
                graphicSquare.setLayoutY(j * GraphicSquare.SQUARE_SIZE + DEFAULT_OFFSET);
                this.getChildren().add(graphicSquare);
            }
        }
        buildBorder();
    }

    private void buildBorder() {
        Polygon topBorder = new Polygon(
                0.0, 0.0,
                DEFAULT_BOARD_LENGTH, 0.0,
                DEFAULT_BOARD_LENGTH - DEFAULT_OFFSET, DEFAULT_OFFSET,
                DEFAULT_OFFSET, DEFAULT_OFFSET);
        topBorder.setFill(GraphicColor.BLACK_STONE.getColor());
        Polygon rightBorder = new Polygon(
                DEFAULT_BOARD_LENGTH, 0.0,
                DEFAULT_BOARD_LENGTH, DEFAULT_BOARD_LENGTH,
                DEFAULT_BOARD_LENGTH - DEFAULT_OFFSET, DEFAULT_BOARD_LENGTH - DEFAULT_OFFSET,
                DEFAULT_BOARD_LENGTH - DEFAULT_OFFSET, DEFAULT_OFFSET);
        rightBorder.setFill(GraphicColor.WHITE_STONE.getColor());
        Polygon bottomBorder = new Polygon(
                DEFAULT_BOARD_LENGTH - DEFAULT_OFFSET, DEFAULT_BOARD_LENGTH - DEFAULT_OFFSET,
                DEFAULT_BOARD_LENGTH, DEFAULT_BOARD_LENGTH,
                0, DEFAULT_BOARD_LENGTH,
                DEFAULT_OFFSET, DEFAULT_BOARD_LENGTH - DEFAULT_OFFSET);
        bottomBorder.setFill(GraphicColor.BLACK_STONE.getColor());
        Polygon leftBorder = new Polygon(
                0.0, 0.0,
                DEFAULT_OFFSET, DEFAULT_OFFSET,
                DEFAULT_OFFSET, DEFAULT_BOARD_LENGTH - DEFAULT_OFFSET,
                0.0, DEFAULT_BOARD_LENGTH);
        leftBorder.setFill(GraphicColor.WHITE_STONE.getColor());

        this.getChildren().addAll(topBorder, rightBorder, bottomBorder, leftBorder);
    }

    public void update() {
        this.getChildren().stream().filter(child -> child instanceof GraphicSquare).
                forEach(graphicSquare -> ((GraphicSquare) graphicSquare).update());
    }
}