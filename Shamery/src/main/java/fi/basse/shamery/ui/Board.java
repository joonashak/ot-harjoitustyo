package fi.basse.shamery.ui;

import fi.basse.shamery.domain.Card;
import fi.basse.shamery.domain.Game;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class Board {
    private Game game;
    private GameUi gameUi;
    private CardGrid cardGrid;
    private Scoreboard scoreboard;

    /**
     * Playing area and main part of the UI.
     * @param gameUi Host GameUI instance.
     */
    public Board(GameUi gameUi) {
        this.gameUi = gameUi;
        this.game = gameUi.getGame();
    }

    /**
     * Scene implementing the playing area.
     * @return Scene
     */
    public Scene getScene() {
        this.cardGrid = new CardGrid(this);
        this.scoreboard = new Scoreboard(game);
        VBox vbox = new VBox(cardGrid, scoreboard.getContent());

        return new Scene(vbox);
    }

    /**
     * Update this Board to reflect Game's current state.
     * Should be called after updates to game state are complete.
     * If update results in end of game, the scene is switched accordingly.
     */
    public void update() {
        cardGrid.update();
        scoreboard.update();

        if (game.isOver()) {
            EndOfGame eog = new EndOfGame(gameUi);
            gameUi.setScene(eog.getScene());
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

    public Game getGame() {
        return game;
    }
}
