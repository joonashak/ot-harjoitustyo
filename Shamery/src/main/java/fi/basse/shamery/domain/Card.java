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
    String name;
    private boolean isRemoved = false;

    /**
     * Construct a new Card.
     * @param id card id
     * @param code type code for matching up pairs
     * @param name used to find the icon for this Card
     */
    public Card(int id, int code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
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

    @Override
    public String toString() {
        return new StringBuilder()
            .append("Card - id: ")
            .append(id)
            .append(", code: ")
            .append(code)
            .append(", name: ")
            .append(name)
            .append(", removed: ")
            .append(isRemoved)
            .toString();
    }
}
