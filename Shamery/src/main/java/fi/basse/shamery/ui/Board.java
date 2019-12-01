package fi.basse.shamery.ui;

import java.util.ArrayList;
import java.util.List;

import fi.basse.shamery.domain.Card;
import fi.basse.shamery.domain.Game;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Board {
    private Game game;
    private List<CardButton> cardBtns;

    /**
     * Playing area and main part of the UI.
     */
    public Board(GameUi gameUi) {
        this.game = gameUi.getGame();
        this.cardBtns = new ArrayList<>();
    }

    /**
     * Scene implementing the playing area.
     * @return Scene
     */
    public Scene getScene() {

        GridPane cardGrid = createCardGrid();

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

    /**
     * Command all CardButtons to update.
     */
    public void update() {
        for (CardButton cb : cardBtns) {
            cb.update();
        }
    }

    /**
     * Attempt to reveal a Card on this Board.
     * If two cards are currently open, this method hides all cards and updates
     * instead of revealing another card. This is done to prevent accidentally
     * revealing cards if the user intends to just click to hide an unmatched pair.
     * @param card Card to be revealed.
     */
    public void reveal(Card card) {
        if (game.getDeck().getOpenCards().size() == 2) {
            game.hideOpenCards();
        } else {
            game.reveal(card);
        }

        update();
    }

    /**
     * Create a grid and populate it with the deck's cards.
     * @return GridPane
     */
    private GridPane createCardGrid() {
        GridPane cardGrid = new GridPane();

        // Add the cards to the board in rows of six.
        int cardsPerRow = 6;
        int x = 0;
        int y = 0;

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

        return cardGrid;
    }
}
