package fi.basse.shamery.scoring;

import fi.basse.shamery.domain.Game;

public class TimeScoring implements Scoring {
    Game game;
    int inTurn = 0;
    long start = 0;

    @Override
    public void startTurn() {
        // Start timer here on first turn.
        if (start == 0) {
            start = System.currentTimeMillis();
            game.setStarted(true);
        }
    }

    @Override
    public void continueTurn() {
        // End of game.
        if (game.getDeck().cardsLeft() == 0) {
            update();
        }
    }

    @Override
    public void endTurn() {
        // Timing switch in multiplayer.
        if (game.getPlayers().size() == 2) {
            update();
            inTurn = inTurn == 0 ? 1 : 0;
        }
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    public void update() {
        if (!game.isStarted()) {
            return;
        }
        
        long elapsed = System.currentTimeMillis() - start;
        game.getPlayers().get(inTurn).incScore((int) elapsed);
        start = System.currentTimeMillis();
    }

    @Override
    public int getInTurn() {
        return inTurn;
    }
}
