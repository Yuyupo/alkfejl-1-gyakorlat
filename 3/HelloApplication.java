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

        stage.setTitle("Hello!");
        stage.setScene(scene);

        /////////////// 1 ///////////////
//        withoutFXML(stage);
        /////////////// 1 ///////////////

        /////////////// 2 ///////////////
//        customStyle(stage);
        /////////////// 2 ///////////////
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


    /////////////// 2 ///////////////
    public void customStyle(Stage stage) {
        // Hozza lehet adni css file-t is
        VBox box = new VBox();
        Button btn = new Button("1");
        // inline style megadas
        btn.setStyle("-fx-background-color: rgb(77, 102, 204)");

        Button btn2 = new Button("2");
        box.getChildren().addAll(btn, btn2);

        Scene scene = new Scene(box, 500, 600);
        // a css file utvonalaval betolteni
        scene.getStylesheets().addAll("hello.css");
        stage.setScene(scene);
    }
    /////////////// 2 ///////////////

    public static void main(String[] args) {
        launch();
    }
}
