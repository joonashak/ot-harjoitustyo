package fi.basse.shamery.domain;

/**
 * Game management.
 */
public class Game {
    Deck deck;

    public Game() {
        this.deck = new Deck(16);
    }

    public Deck getDeck() {
        return deck;
    }
}
