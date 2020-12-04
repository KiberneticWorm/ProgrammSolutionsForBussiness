package ru.medlaboratory.data;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.medlaboratory.data.entity.User;
import ru.medlaboratory.interfaces.SetUser;
import ru.medlaboratory.ui.Controller;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;




public class Utils {

    private static Executor executor = Executors.newFixedThreadPool(2);

    public static <T> void runInBack(GetResult<T> getResult, UpdateUI<T> updateUI) {
        executor.execute(() -> {
            T result = getResult.getResult();

            Platform.runLater(() -> {
                updateUI.updateUI(result);
            });
        });
    }

    public static <T> void nextPage(Stage primaryStage, String layoutName, String styleSheets) {
        FXMLLoader loader = new FXMLLoader(Utils.class.getClassLoader().getResource(layoutName));
        Parent root = null;
        try {
            root = loader.load();
            Controller controller = loader.getController();
            if (controller != null) {
                controller.setPrimaryStage(primaryStage);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, Constants.WIDTH, Constants.HEIGHT));
        primaryStage.getScene()
                .getStylesheets()
                .add(Utils.class.getClassLoader().getResource(styleSheets).toExternalForm());
    }

    public static <T> void nextPage(Stage primaryStage, String layoutName, String styleSheets, User user) {
        FXMLLoader loader = new FXMLLoader(Utils.class.getClassLoader().getResource(layoutName));
        Parent root = null;
        try {
            root = loader.load();
            SetUser controller = loader.getController();
            if (controller != null) {
                controller.setPrimaryStage(primaryStage);
                controller.setUser(user);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, Constants.WIDTH, Constants.HEIGHT));
        primaryStage.getScene()
                .getStylesheets()
                .add(Utils.class.getClassLoader().getResource(styleSheets).toExternalForm());
    }
}
