
package serveriwish.admindashboardview.items;

import dataaccesslayer.DAOItem;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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
    @FXML
    private TableColumn<DTOItem, ImageView> T_image;
    

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
         
        T_image.setCellFactory(col -> new TableCell<DTOItem, ImageView>() {
            private final ImageView imageView = new ImageView();

            {
                setGraphic(imageView);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            @Override
            protected void updateItem(ImageView item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    imageView.setImage(null);
                } else {
                    imageView.setImage(item.getImage());
                    StackPane stackPane = new StackPane(item);
                    stackPane.setAlignment(Pos.CENTER);
                    setGraphic(stackPane);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                }
            }
        });

        T_image.setCellValueFactory(cellData -> {
            ImageView imageView = createImageView(cellData.getValue().getItemimage());
            // Center the ImageView within the cell
            return new SimpleObjectProperty<>(imageView);
        });
    }

// ...

    private ImageView createImageView(byte[] imageData) {
        if (imageData == null || imageData.length == 0) {
            return new ImageView();  // or return a default image if appropriate
        }

        Image image = new Image(new ByteArrayInputStream(imageData));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100); // Set the width as needed
        imageView.setFitHeight(100); // Set the height as needed
        return imageView;
    }
         
     


    @FXML
    private void btnDeleteItem(ActionEvent event) {
        // Get the selected item
        DTOItem selectedItem = itemsTable.getSelectionModel().getSelectedItem();

        // Check if an item is selected
        if (selectedItem == null) {
            // No item selected, show an alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Selecting an item.");
            alert.setHeaderText(null); // No header text
            alert.setContentText("Please select an item first!");
            alert.showAndWait();
            return; // Exit the method without further processing
        }

        // Remove the item from the list and database
        itemList.remove(selectedItem);
        DAOItem del = new DAOItem();
        del.delItem(selectedItem);
    }
    
}
