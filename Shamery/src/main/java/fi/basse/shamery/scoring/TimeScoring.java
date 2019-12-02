package fi.basse.shamery.scoring;

import fi.basse.shamery.domain.Game;

public class TimeScoring implements Scoring {
    Game game;
    Integer inTurn = null;
    long start = 0;

    @Override
    public void startTurn() {
        // Start timer here on first turn.
        if (inTurn == null) {
            inTurn = 0;
            start = System.currentTimeMillis();
        }
    }

    @Override
    public void continueTurn() {
        // End of game.
        if (game.getDeck().cardsLeft() == 0) {
            switchTiming();
        }
    }

    @Override
    public void endTurn() {
        // Timing switch in multiplayer.
        if (game.getPlayers().size() == 2) {
            switchTiming();
            inTurn = inTurn == 0 ? 1 : 0;
        }
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    private void switchTiming() {
        long elapsed = System.currentTimeMillis() - start;
        game.getPlayers().get(inTurn).incScore((int) elapsed);
        start = System.currentTimeMillis();
    }

    @Override
    public Integer getInTurn() {
        return inTurn;
    }
}
