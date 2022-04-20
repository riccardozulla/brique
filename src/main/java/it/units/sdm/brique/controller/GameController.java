package it.units.sdm.brique.controller;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Game;
import it.units.sdm.brique.model.Player;
import it.units.sdm.brique.ui.GraphicBoard;
import it.units.sdm.brique.ui.GraphicSquare;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable, PropertyChangeListener {

    Game game = new Game(new Player("PLayer1", Color.BLACK), new Player("Player2", Color.WHITE));

    @FXML
    private VBox game_wrapper;

    @FXML
    private HBox board_wrapper;

    @FXML
    private GraphicBoard graphicBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Image background = new Image("it/units/sdm/brique/game_carpet.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, new BackgroundSize(300,300,false, false, false, false));
        game_wrapper.setBackground(new Background(backgroundImage));

        board_wrapper.layoutBoundsProperty().addListener(graphicBoard.fit);

        graphicBoard.setOnMouseClicked(event -> {
            if (event.isStillSincePress() && event.getTarget() instanceof GraphicSquare)
                game.addStone(((GraphicSquare) event.getTarget()).getSquare());
        });

        game.addActivePlayerListener(this);
        game.addStatusListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        switch (propertyChangeEvent.getPropertyName()) {
            case "activePlayer" -> graphicBoard.update();
            case "status" -> {
                System.out.println(propertyChangeEvent.getNewValue());
                graphicBoard.update();
                graphicBoard.setDisable(true);
            }
        }
    }
}
