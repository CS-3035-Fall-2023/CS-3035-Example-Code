package cs3035unb.cs3035examplecode.CustomLayoutExample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MyLayoutPane root = new MyLayoutPane();
        root.setPrefSize(400,400);
        Scene scene = new Scene(root);

        Button b = new Button("Button 1");
        root.getChildren().add(b);

        Button b2 = new Button("Button 2");
        root.getChildren().add(b2);

        Button b3 = new Button("Button 3");
        root.getChildren().add(b3);

        Button b4 = new Button("Button 4");
        root.getChildren().add(b4);

        TextField textField = new TextField("test");
        root.getChildren().add(textField);

        primaryStage.setTitle("Custom Layout Pane Example");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
