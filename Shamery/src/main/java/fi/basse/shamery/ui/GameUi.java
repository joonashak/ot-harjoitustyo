package fi.basse.shamery.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameUi extends Application {
    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        MainMenu mainMenu = new MainMenu(this);
        setScene(mainMenu.getScene());
        this.primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("App Closing...");
        primaryStage.close();
    }

    /**
     * Change active Scene of this GameUI.
     * @param scene Scene to be shown.
     */
    public void setScene(Scene scene) {
        primaryStage.setScene(scene);
    }

    /**
     * Show game set-up view.
     * @param noPlayers number of players (1 or 2).
     */
    public void setUpGame(int noPlayers) {
        GameSetup setup = new GameSetup(this, noPlayers);
        setScene(setup.getScene());
    }

    /**
     * Start a new game.
     */
    public void newGame() {
        Board board = new Board();
        setScene(board.getScene());
    }

    /**
     * Launch this application.
     * @param args CLI arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
