package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 1000, 500);
        stage.setTitle("Waluty");
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);


    }

    public static void main(String[] args) {
        launch(args);
    }

}
