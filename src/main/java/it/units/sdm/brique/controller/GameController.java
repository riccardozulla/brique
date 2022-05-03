package it.units.sdm.brique.controller;

import it.units.sdm.brique.model.Game;
import it.units.sdm.brique.model.PlayerHolder;
import it.units.sdm.brique.model.Status;
import it.units.sdm.brique.ui.GraphicBoard;
import it.units.sdm.brique.ui.GraphicSquare;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable, PropertyChangeListener {

    @FXML
    private VBox player_one_wrapper;
    @FXML
    private VBox player_two_wrapper;
    @FXML
    private Text player_one_nickname;
    @FXML
    private Text player_two_nickname;

    @FXML
    private Button pie_button;

    @FXML
    private VBox game_wrapper;

    @FXML
    private HBox board_wrapper;

    @FXML
    private GraphicBoard graphicBoard;

    Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializeNewGame();

        Image background = new Image("it/units/sdm/brique/images/game_carpet.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, new BackgroundSize(300, 300, false, false, false, false));
        game_wrapper.setBackground(new Background(backgroundImage));

        board_wrapper.layoutBoundsProperty().addListener(graphicBoard.fit);

        graphicBoard.setOnMousePressed(event -> game.addStone(((GraphicSquare) event.getTarget()).getSquare()));

        player_one_nickname.setText(game.getPlayer1().getNickname());
        player_two_nickname.setText(game.getPlayer2().getNickname());

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
            player_one_wrapper.getStyleClass().add("activePlayer");
            player_two_wrapper.getStyleClass().remove("activePlayer");
        } else {
            player_two_wrapper.getStyleClass().add("activePlayer");
            player_one_wrapper.getStyleClass().remove("activePlayer");
        }
    }

    @FXML
    protected void handlePieButtonAction() {
        game.pieRule();
    }

    private void updatePieButton() {
        pie_button.setDisable(!game.isPieRuleApplicable());
    }

    private void winningPopup(Status status) {
        Stage stage = (Stage) game_wrapper.getScene().getWindow();
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
        root.getStylesheets().add("/it/units/sdm/brique/endgame_style.css");
        popup.getContent().add(root);
        popup.show(stage);
    }

    @FXML
    private void openPDFGuide() {
        try {
            Application application = new Application() {
                @Override
                public void start(Stage primaryStage) {
                    File file = new File(getClass().getClassLoader().getResource("it/units/sdm/brique/doc/Brique.pdf").getFile());
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
        Stage s = (Stage) (game_wrapper.getScene().getWindow());
        s.close();
    }
}
