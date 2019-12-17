package fi.basse.shamery.ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class EndOfGame {
    public Scene getScene() {
        VBox vbox = new VBox();
        Label gameOver = new Label("GAME OVER");
        vbox.getChildren().addAll(gameOver);
        return new Scene(vbox);
    }
}
