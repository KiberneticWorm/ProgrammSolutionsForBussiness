package ru.medlaboratory.ui.pages;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import ru.medlaboratory.data.*;
import ru.medlaboratory.data.entity.User;
import ru.medlaboratory.listview.CustomCell;
import ru.medlaboratory.ui.Controller;

import java.sql.SQLException;

import static javafx.collections.FXCollections.observableArrayList;


public class MainPageController implements Controller {

    @FXML private Button btnBack;
    @FXML private ListView lstUsers;

    private Stage primaryStage;

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initialize() {
        btnBack.setGraphic(new ImageView(
                new Image(MainPageController.class.getClassLoader().getResource("ic_back.png").toExternalForm())));
        btnBack.setOnMouseClicked(view -> {
            Utils.nextPage(primaryStage, "main.fxml", "main.css");
        });

        if (DataContainer.currentUser.getTypeUser().equals("admin")) {
            Utils.runInBack(() -> {
                try {
                    return DataContainer.dbConnect.getUsers();
                } catch (SQLException exc) {
                    exc.printStackTrace();
                    return null;
                }
            }, result -> {
                if (result == null) {
                    return;
                }
                final ObservableList<User> users = FXCollections.observableArrayList(result);
                lstUsers.setItems(users);
                lstUsers.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {

                    @Override
                    public ListCell<User> call(ListView<User> param) {
                        return new CustomCell(new EventHandler() {
                            @Override
                            public void handle(Event event) {
                                Utils.nextPage(primaryStage, "edit_user_page.fxml", "edit_user_page.css",
                                        result.get(0));
                            }
                        });
                    }
                });
            });
        }
    }
}
