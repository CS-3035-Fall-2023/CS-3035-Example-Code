package cs3035unb.cs3035examplecode.ClippingExample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Demonstrate creating a clipping region and drawing to the clipping region.
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Canvas c = new Canvas();
        c.setWidth(400);
        c.setHeight(400);

        root.setCenter(c);
        Scene scene = new Scene(root, 400, 400);

        clipTest(c.getGraphicsContext2D());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void clipTest(GraphicsContext graphicsContext2D)
    {
        graphicsContext2D.save();

        graphicsContext2D.setFill(Color.DARKGREEN);
        graphicsContext2D.fillRect(10, 10, 300, 300);

        // Create a clipping path for a rectangular area and a bezier curve
        graphicsContext2D.beginPath();
        graphicsContext2D.rect(20, 20, 60, 60);
        graphicsContext2D.bezierCurveTo(0,0, 100,200, 400,400);
        graphicsContext2D.closePath();

        //clip the areas
        graphicsContext2D.clip();

        // Draw red rectangle after clip() only the parts inside clip should be red
        graphicsContext2D.setFill(Color.RED);
        graphicsContext2D.fillRect(0, 0, 400, 400);

        //draw a random shape and it will only fill up part of the path that it intersects with
        graphicsContext2D.setFill(Color.PAPAYAWHIP);
        graphicsContext2D.fillRoundRect(300, 300, 100, 100, 10, 10);

        graphicsContext2D.restore();
    }
}
