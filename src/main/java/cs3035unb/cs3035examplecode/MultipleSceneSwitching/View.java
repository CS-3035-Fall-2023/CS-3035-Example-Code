package cs3035unb.cs3035examplecode.MultipleSceneSwitching;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class View {

    private Scene defaultScene;
    private Scene anotherScene;
    private Stage primaryStage;

    private Scene currentScene;

    public View(Scene defaultScene, Scene anotherScene, Stage primaryStage)
    {
        this.defaultScene = defaultScene;
        this.anotherScene = anotherScene;
        currentScene = defaultScene;
        this.primaryStage = primaryStage;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void switchScene()
    {
        if (currentScene == defaultScene)
            currentScene = anotherScene;
        else
            currentScene = defaultScene;

        primaryStage.setScene(currentScene);
    }
}
