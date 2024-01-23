package clientiwish.views.userprofileview.friendwishlist;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public abstract class FriendWishlistBase extends AnchorPane {

    protected final TableView wishlistTable;
    protected final TableColumn itemNameCol;
    protected final TableColumn descriptionCol;
    protected final TableColumn priceCol;
    protected final TableColumn reminderCol;
    protected final TableColumn contributeCol;
    protected final Button contributBttn;

    public FriendWishlistBase() {

        wishlistTable = new TableView();
        itemNameCol = new TableColumn();
        descriptionCol = new TableColumn();
        priceCol = new TableColumn();
        reminderCol = new TableColumn();
        contributeCol = new TableColumn();
        contributBttn = new Button();

        setId("AnchorPane");
        setPrefHeight(653.0);
        setPrefWidth(815.0);

        wishlistTable.setLayoutX(-8.0);
        wishlistTable.setLayoutY(99.0);
        wishlistTable.setPrefHeight(553.0);
        wishlistTable.setPrefWidth(815.0);

        itemNameCol.setPrefWidth(153.0);
        itemNameCol.setText("Item Name");

        descriptionCol.setPrefWidth(232.0);
        descriptionCol.setText("Description");

        priceCol.setPrefWidth(140.0);
        priceCol.setText("Price");

        reminderCol.setPrefWidth(146.0);
        reminderCol.setText("Reminder");

        contributeCol.setPrefWidth(143.0);
        contributeCol.setText("Comtribution ");

        contributBttn.setLayoutX(341.0);
        contributBttn.setLayoutY(40.0);
        contributBttn.setMnemonicParsing(false);
        contributBttn.setPrefHeight(25.0);
        contributBttn.setPrefWidth(117.0);
        contributBttn.setText("Contribute");
        contributBttn.setFont(new Font("Baskerville Old Face", 16.0));

        wishlistTable.getColumns().add(itemNameCol);
        wishlistTable.getColumns().add(descriptionCol);
        wishlistTable.getColumns().add(priceCol);
        wishlistTable.getColumns().add(reminderCol);
        wishlistTable.getColumns().add(contributeCol);
        getChildren().add(wishlistTable);
        getChildren().add(contributBttn);

    }
}
