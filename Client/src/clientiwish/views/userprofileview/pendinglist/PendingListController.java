package clientiwish.views.userprofileview.pendinglist;

import clientCommunication.Client;
import clientiwish.models.DTOFriendRequest;
import clientiwish.models.DTOUser;
import clientiwish.views.userprofileview.friendslist.FriendsListController;
import java.io.IOException;
import java.sql.Date;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sharedlibraries.Response;

public class PendingListController {

    @FXML
    private TableView<DTOUser> fRequestTableView;
    @FXML
    private TableColumn<DTOUser, String> friendName;
    @FXML
    private TableColumn<DTOUser, String> friendEmail;
    @FXML
    private TableColumn<DTOUser, Date> FriendBirthdate;
    @FXML
    private AnchorPane viewPendingList;
    @FXML
    private Button btnRemoveRequest;
    private Label removeSuccessAlert;

    private Stage stage;
    private Scene scene;
    private Parent root;

    ObservableList<DTOUser> pendingList = FXCollections.observableArrayList();
    @FXML
    private ImageView iconImageView;
    @FXML
    private Label labelViewName;

    public void setFriendsList(ArrayList<DTOUser> friends) {
        System.out.println("2: " + friends);
        pendingList.setAll(friends);
        fRequestTableView.setItems(pendingList);

        friendName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserUniqueName()));
        friendEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        FriendBirthdate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDob()));
    }

    @FXML
    private void removePendingRequest(ActionEvent event) {
        if(fRequestTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the pending request you want to remove!");
            alert.showAndWait();
        }
        else{
        DTOUser selectedUser = fRequestTableView.getSelectionModel().getSelectedItem();
        String selectedUserName = selectedUser.getUserUniqueName();
        String currentUserName = Client.getCurrentUser().getUserUniqueName();
        pendingList.remove(selectedUser);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/pendinglist/PendingList.fxml"));
                AnchorPane newAnchorPane = loader.load();
                new Thread() {
                    @Override
                    public void run() {
                        Client.updateResponse(null);
                        Client.sendRequest("REMOVINGPENDINGREQUEST", new DTOFriendRequest(currentUserName, selectedUserName));

                        while (Client.getResponse() == null) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        Platform.runLater(() -> {
                            Response<ArrayList<DTOUser>> responseRemoveRequest = Client.getResponse();
                            ArrayList<DTOUser> updatePendingList = responseRemoveRequest.getData();
                            if (responseRemoveRequest != null && responseRemoveRequest.isSuccess()) {
                                PendingListController pendingListController = loader.getController();
                                if (pendingListController != null) {
                                    pendingListController.setFriendsList(updatePendingList);
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                                    alert.setTitle("Remove Pending Request Status");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Pending request removed successfully");

                                    alert.showAndWait();

                                } else {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                                    alert.setTitle("Remove Pending Request Status");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Failed to remove pending request, Try Again Later");

                                    alert.showAndWait();
                                }
                            }
                        });

                    }
                }.start();
            } catch (IOException ex) {
                Logger.getLogger(FriendsListController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } 

    }

}
