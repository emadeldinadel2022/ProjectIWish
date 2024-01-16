package clientiwish.views.userprofileview.wishlist;

import clientiwish.models.DTOItem;
import clientiwish.models.DTOItemView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

public class WishlistController{

    @FXML
    private TableView<DTOItem> wishlistTable;
    @FXML
    private TableColumn<DTOItem, String> itemNameCol;
    @FXML
    private TableColumn<DTOItem, String> descriptionCol;
    @FXML
    private TableColumn<DTOItem, Integer> priceCol;
    @FXML
    private TableColumn<DTOItem, Integer> reminderCol;
    
    ObservableList<DTOItem> itemList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<DTOItem, Integer> itemID;

    public void setItemList(ArrayList<DTOItem> items) {
        System.out.println("2: " + items);
        itemList.setAll(items);
        
        wishlistTable.setItems(itemList);

        itemID.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemid()).asObject();
        });

        // Set up the cell value factories without lambda expressions
        itemNameCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getItemname());
        });

        descriptionCol.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getItemdescription());
        });

        priceCol.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemprice()).asObject();
        });
        
        reminderCol.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemreminder()).asObject();
        });


    }

}
