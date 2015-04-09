package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import downloaderAndParser.*;
public class Controller {

    @FXML
    private ComboBox<String> combo;
    public javafx.scene.control.TextArea textarea;

    @FXML
    private void initialize() {
        combo.setOnAction((event) -> {
            combo.getSelectionModel().getSelectedItem();
            System.out.println(combo.getSelectionModel().getSelectedItem());
        });

    }

}
