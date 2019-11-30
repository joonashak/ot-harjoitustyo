package fi.basse.shamery.ui;

import fi.basse.shamery.domain.Card;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Represents a Card on the Board.
 * This extension of Button includes event logic and communicates
 * reveal events to the game.
 * @author Joonas Häkkinen
 */
public class CardButton extends Button {
    private Card card;
    private Board board;

    /**
     * Generate a new CardButton.
     * @param card the Card object this element represents in the UI.
     * @param board the parent Board of this object.
     */
    public CardButton(Card card, Board board) {
        this.card = card;
        this.board = board;
        setOnAction(onClick());
    }

    private EventHandler<ActionEvent> onClick() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                board.reveal(card);
            }
        };
    }

    /**
     * Update this button to match current Card state.
     */
    public void update() {
        if (card.isRemoved()) {
            setVisible(false);
        } else if (card.isRevealed()) {
            setText("" + card.getCode());
        } else {
            setText("");
        }
    }
}
