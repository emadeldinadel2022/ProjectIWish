/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.views.userprofileview.shop;

import clientiwish.models.DTOItem;
import clientiwish.models.DTOItemView;
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

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ShopController {

    @FXML
    private TableView<DTOItem> shopTableView;

    //  @FXML
    //  private TableColumn<?, ?> shopImage;
    @FXML
    private TableColumn<DTOItem, String> shopName;

    @FXML
    private TableColumn<DTOItem, String> shopDescrip;

    @FXML
    private TableColumn<DTOItem, Integer> shopPrice;

    //  @FXML
    //  private TableColumn<?, ?> shopButton;
    ObservableList<DTOItem> itemList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<DTOItem, Integer> shopID;

   

    public void setItemList(ArrayList<DTOItem> items) {
        System.out.println("2: " + items);
        itemList.setAll(items);
        
        shopTableView.setItems(itemList);

        shopID.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemid()).asObject();
        });

        // Set up the cell value factories without lambda expressions
        shopName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getItemname());
        });

        shopDescrip.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getItemdescription());
        });

        shopPrice.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemprice()).asObject();
        });


    }

}
