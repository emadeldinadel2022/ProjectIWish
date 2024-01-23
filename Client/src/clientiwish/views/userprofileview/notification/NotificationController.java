/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.views.userprofileview.notification;

import clientCommunication.Client;
import clientiwish.models.DTOCart;
import clientiwish.models.DTOItem;
import clientiwish.models.DTONotification;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sharedlibraries.Response;

public class NotificationController {

    @FXML
    private TableView<DTONotification> notifTableView;
    @FXML
    private TableColumn<DTONotification, String> itemName;
    @FXML
    private TableColumn<DTONotification, Integer> itemPrice;

    ObservableList<DTONotification> contributeList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<DTONotification, Integer> itemNumber;
    @FXML
    private TableColumn<DTONotification, String> contributiors;
    @FXML
    private TableColumn<DTONotification, String> amounts;
    @FXML
    private Button bttnCollect;

    public void setNotificationItems(ArrayList<DTONotification> notifications) {

        System.out.println("2: " + notifications);
        contributeList.setAll(notifications);

        notifTableView.setItems(contributeList);

        itemNumber.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemNumber()).asObject();
        });

        itemName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getItemName());
        });

        itemPrice.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemPrice()).asObject();
        });

        contributiors.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getContributors());
        });

        amounts.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getContributedAmounts());
        });

    }

    @FXML
    private void collectItem(ActionEvent event) {

        DTONotification addedItem = notifTableView.getSelectionModel().getSelectedItem();

        if (addedItem == null) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Notification List Status");
            alert.setHeaderText(null);
            alert.setContentText("Failed to collect item , because there is not item has been selected");

            alert.showAndWait();
            
            
        } else {
            int itemID = addedItem.getItemNumber();
            String stringUniqueUserName = Client.getCurrentUser().getUserUniqueName();

            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("COLLECTITEM", new DTOCart(stringUniqueUserName, itemID));

                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Response<DTOItem> responseAddedItem = Client.getResponse();
                    Platform.runLater(() -> {
                        if (responseAddedItem != null && responseAddedItem.isSuccess()) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);

                            alert.setTitle("Cart item Collect");
                            alert.setHeaderText(null);
                            alert.setContentText("Item add to your cart, Check your cart now");

                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);

                            alert.setTitle("Cart item Collect");
                            alert.setHeaderText(null);
                            alert.setContentText("Failed to add The Item to your cart, Item is already in your Cart");

                            alert.showAndWait();
                        }
                    });
                }
            }.start();
        }

    }

}
