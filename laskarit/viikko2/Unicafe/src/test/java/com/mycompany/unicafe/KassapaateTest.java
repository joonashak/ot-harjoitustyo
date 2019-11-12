/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joonas
 */
public class KassapaateTest {
    
    Maksukortti kortti, tyhjaKortti;
    Kassapaate kassa;
    
    public KassapaateTest() {
    }
    
    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
        tyhjaKortti = new Maksukortti(0);
        kassa = new Kassapaate();
    }
    
    @Test
    public void alkukassa() {
        assertEquals(kassa.kassassaRahaa(), 100000);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 0);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void kateisosto() {
        int v = kassa.syoEdullisesti(300);
        assertEquals(v, 60);
        assertEquals(kassa.kassassaRahaa(), 100240);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 1);
    }
    
    @Test
    public void kateisostoEiOk() {
        int v = kassa.syoEdullisesti(200);
        assertEquals(v, 200);
        assertEquals(kassa.kassassaRahaa(), 100000);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 0);
    }
    
    @Test
    public void kateisostoMaukas() {
        int v = kassa.syoMaukkaasti(500);
        assertEquals(v, 100);
        assertEquals(kassa.kassassaRahaa(), 100400);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 1);
    }
    
    @Test
    public void kateisostoMaukasEiOk() {
        int v = kassa.syoMaukkaasti(200);
        assertEquals(v, 200);
        assertEquals(kassa.kassassaRahaa(), 100000);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void korttiosto() {
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(kortti.saldo(), 760);
        assertEquals(kassa.kassassaRahaa(), 100000);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 1);
    }
    
    @Test
    public void korttiostoEiOk() {
        assertTrue(!kassa.syoEdullisesti(tyhjaKortti));
        assertEquals(tyhjaKortti.saldo(), 0);
        assertEquals(kassa.kassassaRahaa(), 100000);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 0);
    }
    
    @Test
    public void korttiostoMaukas() {
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(kortti.saldo(), 600);
        assertEquals(kassa.kassassaRahaa(), 100000);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 1);
    }
    
    @Test
    public void korttiostoMaukasEiOk() {
        assertTrue(!kassa.syoMaukkaasti(tyhjaKortti));
        assertEquals(tyhjaKortti.saldo(), 0);
        assertEquals(kassa.kassassaRahaa(), 100000);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void rahanlataus() {
        kassa.lataaRahaaKortille(kortti, 10);
        assertEquals(1010, kortti.saldo());
        assertEquals(100010, kassa.kassassaRahaa());
    }
    
    @Test
    public void eiVoiLadataNeg() {
        kassa.lataaRahaaKortille(kortti, -10);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
