package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import downloaderAndParser.*;
import org.w3c.dom.Document;

import java.util.List;

public class GuiController {

    @FXML
    private ComboBox<String> combo;
    public ComboBox<String> combo1;
    public javafx.scene.control.TextArea textarea;
    private boolean isTabelaC = false;

    @FXML
    private void initialize() {
        Controller controller = new Controller();
        combo1.setItems(FXCollections.observableArrayList("asd","!@#") );
        combo.setOnAction((event) -> {
            String tableName = combo.getSelectionModel().getSelectedItem();
            if (tableName.equals("Tabela C"))
                isTabelaC = true;
            new DataParser(controller.getTableMap().get(tableName), this).parseData(isTabelaC);
            new DataParser(controller.getTableMap().get(tableName), this).getCurrencies();
            isTabelaC = false;

        });

    }

    public void setResult(String result) {
        textarea.appendText(result + "\n");
    }

    public void clearTextarea() {
        textarea.clear();
    }
}
