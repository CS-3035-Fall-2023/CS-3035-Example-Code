package cs3035unb.cs3035examplecode.MVCSquareDrawing;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class View extends Pane {
	public static final Color FILL_COLOR = Color.GREEN;

	public View() {
		Main.model.squareListProperty().addListener(new ListChangeListener<Square>() {
			@Override
			public void onChanged(Change<? extends Square> c) {
				draw();
			}
		});
	}

	/**
	 * Overriding this method from Pane will allow draw to be called whenever necessary.
	 * In this example, it will be first called when the View is displayed.
	 */
	@Override
	public void layoutChildren()
	{
		draw();
	}
	private void draw() {
		this.getChildren().clear();
		for (Square s : Main.model.squareListProperty())
		{
			Rectangle r = new Rectangle(
											s.getPosition().getX(),
											s.getPosition().getY(),
											s.getSideLength(),
											s.getSideLength()
										);
			r.setFill(FILL_COLOR);
			this.getChildren().add(r);
		}
	}


}
