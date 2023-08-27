package cs3035unb.cs3035examplecode.CustomWidgetExample;

/**
 * This widget is designed to demonstrate how a standalone widget can be created.
 * Clicking the widget changes its color. The color is modeled by a number property,
 * which acts as an index into the list of colors.
 *
 * @author Scott Bateman
 */

import cs3035unb.cs3035examplecode.Utility.ColorUtility;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ColorFlipWidget extends StackPane {
    private Canvas canvas;
    private SimpleIntegerProperty counter;

    /**
     * Create a ColorFlipWidget with the specified width and height.
     * @param width
     * @param height
     */
    public ColorFlipWidget(int width, int height)
    {
        //create properties and initialize color
        canvas = new Canvas(width, height);
        this.getChildren().add(canvas);
        counter = new SimpleIntegerProperty();
        counter.set(0);
        updateColor();

        //handle mouse clicks
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                counter.set(counter.get() + 1);
                //updateColor();
            }
        });

        //when change happens to the widget make sure it is updated
        counter.addListener((observable, oldValue, newValue) -> {
            updateColor();
        });
    }

    /**
     * Set the counter property for this widget directly. This will
     * have a side effect of changing the color.
     *
     * @param count
     */
    public void setCounter(int count)
    {
        counter.set(count);
        //updateColor();
    }

    /**
     * Returns the current value of the counter property.
     *
     * @return the value of the counter property/
     */
    public int getCounter()
    {
        return counter.get();
    }

    /**
     * Returns the counter property of this object.
     *
     * @return The counter property of this object.
     */
    public IntegerProperty counter()
    {
        return counter;
    }

    /**
     * Update the color based on the current value of the Color property.
     */
    private void updateColor()
    {
        Color c = ColorUtility.getColorList().get(getCounter());
        canvas.getGraphicsContext2D().setFill(c);
        canvas.getGraphicsContext2D().fillRect(0,0, canvas.getWidth(), canvas.getHeight());
    }
}
