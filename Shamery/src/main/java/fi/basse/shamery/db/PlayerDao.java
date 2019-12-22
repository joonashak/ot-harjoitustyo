package fi.basse.shamery.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDao extends Dao {
    /**
     * Create new Database Access Object.
     * Connection is activated on constructor call and must be closed by calling
     * Dao.close() once done.
     * @throws SQLException Thrown on connection fail.
     */
    public PlayerDao() throws SQLException {
        super();
    }
    
    /**
     * Get the rowid for given name or insert if not found.
     * Players are identified by unique names.
     * @param name Player.name to search for or insert.
     * @return Player.rowid
     * @throws SQLException Thrown if query fails.
     */
    public int getIdOrCreate(String name) throws SQLException {
        // Insert new row if name not found.
        if (findByName(name) == null) {
            save(name);
        }

        return findByName(name);
    }

    /**
     * Find a player by name.
     * @param name Name to search for.
     * @return Player.rowid or null if not found.
     * @throws SQLException Thrown if query fails.
     */
    public Integer findByName(String name) throws SQLException {
        String sql = "SELECT rowid FROM Player WHERE name = ?;";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return null;
        }
    }

    /**
     * Insert new row with given name.
     * @param name Name to save.
     * @throws SQLException Thrown if insert statement fails.
     */
    public void save(String name) throws SQLException {
        String sql = "INSERT INTO Player (name) VALUES (?);";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.execute();
    }
}
