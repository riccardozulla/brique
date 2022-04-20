package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Board;
import javafx.scene.layout.Pane;

public class GraphicBoard extends Pane {

    public static final int DEFAULT_BORDER_WIDTH = GraphicSquare.SQUARE_SIZE / 4;
    public static final int DEFAULT_OFFSET_X = GraphicBoard.DEFAULT_BORDER_WIDTH;
    public static final int DEFAULT_OFFSET_Y = GraphicBoard.DEFAULT_BORDER_WIDTH;
    Board board;

    public GraphicBoard() {
        this.board = Board.getBoard();
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                GraphicSquare graphicSquare = new GraphicSquare(board.getSquare(i, j));
                graphicSquare.setLayoutX(i * GraphicSquare.SQUARE_SIZE + DEFAULT_OFFSET_X);
                graphicSquare.setLayoutY(j * GraphicSquare.SQUARE_SIZE + DEFAULT_OFFSET_Y);
                this.getChildren().add(graphicSquare);
            }
        }
    }

    public void update() {
        this.getChildren().forEach(graphicSquare -> ((GraphicSquare) graphicSquare).update());
    }
}