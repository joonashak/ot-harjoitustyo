package fi.basse.shamery.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ScoreTypeDao extends Dao {
    /**
     * Default constructor.
     * @throws SQLException Thrown if database connection fails.
     */
    public ScoreTypeDao() throws SQLException {
        super();
    }
    
    /**
     * Get all score types.
     * @return Map with rowid as key and name as value.
     * @throws SQLException Thrown if query fails.
     */
    public HashMap<Integer, String> findAll() throws SQLException {
        String sql = "SELECT rowid, name FROM ScoreType;";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        HashMap<Integer, String> res = new HashMap<>();

        while (rs.next()) {
            res.put(rs.getInt("rowid"), rs.getString("name"));
        }

        return res;
    }
}
