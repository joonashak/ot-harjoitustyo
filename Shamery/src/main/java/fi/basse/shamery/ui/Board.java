package fi.basse.shamery.ui;

import java.util.ArrayList;
import java.util.List;

import fi.basse.shamery.domain.Card;
import fi.basse.shamery.domain.Game;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Board {
    private Stage primaryStage;
    private Game game;
    private List<CardButton> cardBtns;

    public Board(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.game = new Game();
        this.cardBtns = new ArrayList<>();
    }

    // TODO: Loop over the deck on each click (card or area) and update cards.
    public Scene getScene() {

        // Add the cards to the board in rows of six.
        int x = 0;
        int y = 0;
        int cardsPerRow = 6;
        GridPane cardGrid = new GridPane();

        for (Card card : game.getDeck().getCards()) {
            CardButton button = new CardButton(card, this);
            cardBtns.add(button);
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

        cardGrid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (game.getDeck().getOpenCards().size() == 2) {
                    game.hideOpenCards();
                }
                update();
            }
        });

        return new Scene(cardGrid, 800, 600);
    }

    public void update() {
        for (CardButton cb : cardBtns) {
            cb.update();
        }
    }

    public void reveal(Card card) {
        if (game.getDeck().getOpenCards().size() == 2) {
            game.hideOpenCards();
        } else {
            game.reveal(card);
        }

        update();
    }
}
