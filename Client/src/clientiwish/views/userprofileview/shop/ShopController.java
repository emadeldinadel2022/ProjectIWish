/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.views.userprofileview.shop;

import clientCommunication.Client;
import clientiwish.models.DTOItem;
import clientiwish.models.DTOWishlist_Items;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import sharedlibraries.Response;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ShopController {

    @FXML
    private TableView<DTOItem> shopTableView;

    //  @FXML
    //  private TableColumn<?, ?> shopImage;
    @FXML
    private TableColumn<DTOItem, String> shopName;

    @FXML
    private TableColumn<DTOItem, String> shopDescrip;

    @FXML
    private TableColumn<DTOItem, Integer> shopPrice;

    @FXML
    private TableColumn<DTOItem, ImageView> shopImage;
    
    @FXML
    private Button addItemButton;
    
    

    //  @FXML
    //  private TableColumn<?, ?> shopButton;
    ObservableList<DTOItem> itemList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<DTOItem, Integer> shopID;
    @FXML
    private ImageView iconImageView;
    @FXML
    private Label labelViewName;

    public void setItemList(ArrayList<DTOItem> items) {
        System.out.println("2: " + items);
        itemList.setAll(items);

        shopTableView.setItems(itemList);

        shopID.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemid()).asObject();
        });

        // Set up the cell value factories without lambda expressions
        shopName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getItemname());
        });

        shopDescrip.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getItemdescription());
        });

        shopPrice.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemprice()).asObject();
        });
        
        shopImage.setCellFactory(col -> new TableCell<DTOItem, ImageView>() {
            private final ImageView imageView = new ImageView();

            {
                setGraphic(imageView);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            @Override
            protected void updateItem(ImageView item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    imageView.setImage(null);
                } else {
                    imageView.setImage(item.getImage());
                    StackPane stackPane = new StackPane(item);
                    stackPane.setAlignment(Pos.CENTER);
                    setGraphic(stackPane);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                }
            }
        });

        shopImage.setCellValueFactory(cellData -> {
            ImageView imageView = createImageView(cellData.getValue().getItemimage());
            // Center the ImageView within the cell
            return new SimpleObjectProperty<>(imageView);
        });
    }
    private ImageView createImageView(byte[] imageData) {
        if (imageData == null || imageData.length == 0) {
            return new ImageView();  // or return a default image if appropriate
        }

        Image image = new Image(new ByteArrayInputStream(imageData));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100); // Set the width as needed
        imageView.setFitHeight(100); // Set the height as needed
        return imageView;
    }

    @FXML
    void handleAddItemButton(ActionEvent event) {
        if (shopTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Item not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the item you want to add to your wishlist!");
            alert.showAndWait();
        } else {
            DTOItem addedItem = shopTableView.getSelectionModel().getSelectedItem();
            itemList.remove(addedItem);
            int itemID = addedItem.getItemid();
            String stringUniqueUserName = Client.getCurrentUser().getUserUniqueName();

            DTOWishlist_Items dTOWishlist_Items = new DTOWishlist_Items(stringUniqueUserName, itemID);
            System.out.println(stringUniqueUserName + itemID);

            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("ADDITEM", dTOWishlist_Items);

                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // Handle the response from the server
                    Response<DTOItem> responseAddedItem = Client.getResponse();
                    Platform.runLater(() -> {
                        if (responseAddedItem != null && responseAddedItem.isSuccess()) {
                            // Registration successful, load login scene
                            Alert alert = new Alert(AlertType.INFORMATION);

                            alert.setTitle("Adding Item Status");
                            alert.setHeaderText(null); // No header text
                            alert.setContentText("Added Successfully");

                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(AlertType.INFORMATION);

                            alert.setTitle("Adding Item Status");
                            alert.setHeaderText(null); // No header text
                            alert.setContentText("Failed to Add The Item, Try Again Later");

                            alert.showAndWait();
                        };
                    });
                }
            }.start();
        }

    }
}
