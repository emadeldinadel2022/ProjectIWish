package clientiwish.views.userprofileview.pendinglist;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public abstract class PendingListBase extends AnchorPane {

    protected final TableView fRequestTableView;
    protected final TableColumn friendName;
    protected final TableColumn friendEmail;
    protected final TableColumn FriendBirthdate;

    public PendingListBase() {

        fRequestTableView = new TableView();
        friendName = new TableColumn();
        friendEmail = new TableColumn();
        FriendBirthdate = new TableColumn();

        setId("AnchorPane");
        setPrefHeight(653.0);
        setPrefWidth(815.0);

        fRequestTableView.setLayoutX(-8.0);
        fRequestTableView.setLayoutY(-1.0);
        fRequestTableView.setPrefHeight(653.0);
        fRequestTableView.setPrefWidth(815.0);

        friendName.setPrefWidth(276.0);
        friendName.setText("Reciever Name");

        friendEmail.setPrefWidth(271.0);
        friendEmail.setText("Reciever Email");

        FriendBirthdate.setPrefWidth(267.9999694824219);
        FriendBirthdate.setText("Reciever Bitthdate");

        fRequestTableView.getColumns().add(friendName);
        fRequestTableView.getColumns().add(friendEmail);
        fRequestTableView.getColumns().add(FriendBirthdate);
        getChildren().add(fRequestTableView);

    }
}
