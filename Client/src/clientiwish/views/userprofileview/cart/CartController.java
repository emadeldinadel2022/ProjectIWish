package clientiwish.views.userprofileview.cart;

import clientCommunication.Client;
import clientiwish.models.DTOCart;
import clientiwish.models.DTOItem;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import sharedlibraries.Response;

public class CartController {

    @FXML
    private TableColumn<DTOItem, Integer> itemNumber;
    @FXML
    private TableColumn<DTOItem, String> itemName;
    @FXML
    private TableColumn<DTOItem, Integer> itemPrice;
    @FXML
    private Label labelViewName;
    @FXML
    private ImageView iconImageView;
    @FXML
    private Button bttnRemoveItem;

    ObservableList<DTOItem> cartItems = FXCollections.observableArrayList();
    @FXML
    private TableView<DTOItem> cartTableView;

    public void setItems(ArrayList<DTOItem> items) {

        System.out.println("2: " + items);
        cartItems.setAll(items);

        cartTableView.setItems(cartItems);

        itemNumber.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemid()).asObject();
        });

        itemName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getItemname());
        });

        itemPrice.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemprice()).asObject();
        });

    }

    @FXML
    private void removeCartItem(ActionEvent event) {

        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);

        alert1.setTitle("Cart List Status");
        alert1.setHeaderText(null);
        alert1.setContentText("item will removed from your wishlist and notifications also");

        DTOItem selectedItem = cartTableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Cart List Status");
            alert.setHeaderText(null);
            alert.setContentText("Failed to remove item from cart , because there is not item has been selected");

            alert.showAndWait();
        } else {

            DTOCart removedItem = new DTOCart(Client.getCurrentUser().getUserUniqueName(), selectedItem.getItemid());
            cartItems.remove(selectedItem);
            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("CARTITEMREMOVE", removedItem);

                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Response<DTOCart> responseCancelContribution = Client.getResponse();
                    Platform.runLater(() -> {
                        if (responseCancelContribution != null && responseCancelContribution.isSuccess()) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);

                            alert.setTitle("Cart Item Reomve");
                            alert.setHeaderText(null);
                            alert.setContentText("Item removed from your cart Successfully");

                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);

                            alert.setTitle("Cancel Contribution");
                            alert.setHeaderText(null);
                            alert.setContentText("Failed to remove item from the cart");

                            alert.showAndWait();
                        }
                    });

                }

            }.start();

        }
    }

}
