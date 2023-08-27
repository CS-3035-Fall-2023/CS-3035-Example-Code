package cs3035unb.cs3035examplecode.ShapeHitTest;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private ArrayList<Shape> cutShapes = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane root = new Pane();

        Rectangle rect = new Rectangle(10, 10, 30, 30);
        Ellipse circle = new Ellipse(60, 60, 15, 15);
        Polygon poly = new Polygon(100, 100, 100, 110, 110, 120, 130, 130, 130, 110, 100, 100);

        rect.setTranslateX(100);
        rect.setTranslateY(10);

        poly.setTranslateX(-20);
        poly.setTranslateY(20);

        rect.setOnMousePressed(this::shapeClickHandler);
        circle.setOnMousePressed(this::shapeClickHandler);
        poly.setOnMousePressed(this::shapeClickHandler);

        root.getChildren().addAll(rect, circle, poly);

        root.addEventHandler(MouseEvent.MOUSE_PRESSED, this::rootClickHandler);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    private void rootClickHandler(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getSource().getClass());
        System.out.println(mouseEvent.getTarget().getClass());
        System.out.println();

        if (mouseEvent.isPrimaryButtonDown()) {
            Pane root = (Pane) mouseEvent.getSource();
            for (Shape s : cutShapes)
            {
                s.setTranslateX(s.getTranslateX() + mouseEvent.getX());
                s.setTranslateY(s.getTranslateY() + mouseEvent.getY());
            }
            root.getChildren().addAll(cutShapes);
            cutShapes.clear();
        }
    }

    private void shapeClickHandler(MouseEvent mouseEvent) {
        Shape s = (Shape) mouseEvent.getTarget();
        Pane root = (Pane) s.getParent();

        if (mouseEvent.isPrimaryButtonDown()) {
            //        System.out.println("Shape contains: "+(s.contains(mouseEvent.getX(), mouseEvent.getY())));

            Point2D topLeftLocal = new Point2D(s.getBoundsInLocal().getMinX(), s.getBoundsInLocal().getMinY());
            Point2D bottomRightLocal = new Point2D(s.getBoundsInLocal().getMaxX(), s.getBoundsInLocal().getMaxY());

            Point2D topLeftParent = new Point2D(s.getBoundsInParent().getMinX(), s.getBoundsInParent().getMinY());
            Point2D bottomRightParent = new Point2D(s.getBoundsInParent().getMaxX(), s.getBoundsInParent().getMaxY());

            System.out.println("local: the top left of this shape is: " + topLeftLocal);
            System.out.println("local: the bottom right of this shape is: " + bottomRightLocal);
            System.out.println("parent: the top left of this shape is: " + topLeftParent);
            System.out.println("parent: the bottom right of this shape is: " + bottomRightParent);

            System.out.println();
        }
        else if (mouseEvent.isSecondaryButtonDown())
        {
            cutShapes.add(s);
            root.getChildren().remove(s);
        }
//        mouseEvent.consume();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
