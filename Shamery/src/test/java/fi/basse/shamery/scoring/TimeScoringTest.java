package fi.basse.shamery.scoring;

import org.junit.Test;

import fi.basse.shamery.domain.Card;
import fi.basse.shamery.domain.Game;
import fi.basse.shamery.domain.Player;
import static org.junit.Assert.*;
import org.junit.Before;

public class TimeScoringTest {
    TimeScoring ts;
    Player p1;
    Game game;
    
    @Before
    public void setUp() {
        // Create single player game.
        game = new Game();
        p1 = new Player("a");
        game.addPlayer(p1);
        ts = new TimeScoring();
        game.setScoring(ts);
    }

    @Test
    public void firstTurnStarts() {
        ts.startTurn();
        assertTrue(game.isStarted());
    }

    @Test
    public void turnChanges() {
        game.addPlayer(new Player("b"));
        ts.startTurn();
        ts.endTurn();
        assertEquals(1, (int) ts.getInTurn());
        ts.endTurn();
        assertEquals(0, (int) ts.getInTurn());
    }

    @Test
    public void singleTimerContinuesThroughTurns() {
        ts.startTurn();
        sleep();
        ts.continueTurn();
        sleep();
        ts.endTurn();
        assertEquals(0, p1.getScore());
    }

    @Test
    public void singleTimerRecordedAtGameEnd() {
        ts.startTurn();
        sleep();

        // Mark all cards as removed to cause game to end.
        for (Card c : game.getDeck().getCards()) {
            c.remove();
        }

        ts.continueTurn();
        assertTrue(p1.getScore() > 0);
    }

    @Test
    public void multiTimerWorksPlayer1() {
        game.addPlayer(new Player("b"));
        ts.startTurn();
        sleep();
        ts.endTurn();
        assertTrue(p1.getScore() > 0);
    }

    @Test
    public void multiTimerWorksPlayer2() {
        Player p2 = new Player("b");
        game.addPlayer(p2);
        ts.startTurn();
        ts.endTurn();
        sleep();
        ts.endTurn();
        assertTrue(p2.getScore() > 0);
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }
    }
}
