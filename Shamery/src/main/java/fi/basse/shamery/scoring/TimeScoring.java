package fi.basse.shamery.scoring;

import java.util.List;

import fi.basse.shamery.domain.Game;
import fi.basse.shamery.domain.Player;

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
        // In multiplayer, decrease time score for each match found.
        if (game.getPlayers().size() == 2) {
            game.getPlayers().get(inTurn).incScore(-2000);
        }

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

    @Override
    public Player getLeader() {
        List<Player> players = game.getPlayers();

        if (players.size() == 1) {
            return players.get(0);
        }

        if (players.get(0).getScore() == players.get(1).getScore()) {
            return null;
        }

        return players.get(0).getScore() < players.get(1).getScore()
            ? players.get(0)
            : players.get(1); 
    }
}
