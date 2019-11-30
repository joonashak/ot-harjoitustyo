package fi.basse.shamery.domain;

import java.util.List;

/**
 * Game management.
 */
public class Game {
    Deck deck;

    /**
     * Initialize a new Game. WIP.
     */
    public Game() {
        this.deck = new Deck(18);
    }

    /**
     * Try to reveal the given card.
     * If there is already a card open on the table, they are checked for a match.
     * @param card Card to be revealed.
     */
    public void reveal(Card card) {
        List<Card> openCards = deck.getOpenCards();
        // No cards are open.
        if (openCards.size() == 0) {
            card.setRevealed(true);
            return;
        }

        // Two cards already open.
        if (openCards.size() > 1) {
            return;
        }
        
        // Otherwise check for a match.
        if (openCards.get(0).getCode() == card.getCode()) {
            card.setRemoved(true);
            openCards.get(0).setRemoved(true);
        } else {
            card.setRevealed(true);
        }
        
    }

    /**
     * Hide all cards in the deck.
     */
    public void hideOpenCards() {
        for (Card c : deck.getOpenCards()) {
            c.setRevealed(false);
        }
    }

    public Deck getDeck() {
        return deck;
    }
}
