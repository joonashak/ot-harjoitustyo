package fi.basse.shamery.ui;

import fi.basse.shamery.db.ScoreDao;
import fi.basse.shamery.domain.Player;
import fi.basse.shamery.scoring.PointScoring;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EndOfGame {
    private GameUi gameUi;

    /**
     * View to announce the end of game and reveal the victor.
     * @param gameUi Host GameUi instance.
     */
    public EndOfGame(GameUi gameUi) {
        this.gameUi = gameUi;

        // Calling the constructor implies the game has ended -> save solo scores.
        if (gameUi.getGame().getPlayers().size() == 1) {
            try (ScoreDao dao = new ScoreDao()) {
                dao.save(gameUi.getGame().getScoring());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("*** ERROR: Failed to save score in db.");
            }
        }
    }
    
    /**
     * Scene containing this view.
     * @return Scene instance.
     */
    public Scene getScene() {
        VBox vbox = new VBox();
        vbox.setId("game-over");
        Label gameOver = new Label("GAME OVER");
        gameOver.setId("game-over-label");

        // Display winner's name or tie.
        Player winner = gameUi.getGame().getScoring().getLeader();
        Label result = new Label(winner == null
            ? "It's a draw!"
            : String.format("%s wins!", winner.getName()));
        result.setId("result-label");

        // Clicking anywhere goes back to main menu.
        vbox.setOnMouseClicked(onClick());
        Label info = new Label("Click anywhere to continue.");
        info.setId("info-label");

        vbox.getChildren().addAll(gameOver, result, scoreGrid(), info);
        return new Scene(vbox);
    }

    private HBox scoreGrid() {
        HBox hbox = new HBox();
        hbox.getStyleClass().addAll("align-center", "game-over-score-grid");

        for (Player player : gameUi.getGame().getPlayers()) {
            VBox vbox = new VBox();
            vbox.getStyleClass().add("align-center");
            
            String scoreStr = gameUi.getGame().getScoring() instanceof PointScoring
                ? Integer.toString(player.getScore())
                : String.format("%s s", Scoreboard.timeFromMs(player.getScore()));
            Label score = new Label(scoreStr);
            score.getStyleClass().add("game-over-score");

            Label name = new Label(player.getName());
            name.getStyleClass().add("game-over-name");

            vbox.getChildren().addAll(score, name);
            hbox.getChildren().add(vbox);
        }

        return hbox;
    }

    private EventHandler<MouseEvent> onClick() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gameUi.showMenu();
            }
        };
    }
}
