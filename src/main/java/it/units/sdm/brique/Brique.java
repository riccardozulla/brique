package it.units.sdm.brique;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Brique extends Application {

    public static final double WINDOW_HEIGHT = 600.0;
    public static final double WINDOW_WIDTH = 700.0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent playerInitView = FXMLLoader.load(getClass().getResource("playerinit.fxml"));
        Parent gameView = FXMLLoader.load(getClass().getResource("/it/units/sdm/brique/game.fxml"));
        Scene scene = new Scene(playerInitView);

        primaryStage.setTitle("Brique");
        primaryStage.setScene(scene);
        primaryStage.setHeight(WINDOW_HEIGHT);
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.show();
        primaryStage.setOnHidden(event -> {
            Stage secondaryStage = new Stage();
            Scene newScene = new Scene(gameView);
            secondaryStage.setScene(newScene);
            secondaryStage.show();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
