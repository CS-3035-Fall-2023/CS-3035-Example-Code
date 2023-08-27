package cs3035unb.cs3035examplecode.MVCSquareDrawing;

/**
 * A domain object for a Square to be stored by the application Model.
 *
 */

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class Square {
	private int x, y, sideLength;

	public Square(int x, int y, int sideLength) {
		this.sideLength = sideLength;
		this.x = x;
		this.y = y;
	}

	public Point2D getPosition()
	{
		return new Point2D(x,y);
	}

	public int getSideLength()
	{
		return sideLength;
	}
	public boolean containsPoint(int x, int y)
	{
		Rectangle s = new Rectangle(this.x,this.y,sideLength,sideLength);

		return s.contains(x, y);
	}

	public String toString()
	{
		return "x: "+x+", y: "+y+", sideLength: "+sideLength;
	}
}
