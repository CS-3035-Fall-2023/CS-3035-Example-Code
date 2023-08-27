module cs3035unb.cs3035examplecode {
    requires javafx.controls;
    requires javafx.fxml;

    exports cs3035unb.cs3035examplecode.HelloWorld;
    exports cs3035unb.cs3035examplecode.HelloWorldFXML;
    opens cs3035unb.cs3035examplecode.HelloWorldFXML to javafx.fxml;
    exports cs3035unb.cs3035examplecode.PushCounter;
    exports cs3035unb.cs3035examplecode.Utility;
    exports cs3035unb.cs3035examplecode.CustomWidgetExample;
    exports cs3035unb.cs3035examplecode.DrawingOnCanvas;
    exports cs3035unb.cs3035examplecode.StyleOptionsWithCheckBoxes;
    exports cs3035unb.cs3035examplecode.MVCSquareDrawing;
    exports cs3035unb.cs3035examplecode.MVCSquareDragging;
    exports cs3035unb.cs3035examplecode.CustomLayoutExample;
    exports cs3035unb.cs3035examplecode.DrawingWithShapeNodes;
    exports cs3035unb.cs3035examplecode.BindingExample;
    exports cs3035unb.cs3035examplecode.EventFilters;
    exports cs3035unb.cs3035examplecode.GetNotifiedOfChangeInSimpleList;
    exports cs3035unb.cs3035examplecode.ShapeHitTest;
    exports cs3035unb.cs3035examplecode.SquareDrawerWithDragSelections;
    exports cs3035unb.cs3035examplecode.FXMLwithController;
    exports cs3035unb.cs3035examplecode.ClipboardExample;
    exports cs3035unb.cs3035examplecode.ClippingExample;
    exports cs3035unb.cs3035examplecode.DeterminingEventSource;
    exports cs3035unb.cs3035examplecode.GridSnappingCanvasDrawer;
    exports cs3035unb.cs3035examplecode.LoginExample;
    exports cs3035unb.cs3035examplecode.ModelPropertyChange;
    exports cs3035unb.cs3035examplecode.MultipleSceneSwitching;
    exports cs3035unb.cs3035examplecode.SceneGraphAnimation;
    opens cs3035unb.cs3035examplecode.FXMLwithController to javafx.fxml;

}