package fi.basse.shamery;

import fi.basse.shamery.db.Database;
import fi.basse.shamery.ui.GameUi;

public class Main {
    /**
     * Run the application.
     * @param args N/A
     */
    public static void main(String[] args) {
        // Make sure database has been initialized.
        try {
            Database db = new Database();
            db.init();
        } catch (Exception e) {
            e.printStackTrace();
            // Database is only needed for saving scores, so don't exit on error.
        }

        GameUi.main(args);
    }
}
