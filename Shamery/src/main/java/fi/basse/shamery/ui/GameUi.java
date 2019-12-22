package fi.basse.shamery.ui;

import fi.basse.shamery.domain.Game;
import fi.basse.shamery.scoring.Scoring;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameUi extends Application {
    private Stage primaryStage;
    private Game game;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Shamery");
        this.primaryStage = primaryStage;
        showMenu();
        this.primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        primaryStage.close();
    }

    /**
     * GameUi.stop() that does not throw.
     */
    public void cleanStop() {
        try {
            this.stop();
        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(1);
        }
    }

    /**
     * Change active Scene of this GameUI.
     * @param scene Scene to be shown.
     */
    public void setScene(Scene scene) {
        scene.getStylesheets().addAll("css/fonts.css", "css/main.css");
        primaryStage.setScene(scene);
    }

    /**
     * Show game set-up view.
     * @param noPlayers number of players (1 or 2).
     */
    public void setUpGame(int noPlayers) {
        this.game = new Game();
        GameSetup setup = new GameSetup(this, noPlayers);
        setScene(setup.getScene());
    }

    /**
     * Start a new game.
     * @param scoring Scoring instance to be used.
     */
    public void newGame(Scoring scoring) {
        game.setScoring(scoring);
        Board board = new Board(this);
        setScene(board.getScene());
    }

    /**
     * Show main menu.
     */
    public void showMenu() {
        MainMenu mainMenu = new MainMenu(this);
        setScene(mainMenu.getScene());
    }

    /**
     * Show highscores view.
     */
    public void showHighscores() {
        Highscores hs = new Highscores(this);
        setScene(hs.getScene());
    }

    /**
     * Launch this application.
     * @param args CLI arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Game getGame() {
        return this.game;
    }
}
