package fi.basse.shamery.ui;

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
            gameUi.newGame();
        });

        Button mpGameButton = new Button("Multiplayer");

        Button exitButton = new Button("Close");
        exitButton.setOnAction(e -> {
            try {
                gameUi.stop();
            } catch (Exception ex) {
                System.out.println(ex);
                System.exit(1);
            }
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(newGameButton, mpGameButton, exitButton);

        return new Scene(vbox);
    }
}
