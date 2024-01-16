/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.views.userprofileview.pendinglist;

import clientiwish.models.DTOUser;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Friends
 */
public class PendingListController{

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
    
    ObservableList<DTOUser> pendingList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    
     public void setFriendsList(ArrayList<DTOUser> friends) {
        System.out.println("2: " + friends);
        pendingList.setAll(friends);
         fRequestTableView.setItems(pendingList);

        friendName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserUniqueName()));
        friendEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        FriendBirthdate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDob()));
    
    }
     
}
