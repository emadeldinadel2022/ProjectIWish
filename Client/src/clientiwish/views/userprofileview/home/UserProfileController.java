/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.views.userprofileview.home;

import clientCommunication.Client;
import clientiwish.models.DTOItem;
import clientiwish.models.DTOItemView;
import clientiwish.models.DTOUser;
import clientiwish.views.userprofileview.*;
import clientiwish.views.userprofileview.friendrequest.FriendRequestController;
import clientiwish.views.userprofileview.friendslist.FriendsListController;
import clientiwish.views.userprofileview.pendinglist.PendingListController;
import clientiwish.views.userprofileview.shop.ShopController;
import clientiwish.views.userprofileview.wishlist.WishlistController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import sharedlibraries.Response;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class UserProfileController implements Initializable {

    @FXML
    private Button homeButton;
    @FXML
    private Button wishlistButton;
    @FXML
    private Button friendlistButton;
    @FXML
    private Button friendRequetButton;
    @FXML
    private Button notificationButton;
    @FXML
    private AnchorPane viewAnchorPane;
    @FXML
    private ImageView iconImageView;

    @FXML
    private TextField searchTextField;

    private ObservableList<String> data = FXCollections.observableArrayList(
            "mostafa", "sherif", "emad", "hatem", "nour"
    );

    @FXML
    private ListView<String> searchListView;

    private ArrayList<DTOItemView> itemWishlist;
    @FXML
    private Button bttnUserImage;
    @FXML
    private ImageView userImage;
    @FXML
    private Label labelUserName;
    @FXML
    private Label labelUserUniqueName;
    @FXML
    private Label labelUserEmail;
    @FXML
    private Button bttnEditProfile;

    public Label getLabelUserName() {
        return labelUserName;
    }

    public void setLabelUserName(Label labelUserName) {
        this.labelUserName = labelUserName;
    }

    public Label getLabelUserUniqueName() {
        return labelUserUniqueName;
    }

    public void setLabelUserUniqueName(Label labelUserUniqueName) {
        this.labelUserUniqueName = labelUserUniqueName;
    }

    public Label getLabelUserEmail() {
        return labelUserEmail;
    }

    public void setLabelUserEmail(Label labelUserEmail) {
        this.labelUserEmail = labelUserEmail;
    }

    public Label getLabelUserBalance() {
        return labelUserBalance;
    }

    public void setLabelUserBalance(Label labelUserBalance) {
        this.labelUserBalance = labelUserBalance;
    }

    public Label getLabelUserDOB() {
        return labelUserDOB;
    }

    public void setLabelUserDOB(Label labelUserDOB) {
        this.labelUserDOB = labelUserDOB;
    }
    @FXML
    private Button bttnPendingList;
    @FXML
    private Button bttnLogOut;
    @FXML
    private Label labelUserBalance;
    @FXML
    private Label labelViewName;
    @FXML
    private ScrollBar scrollBar;
    @FXML
    private Button bttnRecharge;
    @FXML
    private Label labelUserDOB;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //for the search bar handling
        /*
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/shop/Shop.fxml"));
            AnchorPane newAnchorPane = loader.load();

            Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/homeyellow.png"));
            iconImageView.setImage(newImage);
            labelViewName.setText("My Shop");

            // Replace the content in centerAnchorPane
            viewAnchorPane.getChildren().setAll(newAnchorPane);

            // You can get the controller of the new AnchorPane if needed
            // NewAnchorPaneController newController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
        searchListView.visibleProperty().bind(searchTextField.textProperty().isNotEmpty());
        searchListView.setItems(data);
        */
        viewHome(null);

    }

    @FXML
    private void viewHome(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/shop/Shop.fxml"));
            AnchorPane newAnchorPane = loader.load();

            Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/homeyellow.png"));
            iconImageView.setImage(newImage);
            labelViewName.setText("My Shop");

            viewAnchorPane.getChildren().setAll(newAnchorPane);

            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("SHOP", Client.getCurrentUser().getUserUniqueName());
                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Platform.runLater(() -> {
                        Response<ArrayList<DTOItem>> response = Client.getResponse();
                        ArrayList<DTOItem> items = response.getData();
                        if (response != null && response.isSuccess()) {
                            System.out.println("1: " + items);
                            ShopController shopController = loader.getController();
                            if (shopController != null) {
                                shopController.setItemList(items);
                            } else {
                                System.out.println("ShopController is null.");
                            }
                        }
                    });

                }
            }.start();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void viewMywishlist(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/wishlist/Wishlist.fxml"));
            AnchorPane newAnchorPane = loader.load();

            Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/wishlistyellow.png"));
            iconImageView.setImage(newImage);
            labelViewName.setText("My Wishlist");

            // Replace the content in centerAnchorPane
            viewAnchorPane.getChildren().setAll(newAnchorPane);

            // You can get the controller of the new AnchorPane if needed
            // NewAnchorPaneController newController = loader.getController();
            
            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("WISHLIST", Client.getCurrentUser().getUserUniqueName());
                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Platform.runLater(() -> {
                        Response<ArrayList<DTOItem>> response = Client.getResponse();
                        ArrayList<DTOItem> items = response.getData();
                        if (response != null && response.isSuccess()) {
                            System.out.println("1: " + items);
                            WishlistController wishlistController = loader.getController();
                            if (wishlistController != null) {
                                wishlistController.setItemList(items);
                            } else {
                                System.out.println("WishlistController is null.");
                            }
                        }
                    });

                }
            }.start();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    @FXML
    private void viewMyFriendList(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/friendslist/FriendsList.fxml"));
            AnchorPane newAnchorPane = loader.load();

            Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/friendslistyellow.png"));
            iconImageView.setImage(newImage);
            labelViewName.setText("My Friend List");

            // Replace the content in centerAnchorPane
            viewAnchorPane.getChildren().setAll(newAnchorPane);

            // You can get the controller of the new AnchorPane if needed
            // NewAnchorPaneController newController = loader.getController();
            
            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("FRIENDLIST", Client.getCurrentUser().getUserUniqueName());
                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Platform.runLater(() -> {
                        Response<ArrayList<DTOUser>> response = Client.getResponse();
                        ArrayList<DTOUser> friends = response.getData();
                        if (response != null && response.isSuccess()) {
                            System.out.println("1: " + friends);
                            FriendsListController friendsListController = loader.getController();
                            if (friendsListController != null) {
                                friendsListController.setFriendsList(friends);
                            } else {
                                System.out.println("FriendsListController is null.");
                            }
                        }
                    });

                }
            }.start();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    @FXML
    private void viewFriendRequest(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/friendrequest/FriendRequest.fxml"));
            AnchorPane newAnchorPane = loader.load();

            Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/friendrequestyellow.png"));
            iconImageView.setImage(newImage);
            labelViewName.setText("My Friend Requests");

            // Replace the content in centerAnchorPane
            viewAnchorPane.getChildren().setAll(newAnchorPane);
            
            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("FRIENDREQUEST", Client.getCurrentUser().getUserUniqueName());
                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Platform.runLater(() -> {
                        Response<ArrayList<DTOUser>> response = Client.getResponse();
                        ArrayList<DTOUser> friends = response.getData();
                        if (response != null && response.isSuccess()) {
                            System.out.println("1: " + friends);
                            FriendRequestController friendRequestController = loader.getController();
                            if (friendRequestController != null) {
                                friendRequestController.setFriendsList(friends);
                            } else {
                                System.out.println("FriendRequestController is null.");
                            }
                        }
                    });

                }
            }.start();

            // You can get the controller of the new AnchorPane if needed
            // NewAnchorPaneController newController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }

    }

    @FXML
    private void viewNotification(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/notification/Notification.fxml"));
            AnchorPane newAnchorPane = loader.load();

            Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/notificationyellow.png"));
            iconImageView.setImage(newImage);
            labelViewName.setText("My Notifications");

            // Replace the content in centerAnchorPane
            viewAnchorPane.getChildren().setAll(newAnchorPane);

            // You can get the controller of the new AnchorPane if needed
            // NewAnchorPaneController newController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }

    }

    @FXML
    void handleSearchTextField(KeyEvent event) {

        ObservableList<String> filteredData = FXCollections.observableArrayList();

        for (String item : data) {
            if (item.toLowerCase().contains(searchTextField.getText().toLowerCase())) {
                filteredData.add(item);
            }
        }
        searchListView.setItems(filteredData);

    }

    @FXML
    private void uploadImage(ActionEvent event) {
    }

    @FXML
    private void editProfile(ActionEvent event) {
    }

    @FXML
    private void viewPendingList(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/pendinglist/PendingList.fxml"));
            AnchorPane newAnchorPane = loader.load();

            Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/deadline.png"));
            iconImageView.setImage(newImage);
            labelViewName.setText("My Pending List");

            // Replace the content in centerAnchorPane
            viewAnchorPane.getChildren().setAll(newAnchorPane);
            
            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("PENDINGLIST", Client.getCurrentUser().getUserUniqueName());
                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Platform.runLater(() -> {
                        Response<ArrayList<DTOUser>> response = Client.getResponse();
                        ArrayList<DTOUser> friends = response.getData();
                        if (response != null && response.isSuccess()) {
                            System.out.println("1: " + friends);
                            PendingListController pendingListController = loader.getController();
                            if (pendingListController != null) {
                                pendingListController.setFriendsList(friends);
                            } else {
                                System.out.println("PendingListController is null.");
                            }
                        }
                    });

                }
            }.start();

            // You can get the controller of the new AnchorPane if needed
            // NewAnchorPaneController newController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    @FXML
    private void logOut(ActionEvent event) {
    }

    @FXML
    private void recharageBalance(ActionEvent event) {
    }

}
