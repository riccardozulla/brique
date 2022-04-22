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
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("playerinit.fxml"));
        Parent newRoot = FXMLLoader.load(getClass().getResource("/it/units/sdm/brique/game.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Brique");
        stage.setScene(scene);
        stage.setHeight(WINDOW_HEIGHT);
        stage.setWidth(WINDOW_WIDTH);
        stage.show();
        stage.setOnHidden(event -> {
            Stage newStage = new Stage();
            Scene newScene = new Scene(newRoot);
            newStage.setScene(newScene);
            newStage.show();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
