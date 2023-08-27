package cs3035unb.cs3035examplecode.ModelPropertyChange;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The main purpose of this example is to demonstrate adding listeners to models, so that when
 * a widget's model is updated you can take action.
 *
 * However, doing a repeated Task that updates a model is also demonstrated.
 *
 * The example is of a clock that has a message counting the number of zeros in the currently display time.
 *
 * @author scottbateman
 */

public class Main extends Application {
    private Label time;
    private Label message;
    private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Property Change Event");

        // the label for displaying the time, when it is updated, update the message
        // which counts the number of zeros in the time
        time = new Label(dateFormat.format(new Date()));
        time.setFont(Font.font(24));
        time.textProperty().addListener((observable)->updateMessage());

        //label for message and display first message
        message = new Label();
        message.setFont(Font.font(24));
        updateMessage();

        // update the time every second indefinitely
        Timeline schedule = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> updateTime()));
        schedule.setCycleCount(Animation.INDEFINITE);
        schedule.play();

        VBox root = new VBox(20, time, message);
        root.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    private void updateMessage() {
        long count = time.getText().chars().filter(ch -> ch == '0').count();
        message.setText("Zeros in current time: " + count);
    }

    private void updateTime() {
        time.setText(dateFormat.format(new Date()));
    }
    public static void main(String[] args) {
        launch(args);
    }
}