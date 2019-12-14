package fi.basse.shamery.ui;

import java.util.ArrayList;
import java.util.List;
import fi.basse.shamery.domain.Player;
import fi.basse.shamery.scoring.PointScoring;
import fi.basse.shamery.scoring.TimeScoring;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameSetup {
    private GameUi gameUi;
    private int noPlayers;
    private List<TextField> nameFields = new ArrayList<>();

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
        Label logo = new Label("Shamery");
        logo.getStyleClass().add("logo");

        Label prompt = new Label("Enter your name...");

        // One or two input fields for names.
        GridPane nameGrid = new GridPane();
        nameGrid.setId("name-grid");

        for (int i = 1; i <= noPlayers; i++) {
            Label label = new Label(String.format("Player %s", i));
            TextField tf = new TextField(String.format("Player %s", i));
            nameFields.add(tf);
            nameGrid.add(label, 1, i);
            nameGrid.add(tf, 2, i);
        }

        // Buttons for each game type.
        Label selectPrompt = new Label("...and select a game mode:");

        Button pointsGameButton = new Button("POINTS GAME");
        pointsGameButton.setId("points-game-button");
        pointsGameButton.setMinWidth(200);
        pointsGameButton.setOnAction(e -> {
            addNames();
            gameUi.newGame(new PointScoring());
        });

        Button timeTrialButton = new Button("TIME TRIAL");
        timeTrialButton.setId("time-trial-button");
        timeTrialButton.setMinWidth(200);
        timeTrialButton.setOnAction(e -> {
            addNames();
            gameUi.newGame(new TimeScoring());
        });

        HBox startButtons = new HBox();
        startButtons.getChildren().addAll(pointsGameButton, timeTrialButton);
        startButtons.setId("game-button-container");

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(logo, prompt, nameGrid, selectPrompt, startButtons);
        vbox.getStyleClass().add("menu");

        return new Scene(vbox);
    }

    /**
     * Add player names in TextFields to scoring system.
     */
    private void addNames() {
        for (TextField tf : nameFields) {
            Player player = new Player(tf.getText());
            gameUi.getGame().addPlayer(player);
        }
    }
}
