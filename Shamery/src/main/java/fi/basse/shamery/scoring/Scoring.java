package fi.basse.shamery.scoring;

import fi.basse.shamery.domain.Game;

public interface Scoring {

    /**
     * Start new turn.
     */
    public void startTurn();

    /**
     * Continue current turn.
     * Implies that a matching pair was found.
     */
    public void continueTurn();

    /**
     * End current turn.
     * Implies that a matching pair was not found.
     */
    public void endTurn();

    /**
     * Set Game instance.
     * This instance should include the players to be scored.
     * @param game Game instance.
     */
    public void setGame(Game game);

    /**
     * Index of player currently in turn.
     * @return player index no.
     */
    public int getInTurn();
}
