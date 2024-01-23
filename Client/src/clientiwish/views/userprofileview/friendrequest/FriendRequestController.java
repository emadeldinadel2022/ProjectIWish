package clientiwish.views.userprofileview.friendrequest;

import clientCommunication.Client;
import clientiwish.models.DTOFriendRequest;
import clientiwish.models.DTOUser;
import clientiwish.views.userprofileview.usersearch.UserSearchController;
import java.sql.Date;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.layout.AnchorPane;
import sharedlibraries.Response;

public class FriendRequestController {

    @FXML
    private TableView<DTOUser> fRequestTableView;
    @FXML
    private TableColumn<DTOUser, String> friendName;
    @FXML
    private TableColumn<DTOUser, String> friendEmail;
    @FXML
    private TableColumn<DTOUser, Date> friendBirthdate;
    @FXML
    private AnchorPane viewFriendRequest;

    ObservableList<DTOUser> friendRequests = FXCollections.observableArrayList();
    @FXML
    private ImageView iconImageView;
    @FXML
    private Label labelViewName;
    @FXML
    private Button acceptRequestButton;
    @FXML
    private Button declineRequestButton;

    public void setFriendsList(ArrayList<DTOUser> friends) {
        System.out.println("2: " + friends);
        friendRequests.setAll(friends);
        fRequestTableView.setItems(friendRequests);

        friendName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserUniqueName()));
        friendEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        friendBirthdate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDob()));
    }

    @FXML
    void handleAcceptRequestButton(ActionEvent event) {
        if (fRequestTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the request you want to accept!");
            alert.showAndWait();

        }
        else {
        DTOUser acceptedFRequest = fRequestTableView.getSelectionModel().getSelectedItem();
        friendRequests.remove(acceptedFRequest);

        String acceptedUniquename = acceptedFRequest.getUserUniqueName();
        String currentUserUniqueUserName = Client.getCurrentUser().getUserUniqueName();

        DTOFriendRequest dTOFriendrequest = new DTOFriendRequest(acceptedUniquename, currentUserUniqueUserName);
        System.out.println(acceptedUniquename + currentUserUniqueUserName);

        new Thread() {
            @Override
            public void run() {
                Client.updateResponse(null);
                Client.sendRequest("ACCEPTFRIEND", dTOFriendrequest);

                while (Client.getResponse() == null) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Handle the response from the server
                Response<DTOUser> responseAcceptFriend = Client.getResponse();
                Platform.runLater(() -> {
                    if (responseAcceptFriend != null && responseAcceptFriend.isSuccess()) {
                        UserSearchController userSearchController = new UserSearchController();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Accepting Friend Request Status");
                        alert.setHeaderText(null); // No header text
                        alert.setContentText("Accepted Successfully");

                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Accepting Friend Request Status");
                        alert.setHeaderText(null); // No header text
                        alert.setContentText("Failed to Accept Friend, Try Again Later");

                        alert.showAndWait();
                    };

                });
            }
        }.start();
    }}

    @FXML
    void handleDeclineRequestButton(ActionEvent event) {
          if (fRequestTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the request you want to decline!");
            alert.showAndWait();

        }
        else {
        DTOUser declinedFRequest = fRequestTableView.getSelectionModel().getSelectedItem();
        friendRequests.remove(declinedFRequest);

        String declinedUniquename = declinedFRequest.getUserUniqueName();
        String currentUserUniqueUserName = Client.getCurrentUser().getUserUniqueName();

        DTOFriendRequest dTOFriendrequest = new DTOFriendRequest(declinedUniquename, currentUserUniqueUserName);
        System.out.println(declinedUniquename + currentUserUniqueUserName);

        new Thread() {
            @Override
            public void run() {
                Client.updateResponse(null);
                Client.sendRequest("DECLINEFRIEND", dTOFriendrequest);

                while (Client.getResponse() == null) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Handle the response from the server
                Response<DTOUser> responseAcceptFriend = Client.getResponse();
                Platform.runLater(() -> {
                    if (responseAcceptFriend != null && responseAcceptFriend.isSuccess()) {
                        // Registration successful, load login scene
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Declinging Friend Request Status");
                        alert.setHeaderText(null); // No header text
                        alert.setContentText("Declined Successfully");

                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Declinging Friend Request Status");
                        alert.setHeaderText(null); // No header text
                        alert.setContentText("Failed to Decline Friend, Try Again Later");

                        alert.showAndWait();
                    };

                });
            }
        }.start();

    }

}}
