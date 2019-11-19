/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.basse.shamery.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author joonas
 */
public class CardTest {
    Card card;
    
    public CardTest() {
    }
    
    @Before
    public void setUp() {
        card = new Card(1, 99);
    }

    @Test
    public void idOk() {
        assertEquals(1, card.id);
    }
    
    @Test
    public void codeOk() {
        assertEquals(99, card.code);
    }
    
    @Test
    public void notRemoved() {
        assertTrue(!card.isRemoved());
    }
    
    @Test
    public void matchesOk() {
        Card card2 = new Card(1, 999);
        assertTrue(card.matches(card));
        assertTrue(!card.matches(card2));
    }
    
    @Test
    public void removeOk() {
        card.remove();
        assertTrue(card.isRemoved());
    }
}
