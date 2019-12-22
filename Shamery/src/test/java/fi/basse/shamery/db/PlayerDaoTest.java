package fi.basse.shamery.db;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PlayerDaoTest {
    @Before
    public void setUp() throws Exception {
        Database db = new Database();
        db.deleteDbFile();
        db.init();
    }

    @Test
    public void savingOk() {
        try (PlayerDao dao = new PlayerDao()) {
            dao.save("test player");
            assertNotNull(dao.findByName("test player"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByNameOk() {
        try (PlayerDao dao = new PlayerDao()) {
            dao.save("test player 1");
            dao.save("test player 2");
            assertEquals((Integer) 1, dao.findByName("test player 1"));
            assertEquals((Integer) 2, dao.findByName("test player 2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByNameNotFound() {
        try (PlayerDao dao = new PlayerDao()) {
            assertNull(dao.findByName("not found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getIdOrCreateFindsExisting() {
        try (PlayerDao dao = new PlayerDao()) {
            dao.save("test player 1");
            assertEquals(1, dao.getIdOrCreate("test player 1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getIdOrCreateCreatesNew() {
        try (PlayerDao dao = new PlayerDao()) {
            dao.save("test player 1");
            assertEquals(2, dao.getIdOrCreate("test player 2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
