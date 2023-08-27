package cs3035unb.cs3035examplecode.MVCSquareDrawing;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Controller {

	public Controller() {
		Main.view.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				if (e.getButton() == MouseButton.PRIMARY)
				{
					//move the click point so that the square is centered around the mouse
					int x = (int) e.getX() - Main.model.getSquareSideLegnth()/2;
					int y = (int) e.getY() - Main.model.getSquareSideLegnth()/2;

					Main.model.addSquare(x, y);
				}
				else if (e.getButton() == MouseButton.SECONDARY)
				{
					//delete the highest square that intersects with this point
					Main.model.deleteSquareAt((int) e.getX(), (int) e.getY());
				}
			}
		});
	}

}
