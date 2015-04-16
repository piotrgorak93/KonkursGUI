package bin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * G��wny kontroler aplikacji
 * Tworzy okno graficzne, ustawia jego rozmiar, maksymalny oraz minimalny rozmiar, kt�ry jest r�wny rozmiarowi przy utworzeniu.
 * Daje to efekt jak przy setMaximized(true), jednak nie usuwa zmiany kursora przy najechaniu na kraw�dzie okna, tytu� oraz ikon� aplikacji
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 550, 600);
        stage.setTitle("Waluty");
        stage.setMaxHeight(scene.getHeight());
        stage.setMaxWidth(scene.getWidth());
        stage.setMinHeight(scene.getHeight());
        stage.setMinWidth(scene.getWidth());
        stage.getIcons().add(new Image("bin/icon.png"));
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
