package fi.basse.shamery.ui;

import java.util.ArrayList;
import java.util.List;
import fi.basse.shamery.domain.Card;
import fi.basse.shamery.domain.Game;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class CardGrid extends GridPane {
    // Grid size is currently static. Use this to set the number of columns:
    private int cardsPerRow = 6;

    private Game game;
    private List<CardButton> cardBtns;

    /**
     * Create a GridPane populated with CardButtons.
     * @param board parent Board reference.
     */
    public CardGrid(Board board) {
        this.game = board.getGame();
        this.cardBtns = new ArrayList<>();
        setId("card-grid");

        // Add the cards to the board in rows.
        int x = 0;
        int y = 0;

        for (Card card : game.getDeck().getCards()) {
            CardButton button = new CardButton(card, board);
            cardBtns.add(button);
            this.add(button, x, y);

            // Next col and row indexes.
            if (x == cardsPerRow - 1) {
                x = 0;
                y++;
            } else {
                x++;
            }
        }

        // Expand the areas outside the cards to be clickable as well.
        setOnMouseClicked(onClick());
    }

    /**
     * Update all CardButtons in this grid to reflect current state.
     */
    public void update() {
        for (CardButton cardButton : cardBtns) {
            cardButton.update();
        }
    }

    private EventHandler<MouseEvent> onClick() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (game.getDeck().getOpenCards().size() == 2) {
                    game.hideOpenCards();
                }
                update();
            }
        };
    }
}
