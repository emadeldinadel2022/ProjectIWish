/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.views.userprofileview.notification;

import clientiwish.models.Contributors;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class NotificationController implements Initializable {

    @FXML
    private TableView<Contributors> notifTableView;
    @FXML
    private TableColumn<Contributors, String> itemName;
    @FXML
    private TableColumn<Contributors, Integer> itemPrice;
    @FXML
    private TableColumn<Contributors, String> NotifContributors;
    
    ObservableList<Contributors> contributeList = FXCollections.observableArrayList();
    ArrayList<String> arr1 = new ArrayList<>();
    ArrayList<String> arr2 = new ArrayList<>();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for(int i=1; i<6 ;i++){
            arr1.add("conn"+i);
        }
         for(int i=1; i<6 ;i++){
            arr2.add("conn"+i);
        }   
        Contributors c1 =  new Contributors("c1", 10,arr1);
        Contributors c2 =  new Contributors("c2", 10,arr2);
            
       contributeList.add(c1);
       contributeList.add(c2);
       
       
       itemName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getItemName());
        });
       
       itemPrice.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemPrice()).asObject();
        });
      
       

      NotifContributors.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getContributors());
        });
      



        notifTableView.setItems(contributeList);
    }    
    
}
