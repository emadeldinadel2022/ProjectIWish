/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveriwish.admindashboardview.users;

import dataaccesslayer.DAOUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.DTOUser;

/**
 * FXML Controller class
 *
 * @author Sherif
 */
public class UsersController implements Initializable {
    @FXML
    private Button del_user;
    @FXML
    private TableView<DTOUser> usersTables;
    @FXML
    private TableColumn<DTOUser, String> username;
    @FXML
    private TableColumn<DTOUser, String> email;
    @FXML
    private TableColumn<DTOUser, String> name;
    @FXML
    private TableColumn<DTOUser, String> gender;
    @FXML
    private TableColumn<DTOUser, Integer> balance;
    @FXML
    void btnDelUser(ActionEvent event) throws SQLException {
            DTOUser selectedUser = usersTables.getSelectionModel().getSelectedItem();
            userList.remove(selectedUser);
            DAOUser del = new DAOUser();
            del.delUser(selectedUser);
    }
    ObservableList<DTOUser> userList = FXCollections.observableArrayList();
    
    public void setItemList(ArrayList<DTOUser> users) {
        userList.setAll(users);
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            usersTables.setItems(userList);
            
        username.setCellValueFactory(cellData ->{
         return new SimpleStringProperty(cellData.getValue().getUserUniqueName());
         });
        email.setCellValueFactory(cellData ->{
         return new SimpleStringProperty(cellData.getValue().getEmail());
         });
        name.setCellValueFactory(cellData ->{
         return new SimpleStringProperty(cellData.getValue().getName());
         });
        gender.setCellValueFactory(cellData ->{
         return new SimpleStringProperty(cellData.getValue().getGender());
         });
        balance.setCellValueFactory(cellData ->{
         return new SimpleIntegerProperty(cellData.getValue().getBalance()).asObject();
         });
        
        
    }    
    
}
