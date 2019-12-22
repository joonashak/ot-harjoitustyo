package fi.basse.shamery.db;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import fi.basse.shamery.domain.Game;
import fi.basse.shamery.domain.Player;
import fi.basse.shamery.scoring.PointScoring;
import fi.basse.shamery.scoring.Scoring;
import fi.basse.shamery.scoring.TimeScoring;

public class ScoreDaoTest {
    @Before
    public void setUp() throws Exception {
        Database db = new Database();
        db.deleteDbFile();
        db.init();
    }

    @Test
    public void saveAndFindPointsGame() {
        Player player = new Player("test player 1");
        player.incScore(100);
        Game game = new Game();
        game.addPlayer(player);
        Scoring scoring = new PointScoring();
        scoring.setGame(game);

        try (ScoreDao dao = new ScoreDao()) {
            dao.save(scoring);
            List<Player> res = dao.getHighscoresByType(1);
            assertEquals(1, res.size());
            assertEquals(100, res.get(0).getScore());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveAndFindTimeTrial() {
        Player player = new Player("test player 1");
        player.incScore(100);
        Game game = new Game();
        game.addPlayer(player);
        Scoring scoring = new TimeScoring();
        scoring.setGame(game);

        try (ScoreDao dao = new ScoreDao()) {
            dao.save(scoring);
            List<Player> res = dao.getHighscoresByType(2);
            assertEquals(1, res.size());
            assertEquals(100, res.get(0).getScore());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
