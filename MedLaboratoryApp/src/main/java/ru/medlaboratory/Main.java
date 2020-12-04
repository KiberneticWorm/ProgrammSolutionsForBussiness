package ru.medlaboratory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.medlaboratory.data.Constants;
import ru.medlaboratory.ui.MainController;

public class Main extends Application {

    public void start(Stage stage) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("main.fxml"));
            root = loader.load();
            MainController controller = loader.getController();
            controller.setPrimaryStage(stage);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        stage.setScene(new Scene(root, Constants.WIDTH, Constants.HEIGHT));
        stage.getScene().getStylesheets().add(getClass().getClassLoader().getResource("main.css").toExternalForm());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
