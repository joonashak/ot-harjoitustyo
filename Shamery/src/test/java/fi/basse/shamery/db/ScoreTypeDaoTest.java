package fi.basse.shamery.db;

import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.BeforeClass;
import org.junit.Test;

public class ScoreTypeDaoTest {
    @BeforeClass
    public static void setUp() throws Exception {
        Database db = new Database();
        db.deleteDbFile();
        db.init();
    }

    @Test
    public void correctTypesFound() {
        try (ScoreTypeDao dao = new ScoreTypeDao()) {
            HashMap<Integer, String> res = dao.findAll();
            assertEquals("Points Game", res.get(1));
            assertEquals("Time Trial", res.get(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
