package fi.basse.shamery.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameSetup {
    GameUi gameUi;
    int noPlayers;

    /**
     * View for setting up a new game.
     * @param gameUi parent GameUI.
     * @param noPlayers number of players (1 or 2).
     */
    public GameSetup(GameUi gameUi, int noPlayers) {
        this.gameUi = gameUi;
        this.noPlayers = noPlayers;
    }

    /**
     * Scene implementing this GameSetup view.
     * @return Scene
     */
    public Scene getScene() {
        Label prompt = new Label("Enter your name:");

        // One or two input fields for names.
        GridPane nameGrid = new GridPane();

        for (int i = 1; i <= noPlayers; i++) {
            Label label = new Label(String.format("Player %s", i));
            TextField tf = new TextField();
            nameGrid.add(label, 1, i);
            nameGrid.add(tf, 2, i);
        }

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            gameUi.newGame();
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(prompt, nameGrid, startButton);

        return new Scene(vbox, 300, 200);
    }
}
