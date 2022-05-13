package it.units.sdm.brique;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Brique extends Application {

    public static final double PRIMARY_STAGE_HEIGHT = 600.0;
    public static final double PRIMARY_STAGE_WIDTH = 700.0;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent playerInitView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("playerInit.fxml")));
        Scene scene = new Scene(playerInitView);

        primaryStage.setTitle("Brique");
        primaryStage.setScene(scene);
        primaryStage.setHeight(PRIMARY_STAGE_HEIGHT);
        primaryStage.setWidth(PRIMARY_STAGE_WIDTH);
        primaryStage.getIcons().add(new Image("/it/units/sdm/brique/images/icon.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
