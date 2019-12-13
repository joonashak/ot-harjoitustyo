package fi.basse.shamery.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        Label logo = new Label("Shamery");
        logo.getStyleClass().add("logo");

        Button newGameButton = new Button("Single Player");
        newGameButton.setOnAction(e -> gameUi.setUpGame(1));

        Button mpGameButton = new Button("Multiplayer");
        mpGameButton.setOnAction(e -> gameUi.setUpGame(2));

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
        vbox.setId("main-menu");
        vbox.getChildren().addAll(logo, newGameButton, mpGameButton, exitButton);

        return new Scene(vbox);
    }
}
