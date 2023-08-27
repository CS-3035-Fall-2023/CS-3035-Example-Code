package cs3035unb.cs3035examplecode.ClipboardExample;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application{
    private Font font1 = Font.font("Segoe Print", FontWeight.NORMAL, 14);
    private Font font2 = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 20);
    private Font currentFont = font1;
    private MyTextArea textArea;

    public static void main(String[]args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        BorderPane root = new BorderPane();
        textArea = new MyTextArea();
        textArea.setFont(currentFont);

        root.setTop(buttonBar());

        root.setCenter(textArea);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    private Node buttonBar() {
        HBox buttonBox = new HBox(5);

        Button toggleFontButton = new Button("switch font");
        toggleFontButton.setFont(currentFont);
        toggleFontButton.setOnAction(event -> {
            if (font1 == currentFont)
                currentFont = font2;
            else
                currentFont = font1;

            textArea.setFont(currentFont);
            toggleFontButton.setFont(currentFont);
        });

        Button copyButton = new Button("copy");
        copyButton.setOnAction(event->{
            textArea.copy();
        });

        Button cutButton = new Button("cut");
        cutButton.setOnAction(event->{
            textArea.cut();
        });
        Button pasteButton = new Button("paste");

        buttonBox.getChildren().addAll(toggleFontButton, copyButton, cutButton, pasteButton);

        return buttonBox;
    }
}

//    Node node = new Circle(100, 200, 200);
//    PrinterJob job = PrinterJob.createPrinterJob();
// if (job != null) {
//         boolean success = job.printPage(node);
//         if (success) {
//         job.endJob();
//         }
//         }