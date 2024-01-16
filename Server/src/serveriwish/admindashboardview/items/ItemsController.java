
package serveriwish.admindashboardview.items;

import dataaccesslayer.DAOItem;
import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import models.DTOItem;

public class ItemsController implements Initializable {

    @FXML
    private AnchorPane ItemsAP;
    @FXML
    private Button delitem;
    @FXML
    private TableView<DTOItem> itemsTable;
    @FXML
    private TableColumn<DTOItem, Integer> T_itemid;
    @FXML
    private TableColumn<DTOItem, String> T_itemname;
    @FXML
    private TableColumn<DTOItem, String> T_itemdesc;
    @FXML
    private TableColumn<DTOItem, Integer> T_price;
    

    ObservableList<DTOItem> itemList = FXCollections.observableArrayList();
    
    public void setItemList(ArrayList<DTOItem> items) {
        itemList.setAll(items);

    }
    /**
     * Initializes the controller class.s
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Set the items in the table
        itemsTable.setItems(itemList);
        
         // Set up the cell value factories without lambda expressions
         T_itemid.setCellValueFactory(cellData ->{
         return new SimpleIntegerProperty(cellData.getValue().getItemid()).asObject();
         });
         
         T_itemname.setCellValueFactory(cellData ->{
         return new SimpleStringProperty(cellData.getValue().getItemname());
         });
         
         T_itemdesc.setCellValueFactory(cellData ->{
         return new SimpleStringProperty(cellData.getValue().getItemdescription());
         });
         
         T_price.setCellValueFactory(cellData ->{
         return new SimpleIntegerProperty(cellData.getValue().getItemprice()).asObject();
         });
         
         
    }    


    @FXML
    private void btnDeleteItem(ActionEvent event) {
        DTOItem selectedItem = itemsTable.getSelectionModel().getSelectedItem();
        itemList.remove(selectedItem);
        DAOItem del = new DAOItem();
        del.delItem(selectedItem);
    }
    
}
