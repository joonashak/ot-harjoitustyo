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

    /**
     * Generate a new CardButton.
     * @param card the Card object this element represents in the UI.
     */
    public CardButton(Card card) {
        this.card = card;
        setOnAction(onClick());
    }

    private EventHandler<ActionEvent> onClick() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reveal();
            }
        };
    }

    /**
     * Reveal this card (turn face up).
     */
    public void reveal() {
        setText("" + card.getCode());
    }

    /**
     * Hide this card (turn face down).
     */
    public void hide() {
        setText("");
    }
}
