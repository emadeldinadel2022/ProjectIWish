package clientiwish.views.userprofileview.friendslist;

import clientCommunication.Client;
import clientiwish.models.DTOFriendlist;
import clientiwish.models.DTOItem;
import clientiwish.models.DTOUser;
import clientiwish.views.userprofileview.editbalance.EditBalanceController;
import clientiwish.views.userprofileview.friendwishlist.FriendWishListController;
import clientiwish.views.userprofileview.home.UserProfileController;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sharedlibraries.Response;

public class FriendsListController {

    @FXML
    private AnchorPane viewFriendList;
    @FXML
    private TableView<DTOUser> fListTableView;
    @FXML
    private TableColumn<DTOUser, String> FriendName;
    @FXML
    private TableColumn<DTOUser, String> friendEmail;
    @FXML
    private TableColumn<DTOUser, Date> friendBirthdate;
    @FXML
    private Button deleteFriendBttn;

    ObservableList<DTOUser> friendsList = FXCollections.observableArrayList();
    @FXML
    private Button viewWishListBttn;
    ArrayList<DTOUser> updatefriends;
    DTOUser selectedUser;

    public DTOUser getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(DTOUser selectedUser) {
        this.selectedUser = selectedUser;
    }

    public void setFriendsList(ArrayList<DTOUser> friends) {
        System.out.println("2: " + friends);
        friendsList.setAll(friends);
        fListTableView.setItems(friendsList);

        FriendName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserUniqueName()));
        friendEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        friendBirthdate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDob()));

    }

    public ArrayList<DTOUser> getUpdatefriends() {
        return updatefriends;
    }

    public void setUpdatefriends(ArrayList<DTOUser> updatefriends) {
        this.updatefriends = updatefriends;
    }

    @FXML
    public void deleteFriend(ActionEvent event) {

        if (fListTableView.getSelectionModel().getSelectedItem() != null) {
            selectedUser = fListTableView.getSelectionModel().getSelectedItem();
            String selectedUserName = selectedUser.getUserUniqueName();
            String currenrUserName = Client.getCurrentUser().getUserUniqueName();
            friendsList.remove(selectedUser);
            try {
                DTOFriendlist dtoFriend = new DTOFriendlist(currenrUserName, selectedUserName);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/friendslist/FriendsList.fxml"));
                AnchorPane newAnchorPane = loader.load();
                new Thread() {
                    @Override
                    public void run() {
                        Client.updateResponse(null);
                        Client.sendRequest("DELETEFRIEND", dtoFriend);
                        while (Client.getResponse() == null) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        Platform.runLater(() -> {
                            Response<ArrayList<DTOUser>> response = Client.getResponse();
                            updatefriends = response.getData();
                            if (response != null && response.isSuccess()) {
                                System.out.println("1: " + updatefriends);
                                FriendsListController friendsListController = loader.getController();
                                if (friendsListController != null) {
                                    friendsListController.setFriendsList(updatefriends);
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                                    alert.setTitle("Remove Friend Status");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Friendship with " + selectedUserName + " removed successfully");

                                    alert.showAndWait();
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                                    alert.setTitle("Remove Friend Status");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Failed to remove friendship, Try Again Later");

                                    alert.showAndWait();
                                }
                            }
                        });

                    }
                }.start();
            } catch (IOException ex) {
                Logger.getLogger(FriendsListController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Friend not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select friend you want to delete!");
            alert.showAndWait();

        }
    }

    @FXML
    public void viewFriendWishList(ActionEvent event) {

        if (fListTableView.getSelectionModel().getSelectedItem() != null) {
            selectedUser = fListTableView.getSelectionModel().getSelectedItem();
            String selectedUserName = selectedUser.getUserUniqueName();
            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("FRIENDWISHLIST", selectedUserName);
                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Platform.runLater(() -> {
                        Response<ArrayList<DTOItem>> responseWishlist = Client.getResponse();
                        ArrayList<DTOItem> itemsWishlist = responseWishlist.getData();
                        //Client.updateResponse(responseWishlist);
                        if (responseWishlist != null && responseWishlist.isSuccess()) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/home/UserProfile.fxml"));
                            Parent root;
                            try {
                                root = loader.load();
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                                /*iconImageView.setImage(newImage);
                                labelViewName.setText("Edit Balance");*/
                            } catch (IOException ex) {
                                Logger.getLogger(EditBalanceController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            UserProfileController userProfileController = loader.getController();
                            System.out.println("1: " + itemsWishlist);
                            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/friendwishlist/FriendWishlist.fxml"));
                            AnchorPane newAnchorPane;
                            try {
                                newAnchorPane = loader2.load();
                                userProfileController.setviewAnchorPane(newAnchorPane);
                                userProfileController.getLabelUserName().setText(Client.getCurrentUser().getName());
                                userProfileController.getLabelUserUniqueName().setText(Client.getCurrentUser().getUserUniqueName());
                                userProfileController.getLabelUserEmail().setText(Client.getCurrentUser().getEmail());
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String formattedDate = dateFormat.format(Client.getCurrentUser().getDob());
                                userProfileController.getLabelUserDOB().setText(formattedDate);
                                FriendWishListController fwc = loader2.getController();
                                fwc.getLabelViewName().setText("Friend wishlist: " + selectedUserName);

                            } catch (IOException ex) {
                                Logger.getLogger(EditBalanceController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            FriendWishListController friendWishListController = loader2.getController();
                            if (friendWishListController != null) {
                                friendWishListController.setUniqueNameSelectedUser(selectedUserName);
                                friendWishListController.setItemList(itemsWishlist);
                            } else {
                                System.out.println("FriendWishListController is null.");
                            }
                        }
                    });

                }
            }.start();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Friend not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select one of your friends to show his/her wishlist");
            alert.showAndWait();

        }
    }

}
