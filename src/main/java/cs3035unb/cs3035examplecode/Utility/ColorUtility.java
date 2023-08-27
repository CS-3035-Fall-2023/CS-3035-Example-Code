package cs3035unb.cs3035examplecode.Utility;


/**
 Provides an exploreable list of predefined color names.
 Also example of accessing static property through
 reflection of class to get a list.

 @author scottb
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ColorUtility extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        ListView<HBox> lv = new ListView<>();

        HashMap<String, Color> colorMap = getColorsMap();

        for (String colorName: colorMap.keySet())
        {
            HBox hb = new HBox(5);
            hb.getChildren().add(new Rectangle(20,20,colorMap.get(colorName)));
            hb.getChildren().add(new Label(colorName));
            lv.getItems().add(hb);
        }

        root.getChildren().add(lv);

        Scene scene = new Scene(root,200,400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Returns a map of predefined Color objects indexed by name.
     * Adapted from https://www.javaworld.com/article/2074533/viewing-javafx-2-standard-colors.html
     *
     * @return a HashMap of the predefined Color objects indexed by name.
     */
    public static HashMap<String,Color> getColorsMap()
    {
        final Field[] fields = Color.class.getFields(); // only want public
        HashMap<String, Color> colors = new HashMap<>();

        for (final Field field : fields)
        {
            if (field.getType() == Color.class)
            {
                try
                {
                    final Color color = (Color) field.get(null);
                    final String colorName = field.getName();

                    colors.put(colorName, color);
                }
                catch (IllegalAccessException ignored){}
            }
        }
        return colors;
    }

    /**
     * Returns a list of predefined color names.
     *
     * @return The list of predefined color names.
     */
    public static List<String> getColorNameList()
    {
        return new ArrayList<String>(getColorsMap().keySet());
    }

    /**
     * Returns a list of predefined color objects.
     *
     * @return The list of predefined color objects.
     */
    public static List<Color> getColorList()
    {
        return new ArrayList<Color>(getColorsMap().values());
    }
}
