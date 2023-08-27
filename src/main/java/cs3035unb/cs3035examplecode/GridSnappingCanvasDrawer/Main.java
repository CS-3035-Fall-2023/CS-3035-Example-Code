package cs3035unb.cs3035examplecode.GridSnappingCanvasDrawer;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Main extends Application {
    private final int   WIDTH = 600,
            HEIGHT = 600,
            CELL_SIZE = 40,
            SPOT_SIZE = 20;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, new GridSnapEventHandler(canvas.getGraphicsContext2D()));
        BorderPane root = new BorderPane();
        root.setCenter(canvas);

        drawGrid(canvas.getGraphicsContext2D());

        Scene scene = new Scene(root,WIDTH,HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class GridSnapEventHandler implements EventHandler<MouseEvent> {
        private GraphicsContext g;

        public GridSnapEventHandler(GraphicsContext g) {
            this.g = g;
        }

        @Override
        public void handle(MouseEvent e) {

            //erase the canvas
            g.clearRect(0, 0, WIDTH, HEIGHT);

            //draw the grid
            drawGrid(g);

            //calculate the position of the spot
            int x = (int) Math.round(e.getX()/(CELL_SIZE*1.0))*CELL_SIZE;
            int y = (int) Math.round(e.getY()/(CELL_SIZE*1.0))*CELL_SIZE;

            //draw the spot
            g.save();
            g.setFill(Color.MEDIUMPURPLE);
            g.setStroke(Color.BLACK);
            g.fillOval(x - SPOT_SIZE/2, y - SPOT_SIZE/2, SPOT_SIZE, SPOT_SIZE);
            g.strokeOval(x - SPOT_SIZE/2, y - SPOT_SIZE/2, SPOT_SIZE, SPOT_SIZE);
            g.restore();
        }
    }
    //help to draw the grid
    private void drawGrid(GraphicsContext g)
    {
        g.save();
        g.setStroke(Color.BLACK);

        //draw vertical lines
        for (int x = CELL_SIZE; x < WIDTH; x+=CELL_SIZE)
            g.strokeLine(x, 0, x, HEIGHT);

        //draw horizontal lines
        for (int y = CELL_SIZE; y < HEIGHT; y+=CELL_SIZE)
            g.strokeLine(0, y, WIDTH, y);

        g.restore();
    }
}
