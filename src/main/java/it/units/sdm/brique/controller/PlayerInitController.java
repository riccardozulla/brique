package it.units.sdm.brique.controller;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Player;
import it.units.sdm.brique.utility.PlayerHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PlayerInitController implements Initializable {
    public static final int GAME_VIEW_MIN_HEIGHT = 500;
    public static final int GAME_VIEW_MIN_WIDTH = 400;
    public static final int MAX_NICKNAME_SIZE = 15;
    private boolean swapHappened = false;

    @FXML
    private TextField player1TextField;
    @FXML
    private TextField player2TextField;
    @FXML
    private ImageView player1StoneImageView;
    @FXML
    private ImageView player2StoneImageView;
    @FXML
    private VBox welcomeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        makeBlackEffect(player1StoneImageView);
        welcomeView.getStylesheets().addAll("it/units/sdm/brique/styles/style.css", "it/units/sdm/brique/styles/playerInit_style.css");
    }

    @FXML
    protected void handleConfirmButtonAction(ActionEvent event) throws IOException {
        if (isUserInputValid()) {
            savePlayerData();
            Parent gameView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/units/sdm/brique/game.fxml")));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Stage secondaryStage = new Stage();
            Scene newScene = new Scene(gameView);
            secondaryStage.setScene(newScene);
            secondaryStage.setMinWidth(GAME_VIEW_MIN_WIDTH);
            secondaryStage.setMinHeight(GAME_VIEW_MIN_HEIGHT);
            secondaryStage.setMaximized(true);
            secondaryStage.getIcons().add(new Image("/it/units/sdm/brique/images/icon.png"));
            secondaryStage.show();
            currentStage.close();
        }
    }

    private boolean isUserInputValid() {
        if (isNicknameEmpty()) {
            displayNicknameErrorAlert("The nickname can't be empty." + "\nPlease choose a nickname.");
            return false;
        }
        if (isSameNickname()) {
            displayNicknameErrorAlert("The two players can't have the same nickname." + "\nPlease choose a different nickname.");
            return false;
        }
        if (isNicknameTooLong()) {
            displayNicknameErrorAlert("The nickname can't exceed 15 characters." + "\nPlease choose a shorter nickname.");
            return false;
        }
        return true;
    }

    @FXML
    protected void handleToggleButtonAction() {
        swapColor();
    }

    private void swapColor() {
        ImageView img1 = swapHappened ? player2StoneImageView : player1StoneImageView;
        ImageView img2 = swapHappened ? player1StoneImageView : player2StoneImageView;
        img1.setEffect(null);
        makeBlackEffect(img2);
        swapHappened = !swapHappened;
    }

    private void savePlayerData() {
        Player player1 = new Player(player1TextField.getText(), swapHappened ? Color.WHITE : Color.BLACK);
        Player player2 = new Player(player2TextField.getText(), swapHappened ? Color.BLACK : Color.WHITE);
        PlayerHolder playerHolder = PlayerHolder.getInstance();
        playerHolder.setPlayer1(player1);
        playerHolder.setPlayer2(player2);
    }

    private void makeBlackEffect(ImageView img) {
        ColorAdjust blackout = new ColorAdjust();
        blackout.setBrightness(-0.8);
        img.setEffect(blackout);
    }

    private boolean isSameNickname() {
        return player1TextField.getText().equals(player2TextField.getText());
    }

    private boolean isNicknameEmpty() {
        return player1TextField.getText().isEmpty() || player2TextField.getText().isEmpty();
    }

    private boolean isNicknameTooLong() {
        return player1TextField.getText().length() >= MAX_NICKNAME_SIZE || player2TextField.getText().length() >= MAX_NICKNAME_SIZE;
    }

    private void displayNicknameErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Nickname error!");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
