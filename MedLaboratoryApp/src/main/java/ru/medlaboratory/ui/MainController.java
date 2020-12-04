package ru.medlaboratory.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ru.medlaboratory.data.*;
import ru.medlaboratory.data.entity.User;


public class MainController implements Controller {

    @FXML private Button btnLogin;
    @FXML private TextField tfLogin;
    @FXML private PasswordField tfPassword;
    @FXML private ImageView ivCaptcha;

    private Stage primaryStage;

    @Override
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void initialize() {
        btnLogin.setOnMouseClicked(view -> {

            Utils.runInBack(new GetResult<Boolean>() {

                @Override
                public Boolean getResult() {

                    User user = DataContainer.dbConnect.getUserByUsernameAndPassword(tfLogin.getText(), tfPassword.getText());
                    DataContainer.currentUser = user;

                    boolean isChecked = user != null;

                    return isChecked;
                }
            }, new UpdateUI<Boolean>() {
                @Override
                public void updateUI(Boolean result) {

                    if (result) {
                        Utils.nextPage(primaryStage, "main_page.fxml", "main_page.css");
                    }
                }
            });
        });
    }
}
