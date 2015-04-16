package sample;

import downloaderAndParser.DataParser;
import downloaderAndParser.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

/**
 * Kontroler graficzny
 */
public class GuiController {
    /**
     * Deklaracja element�w interfejsu
     */
    @FXML
    private ComboBox<String> combo;
    public javafx.scene.control.TextArea textarea;
    /**
     * Domy�lnie Tabela C nie jest wybrana
     */
    private boolean isTabelaC = false;

    /**
     * Funkcja wywo�ywana po utworzeniu interfejsu
     */
    @FXML
    private void initialize() {
        Controller controller = new Controller();
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

    /**
     * Dopisuje String do obiektu textarea
     *
     * @param result Warto�� do dopisania w postaci String
     */
    public void setResult(String result) {
        textarea.appendText(result + "\n");
    }

    /**
     * Czy�ci obiekt textarea
     */
    public void clearTextarea() {
        textarea.clear();
    }
}
