package fi.basse.shamery.db;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Dao implements Closeable {
    Connection conn;

    /**
     * Create new Database Access Object.
     * Connection is activated on constructor call and must be closed by calling
     * Dao.close() once done.
     * @throws SQLException Thrown on connection fail.
     */
    public Dao() throws SQLException {
        Database db = new Database();
        this.conn = db.getConnection();
    }
    
    @Override
    public void close() throws IOException {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
