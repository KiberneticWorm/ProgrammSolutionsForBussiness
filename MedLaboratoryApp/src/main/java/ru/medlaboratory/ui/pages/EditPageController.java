package ru.medlaboratory.ui.pages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ru.medlaboratory.data.Utils;
import ru.medlaboratory.data.entity.User;
import ru.medlaboratory.interfaces.SetUser;
import ru.medlaboratory.ui.Controller;

import java.io.File;

public class EditPageController implements Controller, SetUser {

    @FXML private Button btnBack;
    @FXML private ImageView imageProfile;
    @FXML private Label labUsername;
    @FXML private Label labPassword;
    @FXML private Label labFio;
    @FXML private Label labAge;
    @FXML private Label labTypeUser;

    private Stage primaryStage;
    private User user;

    @Override
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    public void initialize() {
        btnBack.setGraphic(new ImageView(
                new Image(MainPageController.class.getClassLoader().getResource("ic_back.png").toExternalForm())));
        btnBack.setOnMouseClicked(view -> {
            Utils.nextPage(primaryStage, "main_page.fxml", "main_page.css");
        });

        imageProfile.setImage(new Image(new File(user.getImagePath()).toURI().toString()));
        labUsername.setText(user.getUserName());
        labPassword.setText(user.getPassword());
        labAge.setText(user.getAge() + "");
        labFio.setText(user.getFio());
        labTypeUser.setText(user.getTypeUser());
    }
}
