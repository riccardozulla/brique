package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class GraphicSquare extends StackPane {

    public static final int SQUARE_SIZE = 20;
    private final Square square;

    public GraphicSquare(Square square) {
        super();
        this.square = square;
        this.setPrefSize(SQUARE_SIZE, SQUARE_SIZE);
        this.setStyle("-fx-background-color:" + (square.getColor() == Color.BLACK ? GraphicColor.BLACK_SQUARE.getHexColor() : GraphicColor.WHITE_SQUARE.getHexColor()));
    }

    public Square getSquare() {
        return square;
    }


    private void eraseStone() {
        this.getChildren().clear();
    }

    private void drawStone() {
        square.getStone().ifPresent(stone -> {
            GraphicStone graphicStone = new GraphicStone(stone);
            graphicStone.draw(this);
            this.getChildren().add(graphicStone);
        });
    }

    public void update() {
        eraseStone();
        drawStone();
        toggleMouseClick();
    }

    public void toggleMouseClick() {
        EventHandler<javafx.scene.input.MouseEvent> handler = javafx.scene.input.MouseEvent::consume;
        if (square.getStone().isPresent()) {
            this.addEventFilter(MouseEvent.ANY, handler);
        } else {
            this.removeEventFilter(MouseEvent.ANY, handler);
        }
    }
}
