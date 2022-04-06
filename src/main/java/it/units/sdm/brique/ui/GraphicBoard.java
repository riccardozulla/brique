package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Board;
import javafx.scene.layout.Pane;

public class GraphicBoard extends Pane implements Drawable {

    Board board;

    public GraphicBoard() {
        this.board = Board.getBoard();
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                this.getChildren().add(new GraphicSquare(board.getSquare(i, j)));
            }
        }
    }

    public void draw(Pane pane) {
        this.heightProperty().addListener((obs, oldVal, newVal) -> {
            var squareSize = newVal.doubleValue() / Board.BOARD_SIZE;
            this.setStyle("-fx-border-color:black red black red; -fx-border-style:solid outside; -fx-border-radius:" + squareSize / 2 + " ; -fx-border-width:" + squareSize + ";");
        });
        this.getChildren().forEach(graphicSquare -> ((GraphicSquare) graphicSquare).draw(pane));
        pane.getChildren().add(this);
    }

    public void update() {
        this.getChildren().forEach(graphicSquare -> ((GraphicSquare) graphicSquare).update());
    }
}