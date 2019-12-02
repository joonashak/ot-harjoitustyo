package fi.basse.shamery.scoring;

import fi.basse.shamery.domain.Game;

public interface Scoring {

    public void startTurn();

    public void continueTurn();

    public void endTurn();

    public void setGame(Game game);
}
