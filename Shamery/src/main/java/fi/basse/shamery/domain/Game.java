package fi.basse.shamery.domain;

import java.util.ArrayList;
import java.util.List;
import fi.basse.shamery.scoring.Scoring;

/**
 * Game management.
 */
public class Game {
    private Deck deck;
    private Scoring scoring;
    private List<Player> players;
    private boolean started;

    /**
     * Initialize a new Game.
     */
    public Game() {
        this.deck = new Deck(18);
        this.players = new ArrayList<>();
        this.started = false;
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
            scoring.startTurn();
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
            scoring.continueTurn();
        } else {
            card.setRevealed(true);
            scoring.endTurn();
        }
        
        // Test scoring
        for (Player p : players) {
            System.out.println("Score " + p.getName() + ": " + p.getScore());
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

    /**
     * Add a player to scoring system.
     * @param player Player instance.
     */
    public void addPlayer(Player player) {
        // Max two players.
        if (this.players.size() > 1) {
            System.out.println("ERROR: This game can be played by at most two players!");
            System.exit(1);
        }

        this.players.add(player);
    }

    public Deck getDeck() {
        return deck;
    }

    public Scoring getScoring() {
        return scoring;
    }

    /**
     * Define scoring for this game.
     * @param scoring class implementing Scoring interface.
     */
    public void setScoring(Scoring scoring) {
        scoring.setGame(this);
        this.scoring = scoring;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
