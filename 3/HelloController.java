package com.example.gyak3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Az FXML irja le es tarolja, az alkalmazas KINEZETET
        // Futas kozben tolti be igy, ha valami nem jo az fxml-ben az csak futaskor derul ki
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        //ModuleLayer.Controller controller = fxmlLoader.getController();
        stage.setTitle("Hello!");
        stage.setScene(scene);

        /////////////// 1 ///////////////
//        withoutFXML(stage);
        /////////////// 1 ///////////////
        stage.show();
    }


    /////////////// 1 ///////////////
    // Ugyanezt el lehet erni FXML segitsegevel, amit az fxmlLoader betolt
    public void withoutFXML(Stage stage) {
        VBox box = new VBox();
        box.getChildren().addAll(new Label("Oi"), new Button("Button"));
        Scene scene = new Scene(box, 320, 240);
        stage.setScene(scene);
    }
    /////////////// 1 ///////////////

    public static void main(String[] args) {
        launch();
    }
}
