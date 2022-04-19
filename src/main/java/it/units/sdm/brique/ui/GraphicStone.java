package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Stone;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;

public class GraphicStone extends Ellipse implements Drawable {
    Stone stone;

    public GraphicStone(Stone stone) {
        this.stone = stone;
    }

    @Override
    public void draw(Pane pane) {
        NumberBinding squareSize = Bindings.min(pane.heightProperty(), pane.widthProperty());
        this.radiusXProperty().bind(squareSize.multiply(0.31));
        this.radiusYProperty().bind(squareSize.multiply(0.26));
        this.centerXProperty().bind((squareSize.divide(2)));
        this.centerYProperty().bind((squareSize.divide(2)));
        this.setFill(stone.getColor() == Color.WHITE ? GraphicColor.WHITE_STONE.getColor()
                : GraphicColor.BLACK_STONE.getColor());
        DropShadow dropShadow = new DropShadow();
//        dropShadow.setOffsetX(1.0);
//        dropShadow.setOffsetY(8.0);
        dropShadow.offsetXProperty().bind(radiusXProperty().multiply(0.33));
        dropShadow.offsetYProperty().bind(radiusYProperty().multiply(0.8));
        dropShadow.setColor(stone.getColor() == Color.BLACK ? javafx.scene.paint.Color.BLACK
                : javafx.scene.paint.Color.color(0.05, 0.05,0.05));
        this.setEffect(dropShadow);
    }
}
