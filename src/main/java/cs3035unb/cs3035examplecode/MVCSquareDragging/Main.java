package cs3035unb.cs3035examplecode.MVCSquareDragging;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Simple example to demonstrate drawing on a canvas in an MVC application using an MVC
 */

public class Main extends Application {
    public static Model model = new Model();
    public static GraphView graphView = new GraphView();
    public static GraphViewController graphViewController = new GraphViewController();


    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            BorderPane root = new BorderPane();
            graphView.setPrefSize(500,500);
            root.setCenter(graphView);
            Scene scene = new Scene(root,500,500);

            model.squareListProperty().add(new Square(10, 10, 40, 40));

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
