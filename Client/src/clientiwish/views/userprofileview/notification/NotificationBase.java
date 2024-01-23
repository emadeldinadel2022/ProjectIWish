package clientiwish.views.userprofileview.notification;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public abstract class NotificationBase extends AnchorPane {

    protected final TableView notifTableView;
    protected final TableColumn itemNumber;
    protected final TableColumn itemName;
    protected final TableColumn itemPrice;
    protected final TableColumn contributiors;
    protected final TableColumn amounts;
    protected final Button bttnCollect;

    public NotificationBase() {

        notifTableView = new TableView();
        itemNumber = new TableColumn();
        itemName = new TableColumn();
        itemPrice = new TableColumn();
        contributiors = new TableColumn();
        amounts = new TableColumn();
        bttnCollect = new Button();

        setId("AnchorPane");
        setPrefHeight(653.0);
        setPrefWidth(815.0);

        notifTableView.setLayoutX(-8.0);
        notifTableView.setLayoutY(77.0);
        notifTableView.setPrefHeight(575.0);
        notifTableView.setPrefWidth(815.0);

        itemNumber.setPrefWidth(135.0);
        itemNumber.setText("Item Number");

        itemName.setPrefWidth(135.0);
        itemName.setText("Item Name");

        itemPrice.setPrefWidth(136.0);
        itemPrice.setText("Item Price");

        contributiors.setPrefWidth(203.0);
        contributiors.setText("Contributors");

        amounts.setPrefWidth(205.0);
        amounts.setText("Amounts");

        bttnCollect.setLayoutX(313.0);
        bttnCollect.setLayoutY(19.0);
        bttnCollect.setMnemonicParsing(false);
        bttnCollect.setOnAction(this::collectItem);
        bttnCollect.setPrefHeight(37.0);
        bttnCollect.setPrefWidth(193.0);
        bttnCollect.setText("Collect Item to my cart");

        notifTableView.getColumns().add(itemNumber);
        notifTableView.getColumns().add(itemName);
        notifTableView.getColumns().add(itemPrice);
        notifTableView.getColumns().add(contributiors);
        notifTableView.getColumns().add(amounts);
        getChildren().add(notifTableView);
        getChildren().add(bttnCollect);

    }

    protected abstract void collectItem(javafx.event.ActionEvent actionEvent);

}
