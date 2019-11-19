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

    public Card(Integer id, Integer code) {
        this.id = id;
        this.code = code;
    }
    
    public boolean matches(Card other) {
        return Objects.equals(this.code, other.code);
    }

    public void remove() {
        this.isRemoved = true;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        return Objects.equals(this.id, other.id);
    }
}
