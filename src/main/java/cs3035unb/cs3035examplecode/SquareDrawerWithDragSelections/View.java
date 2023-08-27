package cs3035unb.cs3035examplecode.SquareDrawerWithDragSelections;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class View extends Pane {
	public static final Color FILL_COLOR = Color.GREEN;
	public static final Color SELECTED_FILL_COLOR = Color.BLUE;
	private static Group root;
	
	public View() {
		Main.model.squareListProperty().addListener(new ListChangeListener<Rectangle>() {
			@Override
			public void onChanged(Change<? extends Rectangle> c) {
				while (c.next())
                {
					for (Rectangle r : c.getRemoved())
						root.getChildren().remove(r);
					
					for (Rectangle r : c.getAddedSubList())
					{	
						r.setStroke(Color.BLACK);
						r.setFill(FILL_COLOR);
						root.getChildren().add(r);
					}
                }
			}
		});
		Main.iModel.getSelectedSquares().itemsProperty().addListener(new ListChangeListener<Rectangle>() {
			@Override
			public void onChanged(Change<? extends Rectangle> c) {
				deselectAll();
				for (Rectangle r : Main.iModel.getSelectedSquares().itemsProperty())
				{
					selectSquare(r);
				}
			}
		});
		
		Main.iModel.selectRegionProperty().addListener(new ChangeListener<Rectangle>() {
			@Override
			public void changed(ObservableValue<? extends Rectangle> observable, Rectangle oldValue,
					Rectangle newValue) {
				root.getChildren().remove(oldValue);
				
				if (newValue !=null)
				{
					root.getChildren().add(newValue);
					newValue.setFill(new Color(0,0,.5,.3));
					newValue.setStroke(new Color(0,0,.5,1));
				}
			}
		});
		
		root = new Group();
		getChildren().add(root);
	}	
	
	public void deselect(Rectangle r)
	{
		r.setFill(FILL_COLOR);
		r.setStrokeWidth(1);
	}
	
	public void deselectAll() {
		for (Rectangle s : Main.model.squareListProperty())
		{
			deselect(s);
		}
	}

	public void selectSquare(Rectangle node) {
		node.setFill(View.SELECTED_FILL_COLOR);
		node.setStrokeWidth(4);
	}
}
