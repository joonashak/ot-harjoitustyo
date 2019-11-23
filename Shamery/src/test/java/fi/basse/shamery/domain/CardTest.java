package fi.basse.shamery.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Joonas Häkkinen
 */
public class CardTest {
    Card card;
    
    @Before
    public void setUp() {
        card = new Card(1, 99, "asd");
    }

    @Test
    public void idOk() {
        card.setId(900);
        assertEquals(900, card.getId());
    }
    
    @Test
    public void codeOk() {
        card.setCode(98);
        assertEquals(98, card.getCode());
    }
    
    @Test
    public void notRemoved() {
        assertTrue(!card.isRemoved());
    }
    
    @Test
    public void matchesOk() {
        Card card2 = new Card(1, 999, "asd");
        assertTrue(card.matches(card));
        assertTrue(!card.matches(card2));
    }
    
    @Test
    public void removeOk() {
        card.remove();
        assertTrue(card.isRemoved());
    }

    @Test
    public void nameOk() {
        card.setName("abc");
        assertEquals("abc", card.getName());
    }

    @Test
    public void stringOk() {
        assertEquals("Card - id: 1, code: 99, name: asd, removed: false", card.toString());
    }
}
