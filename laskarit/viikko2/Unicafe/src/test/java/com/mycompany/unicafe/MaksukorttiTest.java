package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void rahanOtto() {
        kortti.otaRahaa(5);
        assertEquals(kortti.saldo(), 5);
    }
    
    @Test
    public void rahanPano() {
        kortti.lataaRahaa(5);
        assertEquals(kortti.saldo(), 15);
    }
    
    @Test
    public void saldoEiNeg() {
        assertTrue(!kortti.otaRahaa(11));
    }
    
    @Test
    public void tekstimuoto() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
