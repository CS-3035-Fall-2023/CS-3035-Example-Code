package cs3035unb.cs3035examplecode.LoginExample;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * Demonstrates a super simple login interface.
 *
 * Based on the example from https://docs.oracle.com/javafx/2/get_started/form.htm
 * There is good description of what is happening there.
 *
 * @author scottbateman
 *
 */
public class Main extends Application {
    //super secret credentials
    private static String userName = "user";
    private static String password = "secret";

    @Override
    public void start(Stage primaryStage) {

        GridPane root = new GridPane();
        Scene scene = new Scene(root,400,400);

        //align content in the grid to the center
        root.setAlignment(Pos.CENTER);

        //put 10 pixel gap around everything
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));

        Text headerText = new Text("Awesome Login!");
        headerText.setFont(new Font("Tahoma",40));

        Text resultText = new Text();

        Label userLabel = new Label("Username:");
        Label passLabel = new Label("Password:");

        TextField userField = new TextField("enter your username");
        PasswordField passField = new PasswordField();

        Button submit = new Button("login");

        //This code is long enough that it would probably be better done with a separate class
        submit.setOnAction(new EventHandler<ActionEvent>()
                           {
                               @Override
                               public void handle(ActionEvent arg0) {
                                   boolean loggedIn = false;
                                   if (userField.getText().equals(userName))
                                   {
                                       if (passField.getText().equals(password))
                                       {
                                           loggedIn = true;
                                       }
                                   }

                                   if (loggedIn)
                                   {
                                       resultText.setText("Logged In!");
                                       resultText.setFill(Color.FORESTGREEN);
                                   }
                                   else
                                   {
                                       resultText.setText("Your username or password is incorrect.");
                                       resultText.setFill(Color.FIREBRICK);
                                   }
                               }
                           }
        );

        Button cancelButton = new Button("cancel");

        //the cancel button uses an anonymous method as an action event handler
        cancelButton.setOnAction((event)->{
            Platform.exit();
        });

        //layout elements
        root.add(headerText, 0, 0, 2, 1);
        root.add(userLabel, 0, 1);
        root.add(passLabel, 0, 2);
        root.add(userField, 1, 1);
        root.add(passField, 1, 2);

        HBox hbSubmit = new HBox(10);		//lays out stuff horizontally with 10 pixels in between
        hbSubmit.setAlignment(Pos.CENTER_RIGHT);	//vertical center, horiz. right
        hbSubmit.getChildren().add(submit);
        hbSubmit.getChildren().add(cancelButton);
        root.add(hbSubmit, 1, 3);

        root.add(resultText, 0, 4, 2, 1);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}