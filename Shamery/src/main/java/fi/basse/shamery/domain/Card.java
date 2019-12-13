package fi.basse.shamery.domain;

import java.util.Objects;

/**
 * Basic element of the game.
 * A Card has an image and at least one pair. The pairs are not similar but
 * rather identified by their card code.
 * @author joonas
 */
public class Card {
    private int code, id, pairing;
    private String name;
    private boolean isRemoved, isRevealed;

    /**
     * Construct a new Card.
     * @param id card id
     * @param pairing indicates which "side" of a pair this card is.
     * @param code type code for matching up pairs
     * @param name used to find the icon for this Card
     */
    public Card(int id, int pairing, int code, String name) {
        this.id = id;
        this.pairing = pairing;
        this.code = code;
        this.name = name;
        this.isRemoved = false;
        this.isRevealed = false;
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

    public void setRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean isRevealed) {
        this.isRevealed = isRevealed;
    }

    public int getPairing() {
        return pairing;
    }

    public void setPairing(int pairing) {
        this.pairing = pairing;
    }
}
