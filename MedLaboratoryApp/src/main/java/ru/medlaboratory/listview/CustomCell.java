package ru.medlaboratory.listview;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import ru.medlaboratory.data.DataContainer;
import ru.medlaboratory.data.Utils;
import ru.medlaboratory.data.entity.User;
import java.io.File;


public class CustomCell extends ListCell<User> {

    private ImageView getImageViewFromPath(String imagePath) {
        ImageView imageView = new ImageView(new Image(new File(imagePath).toURI().toString()));
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        return imageView;
    }

    private TextFlow getTextFlowFromText(String propertyTitle, String valueTitle) {
        TextFlow labTwoText = new TextFlow();

        Text textPropertyBold = new Text(propertyTitle);
        textPropertyBold.setStyle("-fx-font-weight: bold; -fx-font-size: 17px;");

        Text textValueRegular = new Text(valueTitle);
        textValueRegular.setStyle("-fx-font-weight: regular; -fx-font-size: 17px");

        labTwoText.getChildren().addAll(textPropertyBold, textValueRegular);

        return labTwoText;
    }

    private EventHandler<? super MouseEvent> handler;
    public CustomCell(EventHandler handler) {
        this.handler = handler;
    }

    public void updateItem(User item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null) return;
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getStyleClass().add("custom_cell");

        final ImageView imageView = getImageViewFromPath(item.getImagePath());

        final TextFlow labUserName = getTextFlowFromText("Username: ", item.getUserName());
        VBox.setMargin(labUserName, new Insets(10, 0, 5, 0));

        final TextFlow labFio = getTextFlowFromText("Name: ", item.getFio());
        VBox.setMargin(labFio, new Insets(0, 0, 5, 0));

        final TextFlow labTypeUser = getTextFlowFromText("Role: ", item.getTypeUser());
        VBox.setMargin(labTypeUser, new Insets(0, 0, 10, 0));

        final Button btnEdit = new Button("Edit user");
        btnEdit.setOnMouseClicked(handler);

        vBox.getChildren().addAll(imageView, labUserName, labFio, labTypeUser, btnEdit);
        setGraphic(vBox);
    }

}
