package fi.basse.shamery.ui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {
    Stage primaryStage;

    /**
     * Main menu and starting point of the application.
     * @param primaryStage Stage for toggling Scene.
     */
    public MainMenu(Stage primaryStage) {
        this.primaryStage = primaryStage;    
    }

    /**
     * Scene implementing the main menu.
     * @return Scene
     */
    public Scene getScene() {
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> {
            Board board = new Board();
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
