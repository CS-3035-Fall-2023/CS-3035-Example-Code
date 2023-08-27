package cs3035unb.cs3035examplecode.CustomLayoutExample;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class MyLayoutPane extends Pane {

    private static int count = 0;

    public MyLayoutPane()
    {
        super();

    }

    @Override
    public void layoutChildren() {
        for (int i = 0; i < getChildren().size(); i++) {
            Node n = getChildren().get(i);

            //compute the size based on the internals of the node
            n.autosize();

            //alternatively the size can be determined using max, min, and/or pref size
            //based on your layout algorithm
//            n.prefWidth(100);
//            n.prefHeight(100);

            n.autosize();

            //relocate vs translate:
            // layout algorithms should use relocate by convention
            // application programmers would use translate
            n.relocate(this.getWidth() - n.getBoundsInParent().getWidth(),this.getHeight() - n.getBoundsInParent().getHeight());
            //n.translateXProperty().set(-100);

        }
        System.out.println(count++);

    }
}
