package fi.basse.shamery.ui;

import java.util.ArrayList;
import java.util.List;
import fi.basse.shamery.domain.Player;
import fi.basse.shamery.scoring.PointScoring;
import fi.basse.shamery.scoring.Scoring;
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

        // Buttons for each game type.
        Label selectPrompt = new Label("...and select a game mode:");

        Button pointsGameButton = gameButton("POINTS GAME", "points-game-button", new PointScoring());
        Button timeTrialButton = gameButton("TIME TRIAL", "time-trial-button", new TimeScoring());

        HBox startButtons = new HBox();
        startButtons.getChildren().addAll(pointsGameButton, timeTrialButton);
        startButtons.setId("game-button-container");

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(logo, prompt, nameGrid(), selectPrompt, startButtons);
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

    private GridPane nameGrid() {
        // One or two input fields for names.
        GridPane gp = new GridPane();
        gp.setId("name-grid");

        for (int i = 1; i <= noPlayers; i++) {
            Label label = new Label(String.format("Player %s", i));
            TextField tf = new TextField(String.format("Player %s", i));
            nameFields.add(tf);
            gp.add(label, 1, i);
            gp.add(tf, 2, i);
        }

        return gp;
    }

    private Button gameButton(String label, String id, Scoring scoring) {
        Button gameButton = new Button(label);
        gameButton.setId(id);
        gameButton.setMinWidth(200);
        gameButton.setOnAction(e -> {
            addNames();
            gameUi.newGame(scoring);
        });

        return gameButton;
    }
}
