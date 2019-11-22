package fi.basse.shamery.domain;

import java.util.Objects;

/**
 * Basic element of the game.
 * A Card has an image and at least one pair. The pairs are not similar but
 * rather identified by their card code.
 * @author joonas
 */
public class Card {
    int code, id;
    private boolean isRemoved = false;

    /**
     * Construct a new Card.
     * @param id card id
     * @param code type code for matching up pairs
     */
    public Card(int id, int code) {
        this.id = id;
        this.code = code;
    }
    
    /**
     * Check if this Card matches other.
     * @param other Card to check
     * @return true if both card have the same type code.
     */
    public boolean matches(Card other) {
        return Objects.equals(this.code, other.code);
    }

    /**
     * Mark this Card as removed from the game.
     */
    public void remove() {
        this.isRemoved = true;
    }

    public boolean isRemoved() {
        return isRemoved;
    }
}
