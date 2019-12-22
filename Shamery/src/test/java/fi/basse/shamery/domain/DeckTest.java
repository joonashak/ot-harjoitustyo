package fi.basse.shamery.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Joonas Häkkinen
 */
public class DeckTest {
    Deck deck;
    
    @Before
    public void setUp() {
        deck = new Deck(16);
    }

    @Test
    public void noCards() {
        assertEquals(16, deck.getCards().size());
    }

    @Test
    public void oddSizesCorrected() {
        Deck d = new Deck(15);
        assertEquals(16, d.getCards().size());
    }

    @Test
    public void sizeRange() {
        Deck small = new Deck(1);
        Deck large = new Deck(100);
        assertEquals(2, small.getCards().size());
        assertEquals(64, large.getCards().size());
    }

    @Test
    public void getOpenCardsOk() {
        deck.getCards().get(0).remove();
        deck.getCards().get(0).setRevealed(true);
        deck.getCards().get(1).setRevealed(true);
        assertEquals(1, deck.getOpenCards().size());
    }
}
