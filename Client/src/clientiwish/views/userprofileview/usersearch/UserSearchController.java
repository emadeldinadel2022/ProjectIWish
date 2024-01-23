/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.views.userprofileview.usersearch;

import clientCommunication.Client;
import clientiwish.models.DTOFriendRequest;
import clientiwish.models.DTOUser;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sharedlibraries.Response;

/**
 * FXML Controller class
 *
 * @author Mostafa Mohamed
 */
public class UserSearchController implements Initializable {

    @FXML
    private ImageView iconImageView;
    @FXML
    private Label labelViewName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private AnchorPane searchAnchorPane;

    @FXML
    private TableView<DTOUser> searchTableView;

    @FXML
    private TableColumn<DTOUser, String> userNameColumn;

    @FXML
    private TableColumn<DTOUser, String> emailColumn;

    @FXML
    private TableColumn<DTOUser, Date> birthdateColumn;

    ObservableList<DTOUser> searchedUser = FXCollections.observableArrayList();

    @FXML
    private Button addFriendButton;

    @FXML
    void handleAddFriendButton(ActionEvent event) {
        if (searchTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sending Friend Request Status");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Please select the user you want to add!");
            alert.showAndWait();
        } else {
            DTOUser friendRequest = searchTableView.getSelectionModel().getSelectedItem();
            searchedUser.remove(friendRequest);

            String sFRequestUniquename = friendRequest.getUserUniqueName();
            String currentUserUniqueUserName = Client.getCurrentUser().getUserUniqueName();

            DTOFriendRequest dTOFriendrequest = new DTOFriendRequest(currentUserUniqueUserName, sFRequestUniquename);
            System.out.println(sFRequestUniquename + currentUserUniqueUserName);

            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("SENDFRIENDREQUEST", dTOFriendrequest);

                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // Handle the response from the server
                    Response<DTOUser> responseFriendRequest = Client.getResponse();
                    Platform.runLater(() -> {
                        if (responseFriendRequest != null && responseFriendRequest.isSuccess()) {
                            UserSearchController userSearchController = new UserSearchController();

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);

                            alert.setTitle("Sending The Friend Request Status");
                            alert.setHeaderText(null); // No header text
                            alert.setContentText("Sent Successfully");

                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);

                            alert.setTitle("Sending Friend Request Status");
                            alert.setHeaderText(null); // No header text
                            alert.setContentText("Already a Friend");

                            alert.showAndWait();
                        };

                    });
                }
            }.start();
        }
    }

    public void updateSearchResults(ArrayList<DTOUser> searchResults) {
        searchTableView.getItems().clear();

        searchedUser.addAll(searchResults);
        searchTableView.setItems(searchedUser);
        // searchTableView.getItems().addAll(searchResults);

        userNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getUserUniqueName()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        birthdateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDob()));

    }

}
