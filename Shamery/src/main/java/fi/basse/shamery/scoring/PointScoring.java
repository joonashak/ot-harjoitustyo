package fi.basse.shamery.scoring;

import java.util.List;

import fi.basse.shamery.domain.Game;
import fi.basse.shamery.domain.Player;

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

    @Override
    public Player getLeader() {
        List<Player> players = game.getPlayers();

        if (players.size() == 1) {
            return players.get(0);
        }

        if (players.get(0).getScore() == players.get(1).getScore()) {
            return null;
        }

        return players.get(0).getScore() > players.get(1).getScore()
            ? players.get(0)
            : players.get(1); 
    }
}
