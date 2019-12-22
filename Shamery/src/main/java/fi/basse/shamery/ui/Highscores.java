package fi.basse.shamery.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import fi.basse.shamery.db.ScoreDao;
import fi.basse.shamery.db.ScoreTypeDao;
import fi.basse.shamery.domain.Player;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

        Button backButton = new Button("BACK TO MENU");
        backButton.getStyleClass().add("button");
        backButton.setOnAction(e -> gameUi.showMenu());

        VBox vbox = new VBox(title, highscoreTables(), backButton);
        vbox.setId("highscore-view");
        return new Scene(vbox);
    }

    private Node highscoreTables() {
        HBox container = new HBox();
        container.setId("highscore-container");

        fetchScoreTypes().forEach((scoreTypeId, scoreTypeName) -> {
            Label tableTitle = new Label(scoreTypeName);
            tableTitle.getStyleClass().add("hs-table-title");
            List<Player> highscores = fetchHighscores(scoreTypeId);
            GridPane table = new GridPane();
            table.getStyleClass().add("highscore-table");

            for (int i = 0; i < 10; i++) {
                highscoreRow(i, table, highscores);
            }

            VBox vbox = new VBox(tableTitle, table);
            vbox.getStyleClass().add("highscore-table-container");
            container.getChildren().add(vbox);
        });

        return container;
    }

    private void highscoreRow(int i, GridPane table, List<Player> highscores) {
        Label n = new Label(String.format("%s.", i + 1));
        n.getStyleClass().add("ordinal");
        table.add(n, 1, i + 1);

        if (i < highscores.size()) {
            Player player = highscores.get(i);
            Label name = new Label(player.getName());
            name.getStyleClass().add("hs-name");
            Label score = new Label(String.format("%o", player.getScore()));
            score.getStyleClass().add("hs-score");
            table.add(name, 2, i + 1);
            table.add(score, 3, i + 1);
        }
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
