package cs3035unb.cs3035examplecode.MVCSquareDragging;

import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class Model {

    private SimpleListProperty<Square> squareListProperty;

    public Model() {
        //this big statement creates an observable property within the square model itself
        //this allows us not only to notify listeners if a square is added or removed from the list
        //but also to notify listeners if any change happens inside the square object
        //our previous examples have only worked on the adding and removing from the list
        ObservableList<Square> observableSquareList = (ObservableList<Square>) FXCollections.observableArrayList(
                new Callback<Square, Observable[]>() {
                    @Override
                    public Observable[] call(Square s){
                        return new Observable[]{
                            s.startX,
                            s.startY,
                            s.endX,
                            s.endY
                    };
                }
            }
        );
        squareListProperty = new SimpleListProperty<>(observableSquareList);
    }

    public ListProperty<Square> squareListProperty() {
        return squareListProperty;
    }

    //create a new square with no height and width
    //add it to the model and return it
    public Square addSquare(double x, double y) {
        Square newSquare = new Square(x, y, x, y);
        squareListProperty.add(newSquare);
        return newSquare;
    }
}
