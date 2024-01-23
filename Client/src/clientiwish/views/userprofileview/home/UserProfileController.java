/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.views.userprofileview.home;

import clientCommunication.Client;
import clientiwish.models.DTOContributionList;
import clientiwish.models.DTOItem;
import clientiwish.models.DTONotification;
import clientiwish.models.DTOUser;
import clientiwish.views.userprofileview.cart.CartController;
import clientiwish.views.userprofileview.contribution.ContributionController;
import clientiwish.views.userprofileview.friendrequest.FriendRequestController;
import clientiwish.views.userprofileview.friendslist.FriendsListController;
import clientiwish.views.userprofileview.notification.NotificationController;
import clientiwish.views.userprofileview.pendinglist.PendingListController;
import clientiwish.views.userprofileview.shop.ShopController;
import clientiwish.views.userprofileview.usersearch.UserSearchController;
import clientiwish.views.userprofileview.wishlist.WishlistController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    private AnchorPane viewAnchorPane;
    private ImageView iconImageView;

    @FXML
    private TextField searchTextField;

    private ObservableList<String> data ;

    @FXML
    private ListView<String> searchListView;

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

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ImageView contributionIcon;
    @FXML
    private ImageView cartIcon;
    @FXML
    private ImageView notificationView;
    

    public ImageView getUserImage() {
        return userImage;
    }

    public Label getLabelUserName() {
        return labelUserName;
    }

    public void setLabelUserName(Label labelUserName) {
        this.labelUserName = labelUserName;
    }

    public Label getLabelUserUniqueName() {
        return labelUserUniqueName;
    }

    public Label getLabelViewName() {
        return labelViewName;
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

    public void setTextBalance(String txet) {
        labelUserBalance.setText(txet);
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
    private Label labelUserBalance;
    private Label labelViewName;

    public AnchorPane getViewAnchorPane() {
        return viewAnchorPane;
    }
    @FXML
    private Button bttnRecharge;
    @FXML
    private Label labelUserDOB;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        searchListView.visibleProperty().bind(searchTextField.textProperty().isNotEmpty());
        searchListView.setItems(data);
        searchListView.getSelectionModel().selectedItemProperty().addListener(this::selectionChanged);
        //viewHome(null);

    }

    @FXML
    private void viewHome(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/shop/Shop.fxml"));
            AnchorPane newAnchorPane = loader.load();

           // Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/homeyellow.png"));
            //iconImageView.setImage(newImage);
            //labelViewName.setText("My Shop");

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
                                viewAnchorPane.getChildren().setAll(newAnchorPane);
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

            //Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/wishlistyellow.png"));
            //iconImageView.setImage(newImage);
            //labelViewName.setText("My Wishlist");

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
                                viewAnchorPane.getChildren().setAll(newAnchorPane);
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

            //Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/friendslistyellow.png"));
            //iconImageView.setImage(newImage);
            //labelViewName.setText("My Friend List");

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
                                viewAnchorPane.getChildren().setAll(newAnchorPane);
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

            //Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/friendrequestyellow.png"));
            //iconImageView.setImage(newImage);
            //labelViewName.setText("My Friend Requests");

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
                                viewAnchorPane.getChildren().setAll(newAnchorPane);
                                friendRequestController.setFriendsList(friends);
                            } else {
                                System.out.println("FriendRequestController is null.");
                            }
                        }
                    });

                }
            }.start();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    /*
    private void viewNotification(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/notification/Notification.fxml"));
            AnchorPane newAnchorPane = loader.load();

            //Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/notificationyellow.png"));
            //iconImageView.setImage(newImage);
            //labelViewName.setText("My Notifications");

            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("NOTIFICATION", Client.getCurrentUser().getUserUniqueName());
                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Platform.runLater(() -> {
                        Response<ArrayList<DTONotification>> response = Client.getResponse();
                        ArrayList<DTONotification> notifications = response.getData();
                        if (response != null && response.isSuccess()) {
                            System.out.println("1: " + notifications);
                            NotificationController notificationController = loader.getController();
                            if ( notificationController != null) {
                                viewAnchorPane.getChildren().setAll(newAnchorPane);
                                 notificationController.setNotificationItems(notifications);
                            } else {
                                System.out.println("PendingListController is null.");
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
    */

    @FXML
    void handleSearchTextField(KeyEvent event) {

        new Thread() {
            @Override
            public void run() {
                Client.updateResponse(null);
                Client.sendRequest("SEARCHLIST", Client.getCurrentUser().getUserUniqueName());
                while (Client.getResponse() == null) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Handle the response from the server
                Response<ArrayList<String>> responseSearchList = Client.getResponse();
                Platform.runLater(() -> {

                    ArrayList<String> userSearchList = responseSearchList.getData();
                    if (responseSearchList != null && responseSearchList.isSuccess()) {

                        data = FXCollections.observableArrayList();
                        data.addAll(userSearchList);
                        ObservableList<String> filteredData = FXCollections.observableArrayList();

                        for (String item : data) {
                            if (item.toLowerCase().contains(searchTextField.getText().toLowerCase())) {
                                filteredData.add(item);
                            }
                        }
                        searchListView.setItems(filteredData);
                    } else {
                        System.out.println("search list is null.");
                    }

                });
            }
        }.start();

        // Replace the content in centerAnchorPane
    }

    private void selectionChanged(ObservableValue<? extends String> Observable, String oldVal, String newVal) {
        if (newVal != null) {
            try {
                String selectedFriend = searchListView.getSelectionModel().getSelectedItem();
                //String selectedFriend = "emad";
                System.out.println(selectedFriend);

                DTOUser selectedUser = new DTOUser(selectedFriend);
                System.out.println("selected user" + selectedUser.getUserUniqueName());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/usersearch/UserSearch.fxml"));
                AnchorPane newAnchorPane = loader.load();
                viewAnchorPane.getChildren().setAll(newAnchorPane);

                //Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/search.png"));
                //iconImageView.setImage(newImage);
                //labelViewName.setText("User Search");

                new Thread() {
                    @Override
                    public void run() {
                        Client.updateResponse(null);
                        Client.sendRequest("USERSEARCHED", selectedUser);
                        System.out.println("begining run method " + selectedUser.getUserUniqueName());
                        while (Client.getResponse() == null) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        // Handle the response from the server
                        Response<ArrayList<DTOUser>> responseSearchedUser = Client.getResponse();
                        Platform.runLater(() -> {

                            ArrayList<DTOUser> userSearchList = responseSearchedUser.getData();
                            if (responseSearchedUser != null && responseSearchedUser.isSuccess()) {
                                System.out.println("1: " + userSearchList);
                                UserSearchController userSearchController = loader.getController();
                                if (userSearchController != null && userSearchList != null) {
                                    userSearchController.updateSearchResults(userSearchList);
                                } else {
                                    System.out.println("search result is null.");
                                }
                            }
                        });
                    }
                }.start();
            } catch (IOException ex) {
                Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("No item selected");
        }
    }

    @FXML
    public void uploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(bttnUserImage.getScene().getWindow());
        if (selectedFile != null) {
            try (FileInputStream fis = new FileInputStream(selectedFile)) {
                
                byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());
                Image image = new Image(selectedFile.toURI().toString());
                userImage.setImage(image);
                
                new Thread() {
                    @Override
                    public void run() {
                        Client.updateResponse(null);
                        Client.sendRequest("UPLOAD_IMAGE", new DTOUser(Client.getCurrentUser().getUserUniqueName(), imageBytes));
                        while (Client.getResponse() == null) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        

                    }
                }.start();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    

    /*
    @FXML
    public void uploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(bttnUserImage.getScene().getWindow());
        if (selectedFile != null) {
            try (FileInputStream fis = new FileInputStream(selectedFile)) {
                
                byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());
                Image image = new Image(selectedFile.toURI().toString());
                userImage.setImage(image);
                
                new Thread() {
                    @Override
                    public void run() {
                        Client.updateResponse(null);
                        Client.sendRequest("UPLOAD_IMAGE", new DTOUser(Client.getCurrentUser().getUserUniqueName(), imageBytes));
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
                                    viewAnchorPane.getChildren().setAll(newAnchorPane);
                                    friendRequestController.setFriendsList(friends);
                                } else {
                                    System.out.println("FriendRequestController is null.");
                                }
                            }
                        });

                    }
                }.start();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
     */
    @FXML
    private void editProfile(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/editprofile/editprofile.fxml"));
            AnchorPane newAnchorPane = loader.load();
            //labelViewName.setText("Edit my profile");
            viewAnchorPane.getChildren().setAll(newAnchorPane);

        } catch (IOException ex) {
            Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewPendingList(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/pendinglist/PendingList.fxml"));
            AnchorPane newAnchorPane = loader.load();

            //Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/deadline.png"));
            //iconImageView.setImage(newImage);
            //labelViewName.setText("My Pending List");

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
                                viewAnchorPane.getChildren().setAll(newAnchorPane);
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
    private void logOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/loginview/Login.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void recharageBalance(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/editbalance/EditBalance.fxml"));
            AnchorPane newAnchorPane = loader.load();

            //Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/balance.png"));
            //iconImageView.setImage(newImage);
            //labelViewName.setText("Edit Balance");

            // Replace the content in centerAnchorPane
            viewAnchorPane.getChildren().setAll(newAnchorPane);

            // You can get the controller of the new AnchorPane if needed
            // NewAnchorPaneController newController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    public void setviewAnchorPane(AnchorPane ap) {
        viewAnchorPane.getChildren().setAll(ap);
    }

    public void seticonImageView(Image st) {
        iconImageView.setImage(st);
    }

    public void setlabelViewName(String st) {
        labelViewName.setText(st);
    }

    @FXML
    private void viewContributions(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/contribution/Contribution.fxml"));
            AnchorPane newAnchorPane = loader.load();
            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("CONTRIBUTIONLIST", Client.getCurrentUser().getUserUniqueName());
                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Platform.runLater(() -> {
                        Response<ArrayList<DTOContributionList>> response = Client.getResponse();
                        ArrayList<DTOContributionList> completeContribution = response.getData();
                        if (response != null && response.isSuccess()) {
                            System.out.println("1: " + completeContribution);
                            ContributionController contributionController = loader.getController();
                            if ( contributionController != null) {
                                viewAnchorPane.getChildren().setAll(newAnchorPane);
                                 contributionController.setContributionItems(completeContribution);
                            } else {
                                System.out.println("PendingListController is null.");
                            }
                        }
                    });

                }
            }.start();
            
        } catch (IOException ex) {
            Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewCart(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/cart/Cart.fxml"));
            AnchorPane newAnchorPane = loader.load();
            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("CART", Client.getCurrentUser().getUserUniqueName());
                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Platform.runLater(() -> {
                        Response<ArrayList<DTOItem>> response = Client.getResponse();
                        ArrayList<DTOItem> cartItems = response.getData();
                        if (response != null && response.isSuccess()) {
                            System.out.println("1: " + cartItems);
                            CartController cartController = loader.getController();
                            if ( cartController != null) {
                                viewAnchorPane.getChildren().setAll(newAnchorPane);
                                 cartController.setItems(cartItems);
                            } else {
                                System.out.println("PendingListController is null.");
                            }
                        }
                    });

                }
            }.start();
            
        } catch (IOException ex) {
            Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewNotificationTable(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/notification/Notification.fxml"));
            AnchorPane newAnchorPane = loader.load();

            //Image newImage = new Image(getClass().getResourceAsStream("/clientiwish/views/resources/userprofile/notificationyellow.png"));
            //iconImageView.setImage(newImage);
            //labelViewName.setText("My Notifications");

            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("NOTIFICATION", Client.getCurrentUser().getUserUniqueName());
                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Platform.runLater(() -> {
                        Response<ArrayList<DTONotification>> response = Client.getResponse();
                        ArrayList<DTONotification> notifications = response.getData();
                        if (response != null && response.isSuccess()) {
                            System.out.println("1: " + notifications);
                            NotificationController notificationController = loader.getController();
                            if ( notificationController != null) {
                                viewAnchorPane.getChildren().setAll(newAnchorPane);
                                 notificationController.setNotificationItems(notifications);
                            } else {
                                System.out.println("PendingListController is null.");
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

}
