package cs3035unb.cs3035examplecode.BindingExample;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FlowPane root = new FlowPane();
        Text text = new Text("I will update");
        TextField field = new TextField("1. update me");
        TextField field2 = new TextField("2. update me");


        root.getChildren().add(text);
        root.getChildren().add(field);
        root.getChildren().add(field2);


        StringBinding stringBinding = new StringBinding() {
            {
                super.bind(field.textProperty(), field2.textProperty());
            }
            @Override
            protected String computeValue() {
                return field.getText() + " " + field2.getText();
            }
        };

        text.textProperty().bind(stringBinding);

        Scene scene = new Scene(root);
        root.setPrefHeight(200);
        root.setPrefWidth(200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
