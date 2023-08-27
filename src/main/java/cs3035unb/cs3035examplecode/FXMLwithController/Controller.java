package cs3035unb.cs3035examplecode.FXMLwithController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private Text textOutput;

    @FXML
    private Button changeTextButton;

    public void updateText(ActionEvent actionEvent) {
        textOutput.setText("CLICKED!");
        changeTextButton.setText("now there is nothing to see");
        changeTextButton.setDisable(true);
    }
}
