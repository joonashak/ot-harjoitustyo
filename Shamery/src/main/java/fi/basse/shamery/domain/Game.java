package fi.basse.shamery.domain;

import java.util.List;

/**
 * Game management.
 */
public class Game {
    Deck deck;
    Scoring scoring;

    /**
     * Initialize a new Game. WIP.
     */
    public Game() {
        this.deck = new Deck(18);
        this.scoring = new Scoring();
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
            // TODO: scoring: turn start
            return;
        }

        // Two cards already open or same card clicked twice in row.
        if (openCards.size() > 1 || openCards.get(0).getId() == card.getId()) {
            return;
        }
        
        // Otherwise check for a match.
        if (openCards.get(0).getCode() == card.getCode()) {
            card.setRemoved(true);
            openCards.get(0).setRemoved(true);
            // TODO: scoring: points, turn end
        } else {
            card.setRevealed(true);
            // TODO: scoring turn end
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

    public Scoring getScoring() {
        return scoring;
    }
}
