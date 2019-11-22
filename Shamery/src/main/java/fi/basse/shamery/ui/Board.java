package fi.basse.shamery.ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Board {
    Stage primaryStage;

    public Board(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene getScene() {
        Label label = new Label("teeeest");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(label);
        return new Scene(vbox, 800, 600);
    }
}
