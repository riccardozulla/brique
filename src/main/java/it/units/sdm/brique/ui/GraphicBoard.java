package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Board;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class GraphicBoard extends Pane {

    public static final int DEFAULT_BORDER_WIDTH = GraphicSquare.SQUARE_SIZE / 4;
    public static final int DEFAULT_BOARD_LENGTH = Board.BOARD_SIZE * GraphicSquare.SQUARE_SIZE + 2 * DEFAULT_BORDER_WIDTH;
    public static final double PERCENTAGE_PADDING = 0.9;

    public final ChangeListener<Bounds> fit = (observable, oldValue, newValue) -> {
        double ratio = Math.min(newValue.getHeight(), newValue.getWidth()) * PERCENTAGE_PADDING / DEFAULT_BOARD_LENGTH;
        this.setScaleX(ratio);
        this.setScaleY(ratio);
    };

    Board board;

    public GraphicBoard() {
        this.board = Board.getInstance();
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                GraphicSquare graphicSquare = new GraphicSquare(board.getSquare(row, col));
                graphicSquare.setLayoutX(col * GraphicSquare.SQUARE_SIZE + DEFAULT_BORDER_WIDTH);
                graphicSquare.setLayoutY(row * GraphicSquare.SQUARE_SIZE + DEFAULT_BORDER_WIDTH);
                this.getChildren().add(graphicSquare);
            }
        }
        buildBorder();
    }

    private void buildBorder() {
        Polygon topBorder = new Polygon(
                0.0, 0.0,
                DEFAULT_BOARD_LENGTH, 0.0,
                DEFAULT_BOARD_LENGTH - DEFAULT_BORDER_WIDTH, DEFAULT_BORDER_WIDTH,
                DEFAULT_BORDER_WIDTH, DEFAULT_BORDER_WIDTH);
        topBorder.setFill(GraphicColor.BLACK_STONE.getColor());
        Polygon rightBorder = new Polygon(
                DEFAULT_BOARD_LENGTH, 0.0,
                DEFAULT_BOARD_LENGTH, DEFAULT_BOARD_LENGTH,
                DEFAULT_BOARD_LENGTH - DEFAULT_BORDER_WIDTH, DEFAULT_BOARD_LENGTH - DEFAULT_BORDER_WIDTH,
                DEFAULT_BOARD_LENGTH - DEFAULT_BORDER_WIDTH, DEFAULT_BORDER_WIDTH);
        rightBorder.setFill(GraphicColor.WHITE_STONE.getColor());
        Polygon bottomBorder = new Polygon(
                DEFAULT_BOARD_LENGTH - DEFAULT_BORDER_WIDTH, DEFAULT_BOARD_LENGTH - DEFAULT_BORDER_WIDTH,
                DEFAULT_BOARD_LENGTH, DEFAULT_BOARD_LENGTH,
                0, DEFAULT_BOARD_LENGTH,
                DEFAULT_BORDER_WIDTH, DEFAULT_BOARD_LENGTH - DEFAULT_BORDER_WIDTH);
        bottomBorder.setFill(GraphicColor.BLACK_STONE.getColor());
        Polygon leftBorder = new Polygon(
                0.0, 0.0,
                DEFAULT_BORDER_WIDTH, DEFAULT_BORDER_WIDTH,
                DEFAULT_BORDER_WIDTH, DEFAULT_BOARD_LENGTH - DEFAULT_BORDER_WIDTH,
                0.0, DEFAULT_BOARD_LENGTH);
        leftBorder.setFill(GraphicColor.WHITE_STONE.getColor());

        Group borders = new Group(topBorder, rightBorder, bottomBorder, leftBorder);
        borders.addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);

        this.getChildren().addAll(borders);
    }

    public void update() {
        this.getChildren().stream().filter(GraphicSquare.class::isInstance).
                forEach(graphicSquare -> ((GraphicSquare) graphicSquare).update());
    }
}