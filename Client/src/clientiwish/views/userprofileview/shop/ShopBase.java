package clientiwish.views.userprofileview.shop;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public abstract class ShopBase extends AnchorPane {

    protected final TableView shopTableView;
    protected final TableColumn shopID;
    protected final TableColumn shopName;
    protected final TableColumn shopDescrip;
    protected final TableColumn shopPrice;

    public ShopBase() {

        shopTableView = new TableView();
        shopID = new TableColumn();
        shopName = new TableColumn();
        shopDescrip = new TableColumn();
        shopPrice = new TableColumn();

        setId("AnchorPane");
        setPrefHeight(653.0);
        setPrefWidth(815.0);

        shopTableView.setLayoutX(-8.0);
        shopTableView.setLayoutY(-1.0);
        shopTableView.setPrefHeight(653.0);
        shopTableView.setPrefWidth(815.0);

        shopID.setPrefWidth(205.0);
        shopID.setText("Item Number");

        shopName.setPrefWidth(205.0);
        shopName.setText("Item Name");

        shopDescrip.setPrefWidth(205.0);
        shopDescrip.setText("Description");

        shopPrice.setPrefWidth(205.0);
        shopPrice.setText("Price");

        shopTableView.getColumns().add(shopID);
        shopTableView.getColumns().add(shopName);
        shopTableView.getColumns().add(shopDescrip);
        shopTableView.getColumns().add(shopPrice);
        getChildren().add(shopTableView);

    }
}
