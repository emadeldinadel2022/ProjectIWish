package clientiwish.views.userprofileview.friendrequest;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public abstract class FriendRequestBase extends AnchorPane {

    protected final TableView fRequestTableView;
    protected final TableColumn friendName;
    protected final TableColumn friendEmail;
    protected final TableColumn friendBirthdate;

    public FriendRequestBase() {

        fRequestTableView = new TableView();
        friendName = new TableColumn();
        friendEmail = new TableColumn();
        friendBirthdate = new TableColumn();

        setId("AnchorPane");
        setPrefHeight(653.0);
        setPrefWidth(815.0);

        fRequestTableView.setLayoutX(-8.0);
        fRequestTableView.setLayoutY(-1.0);
        fRequestTableView.setPrefHeight(653.0);
        fRequestTableView.setPrefWidth(815.0);

        friendName.setPrefWidth(272.0);
        friendName.setText("Sender Name");

        friendEmail.setPrefWidth(272.0);
        friendEmail.setText("Sender Email");

        friendBirthdate.setPrefWidth(272.0);
        friendBirthdate.setText("Sender birthdate");

        fRequestTableView.getColumns().add(friendName);
        fRequestTableView.getColumns().add(friendEmail);
        fRequestTableView.getColumns().add(friendBirthdate);
        getChildren().add(fRequestTableView);

    }
}
