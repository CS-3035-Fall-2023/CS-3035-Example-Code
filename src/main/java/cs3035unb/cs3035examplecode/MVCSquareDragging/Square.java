package cs3035unb.cs3035examplecode.MVCSquareDragging;

import javafx.beans.property.SimpleDoubleProperty;

public class Square {
    protected SimpleDoubleProperty startX, startY, endX, endY;

    public Square(double startX, double startY, double endX, double endY) {
        this.startX = new SimpleDoubleProperty(startX);
        this.startY = new SimpleDoubleProperty(startY);
        this.endX = new SimpleDoubleProperty(endX);
        this.endY = new SimpleDoubleProperty(endY);
    }

}
