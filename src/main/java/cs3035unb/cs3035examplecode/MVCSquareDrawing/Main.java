package cs3035unb.cs3035examplecode.MVCSquareDrawing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This example is created to demonstrate Model-View-Controller in a simple example.
 * While this introduces a fair bit of code, this help keeps things organized as interfaces
 * start to get large.
 *
 * Here the Model, View and Controller objects are made as public "singletons" of the
 * Main class. That way they are easily accessible to the other classes.
 *
 * First the Model is created, as a place to represent the state of the applications Domain objects.
 *
 * Then the View is created and draws/displays the interface based on the model. The View subscribes
 * to be notified of changes in the Model.
 * Next, the Controller is created, and it listens to appropriate interface events through the View,
 * interprets those events and updates the Model in the appropriate way.
 *
 * @author scottb
 *
 */
public class Main extends Application {
	public static final int SQUARE_SIDE_LENGTH = 40;
	public static final Model model = new Model(SQUARE_SIDE_LENGTH);
	public static final View view = new View();
	public static final Controller controller = new Controller();

	@Override
	public void start(Stage primaryStage) {
		try {
			model.addSquare(100, 100);	//populate the model with an initial square

			Scene scene = new Scene(view,400,400);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
