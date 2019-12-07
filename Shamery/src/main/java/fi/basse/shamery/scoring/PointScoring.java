package fi.basse.shamery.scoring;

import fi.basse.shamery.domain.Game;

public class PointScoring implements Scoring {
    Game game;
    int inTurn = 0;

    @Override
    public void startTurn() {
        // Not used in points scoring.
    }

    @Override
    public void continueTurn() {
        if (game.getPlayers().size() == 2) {
            game.getPlayers().get(inTurn).incScore(1);
        }
    }

    @Override
    public void endTurn() {
        if (game.getPlayers().size() == 1) {
            game.getPlayers().get(0).incScore(1);
        } else {
            inTurn = inTurn == 0 ? 1 : 0;
        }
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public int getInTurn() {
        return inTurn;
    }
}
