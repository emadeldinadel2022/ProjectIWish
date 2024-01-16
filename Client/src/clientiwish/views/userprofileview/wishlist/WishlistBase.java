package clientiwish.views.userprofileview.wishlist;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public abstract class WishlistBase extends AnchorPane {

    protected final TableView wishlistTable;
    protected final TableColumn itemID;
    protected final TableColumn itemNameCol;
    protected final TableColumn descriptionCol;
    protected final TableColumn priceCol;
    protected final TableColumn reminderCol;

    public WishlistBase() {

        wishlistTable = new TableView();
        itemID = new TableColumn();
        itemNameCol = new TableColumn();
        descriptionCol = new TableColumn();
        priceCol = new TableColumn();
        reminderCol = new TableColumn();

        setId("AnchorPane");
        setPrefHeight(653.0);
        setPrefWidth(815.0);

        wishlistTable.setLayoutX(-8.0);
        wishlistTable.setLayoutY(-1.0);
        wishlistTable.setPrefHeight(653.0);
        wishlistTable.setPrefWidth(815.0);

        itemID.setPrefWidth(163.0);
        itemID.setText("Item Number");

        itemNameCol.setPrefWidth(163.0);
        itemNameCol.setText("Item Name");

        descriptionCol.setPrefWidth(163.0);
        descriptionCol.setText("Description");

        priceCol.setPrefWidth(163.0);
        priceCol.setText("Price");

        reminderCol.setPrefWidth(163.0);
        reminderCol.setText("Reminder");

        wishlistTable.getColumns().add(itemID);
        wishlistTable.getColumns().add(itemNameCol);
        wishlistTable.getColumns().add(descriptionCol);
        wishlistTable.getColumns().add(priceCol);
        wishlistTable.getColumns().add(reminderCol);
        getChildren().add(wishlistTable);

    }
}
