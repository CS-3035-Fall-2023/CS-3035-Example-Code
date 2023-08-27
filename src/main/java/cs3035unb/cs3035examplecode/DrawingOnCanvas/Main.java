package cs3035unb.cs3035examplecode.DrawingOnCanvas;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage)
    {
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane, 400, 400, Color.LIGHTBLUE);
        Canvas canvas = new Canvas(400,400);
        stackPane.getChildren().add(canvas);

        GraphicsContext g = canvas.getGraphicsContext2D();

        canvas.setOnMouseClicked(new ClickHandler(g));

        g.setFill(Color.BLUE);
        g.fillRect(0, 175, scene.getWidth(), scene.getHeight() - 175);  // ground

        g.setFill(Color.YELLOW);
        g.fillOval(-40, -40, 80, 80);  // sun

        int mid = (int) scene.getWidth() / 2;
        int top = (int) scene.getHeight() / 3;

        g.setFill(Color.WHITE);
        g.fillOval(mid-20, top, 40, 40);      // head
        g.fillOval(mid-35, top+35, 70, 50);   // upper torso
        g.fillOval (mid-50, top+80, 100, 60);  // lower torso

        g.setFill(Color.BLACK);
        g.fillOval(mid-10, top+10, 5, 5);   // left eye
        g.fillOval(mid+5, top+10, 5, 5);    // right eye

        g.fillArc(mid-10, top+20, 20, 10, 190, 160, ArcType.OPEN);   // smile

        g.strokeLine(mid-25, top+60, mid-50, top+40);  // left arm
        g.strokeLine(mid+25, top+60, mid+55, top+60);  // right arm

        g.strokeLine(mid-20, top+5, mid+20, top+5);  // brim of hat
        g.fillRect (mid-15, top-20, 30, 25);        // top of hat*/

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class ClickHandler implements EventHandler<MouseEvent> {
        GraphicsContext g;

        public ClickHandler(GraphicsContext g)
        {
            this.g = g;
        }

        @Override
        public void handle(MouseEvent event) {
            g.save();
            g.setFill(Color.BLACK);
            g.fillOval(event.getX() - 5, event.getY() - 5, 10, 10);
            g.restore();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
