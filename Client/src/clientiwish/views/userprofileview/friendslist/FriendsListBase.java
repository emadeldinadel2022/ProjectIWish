package clientiwish.views.userprofileview.friendslist;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public abstract class FriendsListBase extends AnchorPane {

    protected final TableView fListTableView;
    protected final TableColumn FriendName;
    protected final TableColumn friendEmail;
    protected final TableColumn friendBirthdate;

    public FriendsListBase() {

        fListTableView = new TableView();
        FriendName = new TableColumn();
        friendEmail = new TableColumn();
        friendBirthdate = new TableColumn();

        setId("AnchorPane");
        setPrefHeight(653.0);
        setPrefWidth(815.0);

        fListTableView.setLayoutX(-8.0);
        fListTableView.setLayoutY(-1.0);
        fListTableView.setPrefHeight(653.0);
        fListTableView.setPrefWidth(815.0);

        FriendName.setPrefWidth(267.6666717529297);
        FriendName.setText("Friend Name");

        friendEmail.setPrefWidth(281.3333282470703);
        friendEmail.setText("Friend Email");

        friendBirthdate.setPrefWidth(265.3333435058594);
        friendBirthdate.setText("Friend Birthdate");

        fListTableView.getColumns().add(FriendName);
        fListTableView.getColumns().add(friendEmail);
        fListTableView.getColumns().add(friendBirthdate);
        getChildren().add(fListTableView);

    }
}
