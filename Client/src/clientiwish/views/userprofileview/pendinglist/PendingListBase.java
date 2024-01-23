package clientiwish.views.userprofileview.pendinglist;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public abstract class PendingListBase extends AnchorPane {

    protected final TableView fRequestTableView;
    protected final TableColumn friendName;
    protected final TableColumn friendEmail;
    protected final TableColumn FriendBirthdate;
    protected final Button btnRemoveRequest;
    protected final Label removeSuccessAlert;

    public PendingListBase() {

        fRequestTableView = new TableView();
        friendName = new TableColumn();
        friendEmail = new TableColumn();
        FriendBirthdate = new TableColumn();
        btnRemoveRequest = new Button();
        removeSuccessAlert = new Label();

        setId("AnchorPane");
        setPrefHeight(653.0);
        setPrefWidth(815.0);

        fRequestTableView.setLayoutX(-8.0);
        fRequestTableView.setLayoutY(57.0);
        fRequestTableView.setPrefHeight(595.0);
        fRequestTableView.setPrefWidth(815.0);

        friendName.setPrefWidth(276.0);
        friendName.setText("Reciever Name");

        friendEmail.setPrefWidth(271.0);
        friendEmail.setText("Reciever Email");

        FriendBirthdate.setPrefWidth(267.9999694824219);
        FriendBirthdate.setText("Reciever Bitthdate");

        btnRemoveRequest.setAlignment(javafx.geometry.Pos.CENTER);
        btnRemoveRequest.setLayoutX(23.0);
        btnRemoveRequest.setLayoutY(12.0);
        btnRemoveRequest.setMnemonicParsing(false);
        btnRemoveRequest.setOnAction(this::removePendingRequest);
        btnRemoveRequest.setPrefHeight(31.0);
        btnRemoveRequest.setPrefWidth(228.0);
        btnRemoveRequest.setText("Remove Request");
        btnRemoveRequest.setFont(new Font("System Bold", 15.0));

        removeSuccessAlert.setLayoutX(454.0);
        removeSuccessAlert.setLayoutY(17.0);
        removeSuccessAlert.setPrefHeight(21.0);
        removeSuccessAlert.setPrefWidth(300.0);
        removeSuccessAlert.setTextFill(javafx.scene.paint.Color.valueOf("#ff0505"));
        removeSuccessAlert.setFont(new Font("System Bold", 15.0));

        fRequestTableView.getColumns().add(friendName);
        fRequestTableView.getColumns().add(friendEmail);
        fRequestTableView.getColumns().add(FriendBirthdate);
        getChildren().add(fRequestTableView);
        getChildren().add(btnRemoveRequest);
        getChildren().add(removeSuccessAlert);

    }

    protected abstract void removePendingRequest(javafx.event.ActionEvent actionEvent);

}
