package cs3035unb.cs3035examplecode.CustomWidgetExample;

import cs3035unb.cs3035examplecode.Utility.ColorUtility;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private Text text;

    public void start(Stage primaryStage)
    {
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.setHgap(20);
        root.setVgap(10);

        ColorFlipWidget fw1 = new ColorFlipWidget(50, 50);
        ColorFlipWidget fw2 = new ColorFlipWidget(50, 50);
        fw1.setCounter(20);

        root.add(fw1, 0, 0);
        root.add(fw2, 1, 0);

        text = new Text();
        updateColorName(fw1.getCounter());
        root.add(text, 0, 1, 2, 1);
        root.setHalignment(text, HPos.CENTER);

        // listen for changes on the widget models
        fw1.counter().addListener(this::counterUpdateListener);
        fw2.counter().addListener(this::counterUpdateListener);

        // handle mouseover events
        fw1.setOnMouseEntered(this::updateColorName);
        fw2.setOnMouseEntered(this::updateColorName);

        // create button to explicitly set the value of the right widget
        Button button = new Button("Set to index 50");
        button.setOnAction(e->fw2.setCounter(50));
        root.add(button, 0, 2, 2, 1);
        root.setHalignment(button, HPos.CENTER);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void counterUpdateListener(Observable observable, Object oldValue, Object newValue) {
        updateColorName((Integer) newValue);
    }

    private void updateColorName(MouseEvent event)
    {
        ColorFlipWidget widget = (ColorFlipWidget) event.getSource();
        updateColorName(widget.getCounter());
    }

    private void updateColorName(int colorValue)
    {
        List<String> colorNameList = ColorUtility.getColorNameList();
        text.setText(colorNameList.get(colorValue));
    }
}
