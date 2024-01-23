package clientiwish.views.userprofileview.friendslist;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public abstract class FriendsListBase extends AnchorPane {

    protected final TableView fListTableView;
    protected final TableColumn FriendName;
    protected final TableColumn friendEmail;
    protected final TableColumn friendBirthdate;
    protected final Button deleteFriendBttn;
    protected final Button viewWishListBttn;

    public FriendsListBase() {

        fListTableView = new TableView();
        FriendName = new TableColumn();
        friendEmail = new TableColumn();
        friendBirthdate = new TableColumn();
        deleteFriendBttn = new Button();
        viewWishListBttn = new Button();

        setId("AnchorPane");
        setPrefHeight(653.0);
        setPrefWidth(815.0);

        fListTableView.setLayoutX(-8.0);
        fListTableView.setLayoutY(75.0);
        fListTableView.setPrefHeight(577.0);
        fListTableView.setPrefWidth(815.0);

        FriendName.setPrefWidth(267.6666717529297);
        FriendName.setText("Friend Name");

        friendEmail.setPrefWidth(281.3333282470703);
        friendEmail.setText("Friend Email");

        friendBirthdate.setPrefWidth(265.3333435058594);
        friendBirthdate.setText("Friend Birthdate");

        deleteFriendBttn.setLayoutX(235.0);
        deleteFriendBttn.setLayoutY(23.0);
        deleteFriendBttn.setMnemonicParsing(false);
        deleteFriendBttn.setOnAction(this::deleteFriend);
        deleteFriendBttn.setPrefHeight(34.0);
        deleteFriendBttn.setPrefWidth(101.0);
        deleteFriendBttn.setText("Delete Friend");
        deleteFriendBttn.setFont(new Font("Baskerville Old Face", 14.0));

        viewWishListBttn.setLayoutX(425.0);
        viewWishListBttn.setLayoutY(23.0);
        viewWishListBttn.setMnemonicParsing(false);
        viewWishListBttn.setOnAction(this::viewFriendWishList);
        viewWishListBttn.setPrefHeight(34.0);
        viewWishListBttn.setPrefWidth(144.0);
        viewWishListBttn.setText("View Friend WishList");
        viewWishListBttn.setFont(new Font("Baskerville Old Face", 14.0));

        fListTableView.getColumns().add(FriendName);
        fListTableView.getColumns().add(friendEmail);
        fListTableView.getColumns().add(friendBirthdate);
        getChildren().add(fListTableView);
        getChildren().add(deleteFriendBttn);
        getChildren().add(viewWishListBttn);

    }

    protected abstract void deleteFriend(javafx.event.ActionEvent actionEvent);

    protected abstract void viewFriendWishList(javafx.event.ActionEvent actionEvent);

}
