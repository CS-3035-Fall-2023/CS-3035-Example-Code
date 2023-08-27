package cs3035unb.cs3035examplecode.MVCSquareDragging;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Click to start creating a square, move the mouse to adjust size, click to stop creating.
 */
public class GraphViewController {

    private enum State{READY, CREATING_SQUARE};
    private State state;
    private Square currentSquare;
    private double origX = 0, origY = 0;

    public GraphViewController() {
        state = State.READY;

        Main.graphView.addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                //click to start the creation
                if (state == State.READY) {
                    if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                        currentSquare = Main.model.addSquare(event.getX(), event.getY());
                        state = State.CREATING_SQUARE;
                        origX = event.getX();
                        origY = event.getY();
                    }
                }

                else if (state == State.CREATING_SQUARE){

                    //adjust the model correctly based on the starting position
                    if (event.getEventType() == MouseEvent.MOUSE_MOVED) {

                        if (event.getX() < origX )
                        {
                            currentSquare.endX.set(origX);
                            currentSquare.startX.set(event.getX());
                        }
                        else {

                            currentSquare.endX.set(event.getX());
                            currentSquare.startX.set(origX);
                        }

                        if (event.getY() < origY )
                        {
                            currentSquare.startY.set(event.getY());
                            currentSquare.endY.set(origY);
                        }
                        else {
                            currentSquare.endY.set(event.getY());
                            currentSquare.startY.set(origY);
                        }

                    }

                    //second click ends the creation
                    if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                        state = State.READY;
                    }
                }
            }
        });
    }
}
