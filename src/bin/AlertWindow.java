package bin;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Piotr G�rak, Maciej Knicha� dnia 2015-04-16.
 */
public class AlertWindow {
    /**
     * Je�li komputer nie jest pod��czony do Internetu, to zostanie wy�wietlony Alert z odpowiedni� informacj�.
     * Po jego zamkni�ciu program zostanie zamkni�ty.
     */


    public void initialize() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("bin/icon.png"));
        alert.setTitle("Blad");
        alert.setContentText("Problem z polaczeniem sieciowym!");
        alert.showAndWait();
    }
}
