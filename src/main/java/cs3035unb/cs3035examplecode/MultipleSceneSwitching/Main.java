package cs3035unb.cs3035examplecode.MultipleSceneSwitching;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static View view;

    @Override
    public void start(Stage primaryStage) throws Exception {
        view = new View(DefaultScene.createDefaultScene(), AnotherScene.createAnotherScene(), primaryStage);
        primaryStage.setScene(view.getCurrentScene());
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);
        primaryStage.show();
    }
}
