package fi.basse.shamery.scoring;

import org.junit.Test;
import fi.basse.shamery.domain.Game;
import fi.basse.shamery.domain.Player;
import static org.junit.Assert.*;
import org.junit.Before;

public class PointScoringTest {
    PointScoring ps;
    Player p1;
    Game game;
    
    @Before
    public void setUp() {
        // Create single player game.
        game = new Game();
        p1 = new Player("a");
        game.addPlayer(p1);
        ps = new PointScoring();
        game.setScoring(ps);
    }

    @Test
    public void ptsIncreaseSingle() {
        ps.continueTurn();
        assertEquals(0, p1.getScore());
        ps.endTurn();
        assertEquals(1, p1.getScore());
    }

    @Test
    public void ptsIncreaseMulti() {
        Player p2 = new Player("b");
        game.addPlayer(p2);
        ps.continueTurn();
        assertEquals(1, p1.getScore());
        assertEquals(0, p2.getScore());
    }

    @Test
    public void turnChanges() {
        game.addPlayer(new Player("b"));
        ps.endTurn();
        assertEquals(1, (int) ps.getInTurn());
        ps.endTurn();
        assertEquals(0, (int) ps.getInTurn());
    }
}
