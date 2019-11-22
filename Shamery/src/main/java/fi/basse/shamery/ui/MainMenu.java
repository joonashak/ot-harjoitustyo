package fi.basse.shamery.ui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {
    Stage primaryStage;

    public MainMenu(Stage primaryStage) {
        this.primaryStage = primaryStage;    
    }

    public Scene getScene() {
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> {
            Board board = new Board(primaryStage);
            primaryStage.setScene(board.getScene());
        });

        Button exitButton = new Button("Close");
        exitButton.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(newGameButton, exitButton);

        return new Scene(vbox, 300, 200);
    }
}
