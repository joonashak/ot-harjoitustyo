package fi.basse.shamery.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import fi.basse.shamery.db.ScoreDao;
import fi.basse.shamery.db.ScoreTypeDao;
import fi.basse.shamery.domain.Player;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Highscores {
    private GameUi gameUi;

    /**
     * Highscores view.
     * @param gameUi parent GameUi.
     */
    public Highscores(GameUi gameUi) {
        this.gameUi = gameUi;
    }
    
    /**
     * Scene implementing this view.
     * @return Scene
     */
    public Scene getScene() {
        Label title = new Label("Highscores");
        title.getStyleClass().add("title");

        VBox vbox = new VBox(title, highscoreTables());
        return new Scene(vbox);
    }

    private Node highscoreTables() {
        HBox container = new HBox();
        container.setId("highscore-container");

        fetchScoreTypes().forEach((scoreTypeId, scoreTypeName) -> {
            Label tableTitle = new Label(scoreTypeName);
            List<Player> highscores = fetchHighscores(scoreTypeId);
            GridPane table = new GridPane();

            for (int i = 0; i < 10; i++) {
                Label n = new Label(String.format("%o.", i + 1));
                table.add(n, 1, i + 1);

                if (i < highscores.size()) {
                    Player player = highscores.get(i);
                    Label name = new Label(player.getName());
                    Label score = new Label(String.format("%o", player.getScore()));
                    table.add(name, 2, i + 1);
                    table.add(score, 3, i + 1);
                }
            }

            VBox vbox = new VBox(tableTitle, table);
            container.getChildren().add(vbox);
        });

        return container;
    }

    private List<Player> fetchHighscores(int scoreTypeId) {
        try (ScoreDao dao = new ScoreDao()) {
            return dao.getHighscoresByType(scoreTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("*** ERROR - Could not fetch highscores from database.");
            return new ArrayList<>();
        }
    }

    private HashMap<Integer, String> fetchScoreTypes() {
        try (ScoreTypeDao dao = new ScoreTypeDao()) {
            return dao.findAll();
        } catch (Exception e) {
            System.out.println("*** ERROR - Could not getch score types from database.");
            return new HashMap<>();
        }
    }
}
