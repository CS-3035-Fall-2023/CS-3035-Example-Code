package cs3035unb.cs3035examplecode.MultipleSceneSwitching;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DefaultScene extends Scene {

    private VBox root;
    private Button button;
    private Text text;

    public static Scene createDefaultScene()
    {
        VBox root = new VBox();
        Scene scene = new DefaultScene(root);

        return scene;
    }

    private DefaultScene(VBox root)
    {
        super(root);
        this.root = root;
        text = new Text("this is the Default Scene!");
        text.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        button = new Button("Switch scene");
        button.setOnAction((actionEvent)->{
            Main.view.switchScene();
        });

        root.getChildren().addAll(text, button);
    }
}
