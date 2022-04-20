package it.units.sdm.brique.controller;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Game;
import it.units.sdm.brique.model.Player;
import it.units.sdm.brique.ui.GraphicBoard;
import it.units.sdm.brique.ui.GraphicSquare;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable, PropertyChangeListener {

    Game game = new Game(new Player("PLayer1", Color.BLACK), new Player("Player2", Color.WHITE));

    @FXML
    private HBox board_wrapper;

    @FXML
    private GraphicBoard graphicBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        board_wrapper.layoutBoundsProperty().addListener(observable -> graphicBoard.fit(board_wrapper));

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
