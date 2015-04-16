package sample;

import downloaderAndParser.DataParser;
import downloaderAndParser.ParseController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class GuiController {

    @FXML
    private ComboBox<String> combo;
    public javafx.scene.control.TextArea textarea;
    private boolean isTabelaC = false;

    @FXML
    private void initialize() {
        ParseController controller = new ParseController();
        controller.downloadData();
        combo.setOnAction((event) -> {
            String tableName = combo.getSelectionModel().getSelectedItem();
            System.out.println(tableName);
            if (tableName.equals("Tabela C"))
                isTabelaC = true;
            new DataParser(controller.getTableMap().get(tableName), this).parseData(isTabelaC);
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
