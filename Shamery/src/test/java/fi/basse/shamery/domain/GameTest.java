package fi.basse.shamery.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import fi.basse.shamery.scoring.PointScoring;
import fi.basse.shamery.scoring.Scoring;

public class GameTest {
    Scoring scoring;
    Game game;

    @Before
    public void setUp() {
        scoring = mock(PointScoring.class);
        game = new Game();
        game.setScoring(scoring);

        // Force first few cards to be of deterministic type.
        getCard(0).setCode(1);
        getCard(0).setPairing(1);
        getCard(1).setCode(2);
        getCard(2).setCode(1);
        getCard(2).setPairing(2);
        getCard(3).setCode(1);
        getCard(3).setPairing(1);
    }

    @Test
    public void firstReveal() {
        Card card = getCard(0);
        game.reveal(card);
        assertTrue(card.isRevealed());
        verify(scoring).startTurn();
    }

    @Test
    public void noMatch() {
        game.reveal(getCard(0));
        game.reveal(getCard(1));
        verify(scoring).endTurn();
    }

    @Test
    public void foundMatch() {
        game.reveal(getCard(0));
        game.reveal(getCard(2));
        verify(scoring).continueTurn();
    }

    @Test
    public void maxTwoCardsRevealed() {
        game.reveal(getCard(0));
        game.reveal(getCard(1));
        game.reveal(getCard(2));
        assertTrue(getCard(0).isRevealed());
        assertTrue(getCard(1).isRevealed());
        assertTrue(!getCard(2).isRevealed());
    }

    @Test
    public void cardsFromSamePairingDontMatch() {
        game.reveal(getCard(0));
        game.reveal(getCard(3));
        verify(scoring, never()).continueTurn();
    }

    @Test
    public void sameCardTwiceInRow() {
        game.reveal(getCard(0));
        game.reveal(getCard(0));
        verify(scoring, never()).continueTurn();
        verify(scoring, never()).endTurn();
        assertTrue(getCard(0).isRevealed());
    }

    private Card getCard(int i) {
        return game.getDeck().getCards().get(i);
    }
}
