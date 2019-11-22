package fi.basse.shamery.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameUi extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> System.out.println("new game pressed"));

        Button exitButton = new Button("Close");
        exitButton.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(newGameButton, exitButton);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
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
