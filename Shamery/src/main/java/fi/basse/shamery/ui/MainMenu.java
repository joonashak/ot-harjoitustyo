package fi.basse.shamery.ui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainMenu {
    GameUi gameUi;

    /**
     * Main menu and starting point of the application.
     * @param gameUi GameUI instance.
     */
    public MainMenu(GameUi gameUi) {
        this.gameUi = gameUi;   
    }

    /**
     * Scene implementing the main menu.
     * @return Scene
     */
    public Scene getScene() {
        Button newGameButton = new Button("Single Player");
        newGameButton.setOnAction(e -> {
            Board board = new Board();
            gameUi.setScene(board.getScene());
        });

        Button mpGameButton = new Button("Multiplayer");

        Button exitButton = new Button("Close");
        exitButton.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(newGameButton, mpGameButton, exitButton);

        return new Scene(vbox, 300, 200);
    }
}
