package clientiwish.views.userprofileview.notification;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public abstract class NotificationBase extends AnchorPane {

    protected final TableView notifTableView;
    protected final TableColumn itemName;
    protected final TableColumn itemPrice;
    protected final TableColumn NotifContributors;

    public NotificationBase() {

        notifTableView = new TableView();
        itemName = new TableColumn();
        itemPrice = new TableColumn();
        NotifContributors = new TableColumn();

        setId("AnchorPane");
        setPrefHeight(653.0);
        setPrefWidth(815.0);

        notifTableView.setLayoutX(-8.0);
        notifTableView.setLayoutY(-1.0);
        notifTableView.setPrefHeight(653.0);
        notifTableView.setPrefWidth(815.0);

        itemName.setPrefWidth(200.0);
        itemName.setText("Item Name");

        itemPrice.setPrefWidth(200.0);
        itemPrice.setText("Item Price");

        NotifContributors.setPrefWidth(414.0);
        NotifContributors.setText("Contributors");

        notifTableView.getColumns().add(itemName);
        notifTableView.getColumns().add(itemPrice);
        notifTableView.getColumns().add(NotifContributors);
        getChildren().add(notifTableView);

    }
}
