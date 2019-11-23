package fi.basse.shamery.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Deck is a collection of Cards.
 * @author joonas
 */
public class Deck {
    private List<Card> cards = new ArrayList<>();
    
    /**
     * Construct a new Deck of given size.
     * The deck will be populated with random cards.
     * @param size number of Cards to include.
     */
    public Deck(int size) {
        // Make sure size is even and between 2-64.
        size += size % 2;
        size = Math.min(64, Math.max(2, size));

        // Generate a deck of the given size.
        CardPairs cardPairs = new CardPairs();
        for (int i = 0; i < size; i = i + 2) {
            List<Card> pair = cardPairs.next(i);
            cards.add(pair.get(0));
            cards.add(pair.get(1));
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}
