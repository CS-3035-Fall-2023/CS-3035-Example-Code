package cs3035unb.cs3035examplecode.MVCSquareDrawing;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Model {
	private SimpleListProperty<Square> squareListProperty;

	private int squareSideLength;

	public Model(int squareSideLength) {
		ArrayList<Square> list = new ArrayList<Square>();
		ObservableList<Square> observableList = (ObservableList<Square>) FXCollections.observableArrayList(list);
		squareListProperty = new SimpleListProperty<Square>(observableList);

		this.squareSideLength = squareSideLength;
	}

	public SimpleListProperty<Square> squareListProperty(){
		return squareListProperty;
	}

	public int getSquareSideLegnth() {return squareSideLength;}

	public void addSquare(int x, int y)
	{
		Square newSquare = new Square(x, y, squareSideLength);
		squareListProperty.add(newSquare);
	}

	public void deleteSquareAt(int x, int y)
	{
		Square delSquare = getSquareAt(x, y);
		squareListProperty.remove(delSquare);
	}

	private Square getSquareAt(int x, int y)
	{
		Square square = null;

		for (Square s : squareListProperty)
		{
			if (s.containsPoint(x, y))
			{
				square = s;
			}
		}

		return square;
	}
}
