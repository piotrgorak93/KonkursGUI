package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import downloaderAndParser.*;
import org.w3c.dom.Document;

public class GuiController {

    @FXML
    private ComboBox<String> combo;
    public javafx.scene.control.TextArea textarea;
    private boolean isTabelaC = false;

    @FXML
    private void initialize() {

        combo.setOnAction((event) -> {
            String tableName = combo.getSelectionModel().getSelectedItem();
            System.out.println(tableName);
            if (tableName.equals("Tabela C"))
                isTabelaC = true;
            new DataParser(new Controller().getTableMap().get(tableName), this).parseData(isTabelaC);
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
