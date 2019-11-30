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
        super.stop();
    }

    /**
     * Change active Scene of this GameUI.
     * @param scene Scene to be shown.
     */
    public void setScene(Scene scene) {
        primaryStage.setScene(scene);
    }

    /**
     * Launch this application.
     * @param args CLI arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
