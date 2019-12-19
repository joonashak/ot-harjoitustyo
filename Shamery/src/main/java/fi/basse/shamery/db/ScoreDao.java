package fi.basse.shamery.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import fi.basse.shamery.domain.Player;
import fi.basse.shamery.scoring.PointScoring;
import fi.basse.shamery.scoring.Scoring;

public class ScoreDao extends Dao {
    public ScoreDao() throws SQLException {
        super();
    }
    
    /**
     * Save new score.
     * Player will be created if not found. Game type will be inferred from scoring
     * object class.
     * @param scoring Leader's score from this object is saved.
     * @throws Exception Thrown on any error.
     */
    public void save(Scoring scoring) throws Exception {
        // Figure out score_type_id. This must match the rowids in db_init.sql!
        int scoreTypeId = scoring instanceof PointScoring ? 1 : 2;

        // Get player rowid, creating if necessary.
        int playerId;
        try (PlayerDao playerDao = new PlayerDao()) {
            playerId = playerDao.getIdOrCreate(scoring.getLeader().getName());
        } catch (Exception e) {
            throw e;
        }

        // And save everything.
        String sql = "INSERT INTO Score (player_id, score_type_id, score) VALUES (?,?,?);";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, playerId);
        stmt.setInt(2, scoreTypeId);
        stmt.setInt(3, scoring.getLeader().getScore());
        stmt.execute();
    }
    
    /**
     * Find the best score for each unique player for the given score type.
     * @param scoreTypeId Search is limited to this ScoreType.rowid.
     * @return List of Player objects with the personal best as score.
     * @throws SQLException Thrown is query fails.
     */
    public List<Player> getHighscoresByType(int scoreTypeId) throws SQLException {
        String sql = new StringBuilder()
            .append("SELECT Player.name, MAX(score) AS highscore ")
            .append("FROM Score LEFT JOIN Player ON Score.player_id = Player.rowid ")
            .append("WHERE score_type_id = ? GROUP BY player_id ")
            .append("ORDER BY highscore DESC;")
            .toString();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, scoreTypeId);
        ResultSet rs = stmt.executeQuery();

        List<Player> res = new ArrayList<>();

        while (rs.next()) {
            Player player = new Player(rs.getString("name"));
            player.incScore(rs.getInt("highscore"));
            res.add(player);
        }

        return res;
    }
}
