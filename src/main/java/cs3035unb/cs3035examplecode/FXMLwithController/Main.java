package cs3035unb.cs3035examplecode.FXMLwithController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Simple Example demonstrating connecting FXML with a Controller class,
 * to handle button events.
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try{
            System.out.println(getClass().getResource("HelloWorld.fxml"));
            root = FXMLLoader.load(getClass().getResource("layout.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e)
        {
            System.err.println("cannot find fxml file");
        }
    }
}
