package fi.basse.shamery.db;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;
import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.AppDirsFactory;

/**
 * Database configuration and utilities.
 */
public class Database {
    private String dbPath;

    /**
     * Initialize Database for use.
     */
    public Database() {
        // AppDirs provides cross-platform directories.
        AppDirs appDirs = AppDirsFactory.getInstance();
        String dirPath = appDirs.getUserDataDir("Shamery", "1.0.0", "Basse");

        // Make sure data dir exists.
        new File(dirPath).mkdirs();

        // Add correct db filename and save.
        String dbFile = System.getenv("SHAMERY_TEST") == null ? "main.db" : "test.db";
        this.dbPath = dirPath.concat(File.separator).concat(dbFile);
    }

    /**
     * Get Connection object.
     * @return Active database connection. Must be closed after use.
     * @throws SQLException thrown if connection fails.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(String.format("jdbc:sqlite:%s", dbPath));
    }

    /**
     * Run SQLite initialization script.
     * This is supposed to be run once on application start and thus the
     * initializing SQL statements should be safe to be run against a database
     * in use.
     * @throws Exception thrown for both SQL and resource errors.
     */
    public void init() throws Exception {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("db_init.sql");

        // Run SQL statements in stream with mybatis.
        try (Connection conn = getConnection()) {
            ScriptRunner sr = new ScriptRunner(conn);
            // Silence console output (prints out SQL statements).
            sr.setLogWriter(null);
            // JDBC:SQLite does not implement escape processing:
            sr.setEscapeProcessing(false);
            sr.runScript(new InputStreamReader(stream));
        } catch (Exception e) {
            throw e;
        }
    }
}
