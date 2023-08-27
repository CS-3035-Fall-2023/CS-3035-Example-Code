package cs3035unb.cs3035examplecode.SquareDrawerWithDragSelections;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Controller {
	public enum State {READY, DRAG_SELECTION_STARTED, DRAG_ITEMS_STARTED}
	private State state;
	
	public Controller() {
		Main.view.addEventHandler(MouseEvent.ANY, new MouseHandler());	
		state = State.READY;
	}
	
	public class MouseHandler implements EventHandler<MouseEvent>{
		private double prevX = 0, prevY = 0;
		
		@Override
		public void handle(MouseEvent e) {
			switch(state)
			{
				case READY:
					if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
						prevX = e.getSceneX();
						prevY = e.getSceneY();
						
						if (e.getTarget().getClass() == Rectangle.class)
						{
							Rectangle node = ((Rectangle) e.getTarget());
							node.toFront();
													
							if (!Main.iModel.getSelectedSquares().itemsProperty().contains(node))
							{
								if (!e.isControlDown())
									Main.iModel.getSelectedSquares().itemsProperty().clear();
								
								Main.iModel.getSelectedSquares().itemsProperty().add(node);
							}
							else if (e.isControlDown())
							{
								Main.iModel.getSelectedSquares().itemsProperty().remove(node);
							}
						}	
						else 
						{
							Main.iModel.getSelectedSquares().itemsProperty().clear();
						}
					}
					else if (e.getEventType()==MouseEvent.DRAG_DETECTED)
					{					
						if (e.getTarget().getClass() == Main.view.getClass())
						{
							state = State.DRAG_SELECTION_STARTED;
							Main.iModel.startSelectRegion(e.getX(), e.getY());
						}
						else if (e.getTarget().getClass() == Rectangle.class)
						{
							state = State.DRAG_ITEMS_STARTED;
						}
					}
					else if (e.getEventType()==MouseEvent.MOUSE_RELEASED)
					{
						if (e.getTarget().getClass()==Main.view.getClass())
						{
							Main.model.addSquare(e.getX(), e.getY());
						}
						
						if (e.getTarget().getClass() == Rectangle.class)
						{
							if (!e.isControlDown())
							{
								Main.iModel.getSelectedSquares().itemsProperty().clear();
							}
						}
					}
					break;	//end State.READY
			
				case DRAG_SELECTION_STARTED:
					if (e.getEventType() == MouseEvent.MOUSE_DRAGGED)
					{
						Main.iModel.updateSelectRegion(e.getX(), e.getY());
					}
					else if (e.getEventType() == MouseEvent.MOUSE_RELEASED)
					{
						state = State.READY;
						selectObjectsWithRegion();
						Main.iModel.selectRegionProperty().setValue(null);
					}
					break;// end State.DRAG_SELECTION_STARTED
			
				case DRAG_ITEMS_STARTED:
					if (e.getEventType()==MouseEvent.MOUSE_DRAGGED)
					{			
						moveSelected(e.getSceneX() - prevX, e.getSceneY() - prevY);
						prevX = e.getSceneX();
						prevY = e.getSceneY();
					}
					
					else if (e.getEventType()==MouseEvent.MOUSE_RELEASED)
					{
						state = State.READY;
					}
					break; //end State.DRAG_ITEMS_STARTED
			}//end switch(state)
		}
	}

	private void selectObjectsWithRegion() {
		Rectangle selectRegion = Main.iModel.selectRegionProperty().getValue();
		Main.iModel.getSelectedSquares().itemsProperty().clear();
		
		if (selectRegion != null)
		{
			for (Rectangle s : Main.model.squareListProperty())
			{
				Point2D topLeft = new Point2D(s.getBoundsInParent().getMinX(), s.getBoundsInParent().getMinY());
				Point2D bottomRight = new Point2D(s.getBoundsInParent().getMaxX(), s.getBoundsInParent().getMaxY());
				
				if (selectRegion.contains(topLeft) && selectRegion.contains(bottomRight))
				{
					Main.iModel.getSelectedSquares().itemsProperty().add(s);
				}
			}
		}
	}
	
	private void moveSelected(double addX, double addY) 
	{
		for (Rectangle r : Main.iModel.getSelectedSquares().itemsProperty())
		{
			r.setTranslateX(r.getLayoutX() + r.getTranslateX() + addX);
			r.setTranslateY(r.getLayoutY() + r.getTranslateY() + addY);
		}
	}
}
