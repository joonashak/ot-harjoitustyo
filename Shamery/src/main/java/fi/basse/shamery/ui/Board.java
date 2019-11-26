package fi.basse.shamery.ui;

import fi.basse.shamery.domain.Card;
import fi.basse.shamery.domain.Game;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Board {
    private Stage primaryStage;

    public Board(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene getScene() {
        Game game = new Game();

        // Add the cards to the board in rows of six.
        int x = 0;
        int y = 0;
        int cardsPerRow = 6;
        GridPane cardGrid = new GridPane();

        for (Card card : game.getDeck().getCards()) {
            Button button = new CardButton(card);
            button.setMinHeight(100);
            button.setMinWidth(60);
            cardGrid.add(button, x, y);

            // Next col and row indexes.
            if (x == cardsPerRow - 1) {
                x = 0;
                y++;
            } else {
                x++;
            }
        }

        return new Scene(cardGrid, 800, 600);
    }
}
