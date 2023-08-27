package cs3035unb.cs3035examplecode.SquareDrawerWithDragSelections;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.BoundingBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SelectionGroup {
	private SimpleListProperty<Rectangle> items;
	private BoundingBox bounds = new BoundingBox(0, 0, 0, 0);
	
	public SelectionGroup()
	{
		ArrayList<Rectangle> list = new ArrayList<Rectangle>();
		ObservableList<Rectangle> observableList = (ObservableList<Rectangle>) FXCollections.observableArrayList(list);
		items = new SimpleListProperty<Rectangle>(observableList);
		
		itemsProperty().addListener(new ListChangeListener<Rectangle>() {
			@Override
			public void onChanged(Change<? extends Rectangle> c) {
				calculateBoundingBox();
			}
		});
	}
	
	public BoundingBox getBoundingBox()
	{
		calculateBoundingBox();
		return bounds;
	}
	
	public SimpleListProperty<Rectangle> itemsProperty()
	{
		return items;
	}
	
	private void calculateBoundingBox()
	{
		double 	minX = Double.MAX_VALUE, minY = Double.MAX_VALUE, 
				maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE; 
		for (Rectangle r : itemsProperty())
		{
			if (r.getX() < minX)
				minX = r.getX();
			if (r.getY() < minY)
				minY = r.getY();
			if (r.getX() + r.getWidth() > maxX)
				maxX = r.getX() + r.getWidth();
			if (r.getY() + r.getHeight() > maxY)
				maxY = r.getY() + r.getHeight();
		}
		bounds = new BoundingBox(minX, minY, maxX - minX, maxY - minY);
	}
}
