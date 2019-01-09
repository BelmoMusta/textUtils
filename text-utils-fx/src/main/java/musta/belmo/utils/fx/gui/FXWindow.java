package musta.belmo.utils.fx.gui;

/**
 * Created by DELL on 04/08/2018.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class FXWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        URL resourceAsStream = FXWindow.class.getClassLoader().getResource("window-fx.fxml");
        Parent root = FXMLLoader.load(resourceAsStream);
        primaryStage.setTitle("Text-Utils");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
        primaryStage.show();


    }
}
