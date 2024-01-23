package clientiwish.views.userprofileview.wishlist;

import clientCommunication.Client;
import clientiwish.models.DTOItem;
import clientiwish.models.DTOWishlist_Items;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import sharedlibraries.Response;

public class WishlistController {

    @FXML
    private TableView<DTOItem> wishlistTable;
    @FXML
    private TableColumn<DTOItem, String> itemNameCol;
    @FXML
    private TableColumn<DTOItem, String> descriptionCol;
    @FXML
    private TableColumn<DTOItem, Integer> priceCol;
    @FXML
    private TableColumn<DTOItem, Integer> reminderCol;

    ObservableList<DTOItem> itemList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<DTOItem, Integer> itemID;
    @FXML
    private Button removeWishlistItem;

    public void setItemList(ArrayList<DTOItem> items) {
        System.out.println("2: " + items);
        itemList.setAll(items);

        wishlistTable.setItems(itemList);

        itemID.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemid()).asObject();
        });

        // Set up the cell value factories without lambda expressions
        itemNameCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getItemname());
        });

        descriptionCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getItemdescription());
        });

        priceCol.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemprice()).asObject();
        });

        reminderCol.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemreminder()).asObject();
        });

    }

    @FXML
    private void removeWishlitItem(ActionEvent event) {
        if (wishlistTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Item not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the item you want to remove!");
            alert.showAndWait();
        } else {
            DTOItem addedItem = wishlistTable.getSelectionModel().getSelectedItem();
            itemList.remove(addedItem);
            int itemID = addedItem.getItemid();
            String stringUniqueUserName = Client.getCurrentUser().getUserUniqueName();

            DTOWishlist_Items dTOWishlist_Items = new DTOWishlist_Items(stringUniqueUserName, itemID);
            System.out.println(stringUniqueUserName + itemID);

            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("REMOVEITEM", dTOWishlist_Items);

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

                            alert.setTitle("Remove Item Status");
                            alert.setHeaderText(null);
                            alert.setContentText("Item removed successfully");

                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);

                            alert.setTitle("Remove Item Status");
                            alert.setHeaderText(null);
                            alert.setContentText("Failed to remove The Item, Try Again Later");

                            alert.showAndWait();
                        }
                    });
                }
            }.start();
        }

    }
}
