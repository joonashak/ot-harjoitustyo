package fi.basse.shamery.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameUi extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainMenu mainMenu = new MainMenu(primaryStage);
        primaryStage.setScene(mainMenu.getScene());
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("App Closing...");
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
