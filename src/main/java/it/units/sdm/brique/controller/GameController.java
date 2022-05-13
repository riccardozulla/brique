package it.units.sdm.brique.controller;

import it.units.sdm.brique.model.Game;
import it.units.sdm.brique.utility.PlayerHolder;
import it.units.sdm.brique.model.Status;
import it.units.sdm.brique.ui.GraphicBoard;
import it.units.sdm.brique.ui.GraphicSquare;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable, PropertyChangeListener {

    Game game;
    @FXML
    private VBox playerOneWrapper;
    @FXML
    private VBox playerTwoWrapper;
    @FXML
    private Label playerOneNickname;
    @FXML
    private Label playerTwoNickname;
    @FXML
    private Button pieButton;
    @FXML
    private VBox gameWrapper;
    @FXML
    private HBox boardWrapper;
    @FXML
    private GraphicBoard graphicBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeNewGame();
        boardWrapper.layoutBoundsProperty().addListener(graphicBoard.fit);
        graphicBoard.setOnMousePressed(event -> game.playTurn(((GraphicSquare) event.getTarget()).getSquare()));
        playerOneNickname.setText(game.getPlayer1().getNickname());
        playerTwoNickname.setText(game.getPlayer2().getNickname());
        gameWrapper.getStylesheets().addAll("it/units/sdm/brique/styles/style.css", "it/units/sdm/brique/styles/game_style.css");

    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        switch (propertyChangeEvent.getPropertyName()) {
            case "activePlayer" -> {
                graphicBoard.update();
                highlightActivePlayer();
                updatePieButton();
            }
            case "status" -> {
                winningPopup((Status) propertyChangeEvent.getNewValue());
                graphicBoard.update();
                graphicBoard.setDisable(true);
            }
        }
    }

    @FXML
    private void initializeNewGame() {
        game = new Game(PlayerHolder.getInstance().getPlayer1(), PlayerHolder.getInstance().getPlayer2());
        game.addActivePlayerListener(this);
        game.addStatusListener(this);
        graphicBoard.update();
        highlightActivePlayer();
        updatePieButton();
        graphicBoard.setDisable(false);
    }

    private void highlightActivePlayer() {
        if (game.getPlayer1().equals(game.getActivePlayer())) {
            playerOneWrapper.getStyleClass().add("active-player");
            playerTwoWrapper.getStyleClass().clear();
        } else {
            playerTwoWrapper.getStyleClass().add("active-player");
            playerOneWrapper.getStyleClass().clear();
        }
    }

    @FXML
    protected void handlePieButtonAction() {
        game.pieRule();
    }

    private void updatePieButton() {
        pieButton.setDisable(!game.isPieRuleApplicable());
    }

    private void winningPopup(Status status) {
        Stage stage = (Stage) gameWrapper.getScene().getWindow();
        Popup popup = new Popup();
        HBox headerBox = new HBox(new Label("CONGRATULATIONS"));
        headerBox.prefHeight(100);
        Label personalizedLabel = new Label();
        switch (status) {
            case WHITE_WINS -> personalizedLabel.setText("White player wins!");
            case BLACK_WINS -> personalizedLabel.setText("Black player wins!");
        }
        HBox contentBox = new HBox(personalizedLabel);
        Button playAgainButton = new Button("Play again");
        playAgainButton.setOnMouseClicked(event -> {
            initializeNewGame();
            popup.hide();
        });
        Button closeButton = new Button("Close");
        closeButton.setOnMouseClicked(event -> stage.close());
        HBox buttonsBox = new HBox(playAgainButton, closeButton);
        Parent root = new VBox(headerBox, contentBox, buttonsBox);
        VBox.setVgrow(contentBox, Priority.ALWAYS);
        root.getStylesheets().addAll("/it/units/sdm/brique/styles/style.css", "/it/units/sdm/brique/styles/endgame_style.css");
        popup.getContent().add(root);
        popup.show(stage);
    }

    @FXML
    private void openPDFGuide() {
        try {
            Application application = new Application() {
                @Override
                public void start(Stage primaryStage) {
                    File file = new File(getClass().getClassLoader().getResource("it/units/sdm/brique/docs/Brique.pdf").getFile());
                    getHostServices().showDocument(file.getAbsolutePath());
                }
            };
            application.start(new Stage());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void quitGame() {
        Stage s = (Stage) (gameWrapper.getScene().getWindow());
        s.close();
    }
}
