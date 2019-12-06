package fi.basse.shamery.ui;

import fi.basse.shamery.domain.Game;
import fi.basse.shamery.scoring.Scoring;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class Scoreboard {
    Scoring scoring;

    public Scoreboard(Game game) {
        this.scoring = game.getScoring();
    }

    public Node getContent() {
        Label l = new Label("This is an score board.");
        return l;
    }
}
