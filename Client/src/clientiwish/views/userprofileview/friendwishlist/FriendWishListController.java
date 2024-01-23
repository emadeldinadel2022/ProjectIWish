/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.views.userprofileview.friendwishlist;

import clientCommunication.Client;
import clientiwish.models.DTOContribution;
import clientiwish.models.DTOItem;
import clientiwish.views.userprofileview.friendslist.FriendsListController;
import clientiwish.views.userprofileview.home.UserProfileController;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sharedlibraries.Response;

/**
 *
 * @author Software
 */
public class FriendWishListController {

    @FXML
    private AnchorPane contributeBttn;

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

    @FXML
    private Button contributBttn;

    ObservableList<DTOItem> itemList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<DTOItem, Integer> itemNumberCol;
    @FXML
    private TextField txtfieldContributedAmount;

    @FXML
    private Label labelSelectedUser;
    @FXML
    private Label labelViewName;
    @FXML
    private Label balanceLabel;
    private String uniqueNameSelectedUser;

    public String getUniqueNameSelectedUser() {
        return uniqueNameSelectedUser;
    }

    public void setUniqueNameSelectedUser(String uniqueNameSelectedUser) {
        this.uniqueNameSelectedUser = uniqueNameSelectedUser;
    }

    public Label getLabelViewName() {
        return labelViewName;
    }

    public void setItemList(ArrayList<DTOItem> items) {
        System.out.println("2: " + items);
        itemList.setAll(items);
        balanceLabel.setText("$ " + String.valueOf(Client.getCurrentUser().getBalance()));
        wishlistTable.setItems(itemList);
        System.out.println("    " + labelViewName.getText());
        // Set up the cell value factories without lambda expressions
        itemNumberCol.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemid()).asObject();
        });

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
    private void makeContribution(ActionEvent event) {
        String contributedAmountText = txtfieldContributedAmount.getText();
        if (txtfieldContributedAmount.getText() == null || wishlistTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Contribution Status");
            alert.setHeaderText(null);
            alert.setContentText("Failed to contribute, because your contributed amount is empty or wish not been selected!");
            alert.showAndWait();

        } else if (!contributedAmountText.matches("\\d+") || contributedAmountText.matches("0")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Contribution Status");
            alert.setHeaderText(null);
            alert.setContentText("Failed to contribute, because your contributed amount is not correct!");
            alert.showAndWait();
        } else if (wishlistTable.getSelectionModel().getSelectedItem().getItemreminder() > Client.getCurrentUser().getBalance()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Contribution Status");
            alert.setHeaderText(null);
            alert.setContentText("Insufficient balance, please recharge and try again!");
            alert.showAndWait();
        } else if (wishlistTable.getSelectionModel().getSelectedItem() != null && Integer.parseInt(txtfieldContributedAmount.getText()) > 0 || txtfieldContributedAmount.getText() != null) {
            DTOItem selectedItem = wishlistTable.getSelectionModel().getSelectedItem();
            int id = selectedItem.getItemid();
            String selectedUserUniqueName = getUniqueNameSelectedUser();
            int amount = Integer.parseInt(txtfieldContributedAmount.getText());

            System.out.println(id);
            System.out.println(selectedUserUniqueName);
            System.out.println(amount);

            if (amount > Client.getCurrentUser().getBalance()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Contribution Status");
                alert.setHeaderText(null);
                alert.setContentText("Failed to contribute to " + selectedUserUniqueName + " , because your contributed amount is greater than your balance");

                alert.showAndWait();
            } else if (amount > selectedItem.getItemprice()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Contribution Status");
                alert.setHeaderText(null);
                alert.setContentText("Failed to contribute to " + selectedUserUniqueName + " , because your contributed amount is greater than item price");

                alert.showAndWait();
            } else {
                new Thread() {
                    @Override
                    public void run() {
                        Client.updateResponse(null);
                        Client.sendRequest("CONTRIBUTION", new DTOContribution(Client.getCurrentUser().getUserUniqueName(), selectedUserUniqueName, id, amount));

                        while (Client.getResponse() == null) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        Response<ArrayList<DTOItem>> responseAddedItem = Client.getResponse();
                        Platform.runLater(() -> {
                            if (responseAddedItem != null && responseAddedItem.isSuccess()) {
                                setItemList(responseAddedItem.getData());

                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                ArrayList<DTOItem> updatedItems = responseAddedItem.getData();
                                alert.setTitle("Contribution Status");
                                alert.setHeaderText(null);
                                alert.setContentText("Contribution done successfully");
                                alert.showAndWait();

                                int balance = Client.getCurrentUser().getBalance();
                                int subbalance = balance - amount;
                                System.out.println(subbalance);
                                setItemList(updatedItems);

                                Client.getCurrentUser().setBalance(balance - amount);
                                balanceLabel.setText("$ " + String.valueOf(Client.getCurrentUser().getBalance()));

                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Contribution Status");
                                alert.setHeaderText(null);
                                alert.setContentText("Failed to contribute to " + selectedUserUniqueName + " , because item's contribution is completed");

                                alert.showAndWait();
                            }
                        });
                    }
                }
                        .start();
            }
        }

    }
}
