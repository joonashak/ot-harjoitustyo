package fi.basse.shamery.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import fi.basse.shamery.domain.Game;
import fi.basse.shamery.domain.Player;
import fi.basse.shamery.scoring.PointScoring;
import fi.basse.shamery.scoring.TimeScoring;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Scoreboard section of playing view.
 */
public class Scoreboard {
    Game game;
    List<Label> scoreFields;
    Timeline timeline;
    long timelineStart;
    int inTurn;

    /**
     * Initialise new Scoreboard.
     * @param game host Game.
     */
    public Scoreboard(Game game) {
        this.game = game;
        this.scoreFields = new ArrayList<>();
        this.inTurn = game.getScoring().getInTurn();
    }

    /**
     * Content Node to integrate this view with others.
     * @return Node the content.
     */
    public Node getContent() {
        HBox container = new HBox();

        for (Player p : game.getPlayers()) {
            Label name = new Label(p.getName());
            Label score = new Label();
            scoreFields.add(score);
            VBox vbox = new VBox(name, score);
            container.getChildren().addAll(vbox);
        }

        // Start score animation.
        timeline = createTimeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        return container;
    }

    /**
     * Update this Scoreboard with current scores.
     */
    public void update() {
        if (game.getScoring() instanceof PointScoring) {
            setPointScores();
        }
    }

    private void setPointScores() {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            int score = game.getPlayers().get(i).getScore();
            scoreFields.get(i).setText(String.format("%s", score));
        }
    }

    private void setTimingScores() {
        TimeScoring ts = (TimeScoring) game.getScoring();
        ts.update();

        for (int i = 0; i < game.getPlayers().size(); i++) {
            int score = game.getPlayers().get(i).getScore();
            scoreFields.get(i).setText(timeFromScore(score));
        }
    }

    private String timeFromScore(int score) {
        StringBuilder res = new StringBuilder();
        Date date = new Date(score);

        // Show minutes if > 0.
        if (score < 60 * 1000) {
            res.append(String.format("%tS", date));
        } else {
            res.append(String.format("%tM:%tS", date, date));
        }

        // Add tenths.
        int ms = score % 1000;
        res.append(String.format(".%d", ms / 100));

        return res.toString();
    }

    private Timeline createTimeline() {
        return new Timeline(
            new KeyFrame(Duration.millis(100),
                event -> {
                    if (game.getScoring() instanceof PointScoring) {
                        setPointScores();
                    } else {
                        setTimingScores();
                    }
                }));
    }

    // TODO: timeline should be closed.
}
