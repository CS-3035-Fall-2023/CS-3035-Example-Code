package cs3035unb.cs3035examplecode.MVCSquareDragging;

import javafx.collections.ListChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class GraphView extends Pane {

    private Canvas canvas;

    public GraphView() {
        canvas = new Canvas();
        canvas.setLayoutX(0);
        canvas.setWidth(0);
        this.getChildren().add(canvas);

        //if any change happens to the model (the list of squares) redraw.
        Main.model.squareListProperty().addListener(new ListChangeListener<Square>() {
            @Override
            public void onChanged(Change<? extends Square> s) {
                requestLayout();
            }
        });
    }

    //draw all squares.
    private void drawSquares() {
        GraphicsContext g = canvas.getGraphicsContext2D();

        g.clearRect(0,0, canvas.getWidth(), canvas.getHeight());

        for (Square s : Main.model.squareListProperty().get()) {
            double height = s.endY.get() - s.startY.get();
            double width = s.endX.get() - s.startX.get();

            g.strokeRect(s.startX.get(), s.startY.get(), width, height);
        }
    }

    @Override
    public void layoutChildren() {
        canvas.setWidth(this.getWidth());
        canvas.setHeight(this.getHeight());

        drawSquares();
    }

}
