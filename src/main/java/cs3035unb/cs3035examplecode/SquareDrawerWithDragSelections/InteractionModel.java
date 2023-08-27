package cs3035unb.cs3035examplecode.SquareDrawerWithDragSelections;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.shape.Rectangle;

public class InteractionModel {
	private SelectionGroup selectedSquares;
	private SimpleObjectProperty<Rectangle> selectRegion;
	
	public InteractionModel()
	{
		selectedSquares = new SelectionGroup();
		selectRegion = new SimpleObjectProperty<Rectangle>();
	}
	
	public SimpleObjectProperty<Rectangle> selectRegionProperty()
	{
		return selectRegion;
	}
	
	public SelectionGroup getSelectedSquares(){
		return selectedSquares;
	}
	
	public void startSelectRegion(double x, double y)
	{
		selectRegion.set(new Rectangle(x,y,0,0));
	}
	
	public void updateSelectRegion(double x, double y)
	{
		Rectangle selectRect = selectRegionProperty().getValue();
		selectRect.setWidth(x - selectRect.getX());
		selectRect.setHeight(y - selectRect.getY());
	}
}
