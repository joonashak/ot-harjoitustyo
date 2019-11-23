package fi.basse.shamery.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Joonas Häkkinen
 */
public class PairDataTest {
    PairData pd;
    
    @Before
    public void setUp() {
        pd = new PairData(1, "asd");
    }

    @Test
    public void typeOk() {
        assertEquals(1, pd.getType());
        pd.setType(2);
        assertEquals(2, pd.getType());
    }

    @Test
    public void nameOk() {
        assertEquals("asd", pd.getName());
        pd.setName("qwe");
        assertEquals("qwe", pd.getName());
    }
}
