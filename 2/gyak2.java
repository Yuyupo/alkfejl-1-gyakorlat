package com.example.alkfejl2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

// Minden JavaFx alkalmazasnak az Applicationbol kell szarmaznia
public class HelloApplication extends Application {
    // Stage az maga az ablak
    private Stage mainWindow;
    // A scenekhez adjuk hozza a gui elemeket, majd rendeljuk hozza az ablakhoz
    private Scene scene1, scene2;

    // Fo belepesi pont, az alkalmazas elindulasakor fog meghivodni
    @Override
    public void start(Stage stage) throws IOException {
        // Betolti az FXML file-t a letrehozott loader object segitsegevel (resourcesban megtalalhato)
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // A scene parameterei, amik az elobb megadott fxml-bol jonnek
        // Alapbol a Stage-hez nincs hozza rendelve scene
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        // Az ablak title-je
        stage.setTitle("Hello!");

        /////////////// 2 ///////////////
        // Ablak stilusa megvaltoztathato, ha az initStyle-ra rahivunk a show elott
        // stage.initStyle(StageStyle.UNDECORATED);
        /////////////// 2 ///////////////

        /////////////// 3 ///////////////
        // Ablak atmeretezes letiltasa es engedelyezese
        // stage.setResizable(true);
        /////////////// 3 ///////////////

        /////////////// 4 ///////////////
        // Beallithatunk minimalis, maximalis szelesseget, magassagot
        // stage.setMinWidth(), setMaxWidth()
        /////////////// 4 ///////////////

        // Hogyan adjunk hozza elemeket
        /////////////// 5 ///////////////
//        addBasicElements(stage);
        /////////////// 5 ///////////////

        // Event handling
        /////////////// 6 ///////////////
//        addEventHandling(stage);
        /////////////// 6 ///////////////

        // Scene management
        /////////////// 7 ///////////////
//        sceneManagement(stage);
        /////////////// 7 ///////////////

        // Basic GUI elements
        /////////////// 8 ///////////////
        // Combo box
//        addComboBox(stage);

        // Spinner
//        addSpinner(stage);

        // Check Box
//        addCheckbox(stage);

        // Radio Box
//        addToggleGroup(stage);
        /////////////// 8 ///////////////

        /////////////// 9 ///////////////
        addAlert();
        /////////////// 9 ///////////////

        // Rendereli az ablakot a stage-hez utoljara hozza rendelt scene-el
        stage.show();
    }

    /////////////// 5 ///////////////
    private void addBasicElements(Stage stage) {
        // Vertikalis box, ami elemeket tarol, es hozza adjuk a scenehez
         VBox root = new VBox();
         Text msg = new Text("I'm a message");
         Text msg2 = new Text("I'm a message too");

        // getChildrennel le lehet kerni a tarolo osszes gyereket listakent
         root.getChildren().add(msg);
         root.getChildren().add(msg2);

         Scene scene2 = new Scene(root, 320, 240);
         stage.setScene(scene2);
    }
    /////////////// 5 ///////////////

    /////////////// 6 ///////////////
    private void addEventHandling(Stage stage) {
        VBox root = new VBox();
        Text msg = new Text("I'm a message");
        Button exitButton = new Button("Exit");
        root.getChildren().add(msg);
        root.getChildren().add(exitButton);

        // Lambda kifejezes
        exitButton.setOnAction(e -> {
            Platform.exit();
        });

        Scene scene3 = new Scene(root, 320, 240);
        stage.setScene(scene3);
    }
    /////////////// 6 ///////////////

    /////////////// 7 ///////////////
    private void sceneManagement(Stage stage) {
        mainWindow = stage;

        // Letrehozza a ket scene-t a rajta talalhato GUI elemekkel es a funkcionalitasaval
        constructScene1();
        constructScene2();

        stage.setScene(scene1); // Default scene beallitasa
    }

    private void constructScene1() {
        Label lb = new Label("Scene 1");
        Button btn = new Button("Go to Scene 2");
        btn.setOnAction(e -> {
            mainWindow.setScene(scene2);
        });

        //Some layout
        VBox root = new VBox();
        root.getChildren().addAll(lb, btn);

        scene1 = new Scene(root, 300, 300);
    }

    private void constructScene2() {
        Label lb = new Label("Scene 2");
        Button btn = new Button("Go to Scene 1");
        btn.setOnAction(e -> {
            mainWindow.setScene(scene1);
        });

        //Some layout
        VBox root = new VBox();
        root.getChildren().addAll(lb, btn);

        scene2 = new Scene(root, 300, 300);
    }
    /////////////// 7 ///////////////

    /////////////// 8 ///////////////
    public void addComboBox(Stage stage) {
        VBox root = new VBox();
        Button logButton = new Button("Chosen one");
        root.getChildren().add(logButton);

        ObservableList<String> days = FXCollections.observableArrayList(
                "Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday", "Sunday");

        ComboBox<String> combo = new ComboBox<String>();

        combo.setItems(days); //ertekkeszlet beallitasa
        combo.setValue("Friday"); //default érték
        combo.setEditable(true); // modosithato-e

        root.getChildren().add(combo);

        // Get the value of the combo box on btnp
        logButton.setOnAction( e -> {
            System.out.println(combo.getValue());
        });
        Scene scene2 = new Scene(root, 320, 240);
        stage.setScene(scene2);
    }

    public void addSpinner(Stage stage) {
        VBox root = new VBox();
        Button logButton = new Button("Chosen one");
        root.getChildren().add(logButton);

        Spinner<Integer> spinner = new Spinner<Integer>();
        // meg kell adni neki valamilyen értékkészletet amit használ
        SpinnerValueFactory<Integer> valueFactory=
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 3);

        spinner.setValueFactory(valueFactory);
        spinner.setEditable(true); // ha szeretnénk, hogy beleírni is lehessen
        root.getChildren().add(spinner);
        logButton.setOnAction( e -> {
            System.out.println(spinner.getValue());
        });

        Scene scene2 = new Scene(root, 320, 240);
        stage.setScene(scene2);
    }

    public void addCheckbox(Stage stage) {
        VBox root = new VBox();
        Button logButton = new Button("Chosen one");
        root.getChildren().add(logButton);

        CheckBox box = new CheckBox();
        box.setText("Okos-e a cica");
        box.setSelected(true); //default ertek
        root.getChildren().add(box);

        logButton.setOnAction(actionEvent -> {
            if (box.isSelected()) {
                System.out.println("Obviously okos a cica");
            } else {
                System.out.println("Akkor is okos a cica :(");
            }
        });

        Scene scene2 = new Scene(root, 320, 240);
        stage.setScene(scene2);
    }

    public void addToggleGroup(Stage stage) {
        VBox root = new VBox();
        Button logButton = new Button("Chosen one");
        root.getChildren().add(logButton);

        ToggleGroup group = new ToggleGroup(); // groupba rakva egyszerre csak 1 valaszthato ki
        RadioButton button1 = new RadioButton("Kicsi cica");
        button1.setToggleGroup(group);
        button1.setSelected(true); // default értéknek bejelöljük ezt
        RadioButton button2 = new RadioButton("Nagy cica");
        button2.setToggleGroup(group);

        root.getChildren().add(button1);
        root.getChildren().add(button2);

        logButton.setOnAction(actionEvent -> {
            RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText();
            System.out.println("Kivalasztott gomb:" + toogleGroupValue);

            if (button1.isSelected()) {
                System.out.println("Pici a cica");
            } else if (button2.isSelected()){
                System.out.println("Dagadt deszno");
            }
        });

        Scene scene2 = new Scene(root, 320, 240);
        stage.setScene(scene2);
    }
    /////////////// 8 ///////////////

    /////////////// 9 ///////////////
    public void addAlert() {
        // Alert tipusa
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Cím");
        alert.setHeaderText("Az ablak tartalom felső header része");
        alert.setContentText("Részletesebb leírás a header text alatt");

        alert.showAndWait();
    }
    /////////////// 9 ///////////////
    
    public static void main(String[] args) {
        launch();
    }
}

