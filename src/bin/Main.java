package bin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * G³ówny kontroler aplikacji
 * Tworzy okno graficzne, ustawia jego rozmiar, maksymalny oraz minimalny rozmiar, który jest równy rozmiarowi przy utworzeniu.
 * Daje to efekt jak przy setMaximized(true), jednak nie usuwa zmiany kursora przy najechaniu na krawêdzie okna, tytu³ oraz ikonê aplikacji
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
