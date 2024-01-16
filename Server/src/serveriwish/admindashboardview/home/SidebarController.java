/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveriwish.admindashboardview.home;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.DTOItem;
import models.DTOUser;
import dataaccesslayer.DAOItem;
import dataaccesslayer.DAOUser;
import serveriwish.admindashboardview.items.ItemsController;
import serveriwish.admindashboardview.users.UsersController;

/**
 *
 * @author Sherif
 */
public class SidebarController implements Initializable {
    

    @FXML
    private BorderPane bp;

    @FXML
    private Button btndashboard;

    @FXML
    private Button btnuser;

    @FXML
    private Button btnItems;

    @FXML
    private AnchorPane mainAP;
    
    public ArrayList<DTOItem> arrItems;
    public ArrayList<DTOUser> arrUser;

    @FXML
    void clickItems(ActionEvent event) {
       try {

           FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/items/Items.fxml"));
           AnchorPane anchorpane = loader.load();
           mainAP.getChildren().setAll(anchorpane);
           
           ItemsController itemsController = loader.getController();
           DAOItem itemObj = new DAOItem();
            arrItems = itemObj.getItems();
            itemsController.setItemList(arrItems);
            arrItems.clear();
           
       } catch (IOException ex) {
           Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }
    
    
    @FXML
    void clickDashboard(ActionEvent event) {
       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/Dashboard.fxml"));
           AnchorPane anchorpane = loader.load();
           mainAP.getChildren().setAll(anchorpane);
           
       } catch (IOException ex) {
           Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    @FXML
    void clickUsers(ActionEvent event) {
       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/users/Users.fxml"));
           AnchorPane anchorpane = loader.load();
           mainAP.getChildren().setAll(anchorpane);
           UsersController userController = loader.getController();
           DAOUser userObj = new DAOUser();
           try {
               arrUser = userObj.getUsers();
               userController.setItemList(arrUser);
               arrUser.clear();
               
           } catch (SQLException ex) {
               Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
           }
       } catch (IOException ex) {
           Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/Dashboard.fxml"));
           AnchorPane anchorpane = loader.load();
           mainAP.getChildren().setAll(anchorpane);
       } catch (IOException ex) {
           Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

}
