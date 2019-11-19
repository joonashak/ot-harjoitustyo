/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.basse.shamery.domain;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joonas
 */
public class CardTest {
    static Card card;
    
    public CardTest() {
    }
    
    @BeforeClass
    static public void setUp() {
        card = new Card(1, 99);
    }

    @Test
    public void initializedOk() {
        assertEquals(1, card.id);
    }
}
