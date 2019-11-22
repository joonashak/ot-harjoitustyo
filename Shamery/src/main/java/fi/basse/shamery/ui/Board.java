package fi.basse.shamery.ui;

import fi.basse.shamery.domain.Card;
import fi.basse.shamery.domain.Game;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Board {
    Stage primaryStage;

    public Board(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene getScene() {
        Game game = new Game();
        VBox vbox = new VBox();

        for (Card card : game.getDeck().getCards()) {
            Label label = new Label("" + card.getId());
            vbox.getChildren().addAll(label);
        }

        return new Scene(vbox, 800, 600);
    }
}
