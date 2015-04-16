package bin;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Piotr Górak, Maciej Knicha³ dnia 2015-04-16.
 */
public class AlertWindow {
    /**
     * Jeœli komputer nie jest pod³¹czony do Internetu, to zostanie wyœwietlony Alert z odpowiedni¹ informacj¹.
     * Po jego zamkniêciu program zostanie zamkniêty.
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
