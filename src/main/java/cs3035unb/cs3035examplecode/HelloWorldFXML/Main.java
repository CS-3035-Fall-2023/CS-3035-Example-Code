package cs3035unb.cs3035examplecode.HelloWorldFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * HelloWorld in JavaFX with FXML.
 * @author scottbateman
 */

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("HelloWorld.fxml"));

        Scene scene = new Scene(root, 300, 120);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	public static void main(String[] args) {
		launch(args);
	}


}
