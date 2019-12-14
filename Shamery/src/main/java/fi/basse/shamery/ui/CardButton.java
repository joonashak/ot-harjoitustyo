package fi.basse.shamery.ui;

import java.io.InputStream;
import fi.basse.shamery.domain.Card;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * Represents a Card on the Board.
 * This extension of Button includes event logic and communicates
 * reveal events to the game.
 * @author Joonas Häkkinen
 */
public class CardButton extends Rectangle {
    private Card card;
    private Board board;
    private ImagePattern frontside;
    private Color backside;

    /**
     * Generate a new CardButton.
     * @param card the Card object this element represents in the UI.
     * @param board the parent Board of this object.
     */
    public CardButton(Card card, Board board) {
        this.card = card;
        this.board = board;

        // Dimensions.
        setHeight(200);
        setWidth(150);
        setArcHeight(20);
        setArcWidth(20);
        
        // Card backside.
        this.backside = Color.BISQUE;

        // Preload card image.
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String path = String.format("card_icons/%s.png", card.getName());
        InputStream stream = loader.getResourceAsStream(path);
        this.frontside = new ImagePattern(
            new Image(stream, getWidth(), getHeight(), false, false));

        // Apply styles and action handling.
        setOnMouseClicked(onClick());
        getStyleClass().add("card-button");
        setFill(backside);
    }

    private EventHandler<MouseEvent> onClick() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                board.reveal(card);
                event.consume();
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
            setFill(frontside);
        } else {
            setFill(backside);
        }
    }
}
