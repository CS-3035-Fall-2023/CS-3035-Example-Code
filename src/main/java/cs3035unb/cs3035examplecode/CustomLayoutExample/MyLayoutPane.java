package cs3035unb.cs3035examplecode.CustomLayoutExample;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;

public class MyLayoutPane extends Pane {

    private static int count = 0;

    public MyLayoutPane()
    {
        super();
    }

    @Override
    protected void layoutChildren() {
        double buttonWidth = this.getWidth() *.10;
        double buttonHeight = this.getHeight() * .10;
        double spacing = 20; // Adjust the spacing between buttons
        double x = 0, y = 0;
        for (Node node : getChildren()) {

                node.maxHeight(buttonHeight);
                node.maxWidth(buttonWidth);
                node.resizeRelocate(x, y, buttonWidth, buttonHeight);

                // Calculate the position of the next child
                x += (buttonWidth + spacing);
                y += (buttonHeight + spacing);

        }
        System.out.println(++count);
    }
}
