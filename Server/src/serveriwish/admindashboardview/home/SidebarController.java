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
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import server.ItemScrapping;
import serveriwish.admindashboardview.DashboardController;
import serveriwish.admindashboardview.items.ItemsController;
import serveriwish.admindashboardview.scrap.ScrapController;
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
    private TextField txtfieldSearch;
    @FXML
    private Button btnItems;
    @FXML
    private Button btnSearch;
    @FXML
    private AnchorPane mainAP;

    public ArrayList<DTOItem> arrItems;
    public ArrayList<DTOUser> arrUser;
    public ArrayList<DTOItem> arrSearchItems;
    public ArrayList<DTOUser> arrSearchUser;
    String view;
    @FXML
    private Button btnscrap;

    @FXML
    void clickItems(ActionEvent event) {
        if (DashboardController.getServer() == null) {
            showAlert("Server is not running. Please start the server.");
        } else if (!DashboardController.getServer().isServerRunning()) {
            showAlert("Server is not running. Please start the server.");
        } else {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/items/Items.fxml"));
                AnchorPane anchorpane = loader.load();
                mainAP.getChildren().setAll(anchorpane);
                view = "Items";
                ItemsController itemsController = loader.getController();
                DAOItem itemObj = new DAOItem();
                arrItems = itemObj.getItems();
                itemsController.setItemList(arrItems);
                arrItems.clear();

            } catch (IOException ex) {
                Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void clickDashboard(ActionEvent event) {
        if (DashboardController.getServer() == null) {
            showAlert("Server is not running. Please start the server.");
        } else if (!DashboardController.getServer().isServerRunning()) {
            showAlert("Server is not running. Please start the server.");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/Dashboard.fxml"));
                AnchorPane anchorpane = loader.load();
                mainAP.getChildren().setAll(anchorpane);

            } catch (IOException ex) {
                Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void clickUsers(ActionEvent event) {
        if (DashboardController.getServer() == null) {
            showAlert("Server is not running. Please start the server.");
        } else if (!DashboardController.getServer().isServerRunning()) {
            showAlert("Server is not running. Please start the server.");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/users/Users.fxml"));
                AnchorPane anchorpane = loader.load();
                mainAP.getChildren().setAll(anchorpane);
                view = "Users";
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
    }

    @FXML
    void search(ActionEvent event) throws IOException, SQLException {
        if (DashboardController.getServer() == null) {
            showAlert("Server is not running. Please start the server.");
        } else if (!DashboardController.getServer().isServerRunning()) {
            showAlert("Server is not running. Please start the server.");
        } else {
            switch (view) {
                case "Users":
                    if (txtfieldSearch.getText().isEmpty() != true) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/users/Users.fxml"));
                        AnchorPane anchorpane = loader.load();
                        mainAP.getChildren().setAll(anchorpane);
                        UsersController userController = loader.getController();
                        DAOUser usersearchObj = new DAOUser();
                        arrSearchUser = usersearchObj.searchUsers(txtfieldSearch.getText());
                        userController.setItemList(arrSearchUser);
                        arrSearchUser.clear();
                    } else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/users/Users.fxml"));
                        AnchorPane anchorpane = loader.load();
                        mainAP.getChildren().setAll(anchorpane);
                        UsersController userController = loader.getController();
                        DAOUser userObj = new DAOUser();
                        arrUser = userObj.getUsers();
                        userController.setItemList(arrUser);
                        arrUser.clear();
                    }
                    break;

                case "Items":
                    if (txtfieldSearch.getText().isEmpty() != true) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/items/Items.fxml"));
                        AnchorPane anchorpane = loader.load();
                        mainAP.getChildren().setAll(anchorpane);
                        ItemsController itemsController = loader.getController();
                        DAOItem itemsearchObj = new DAOItem();
                        arrSearchItems = itemsearchObj.searchItems(txtfieldSearch.getText());
                        itemsController.setItemList(arrSearchItems);
                        arrSearchItems.clear();
                    } else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/items/Items.fxml"));
                        AnchorPane anchorpane = loader.load();
                        mainAP.getChildren().setAll(anchorpane);
                        view = "Items";
                        ItemsController itemsController = loader.getController();
                        DAOItem itemObj = new DAOItem();
                        arrItems = itemObj.getItems();
                        itemsController.setItemList(arrItems);
                        arrItems.clear();
                    }
                    break;
                default:
                    System.out.println("failed search");
                    break;
            }
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

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Server Alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void clickscrap(ActionEvent event) {
        if (DashboardController.getServer() == null) {
            showAlert("Server is not running. Please start the server.");
        } else if (!DashboardController.getServer().isServerRunning()) {
            showAlert("Server is not running. Please start the server.");
        } else {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/serveriwish/admindashboardview/scrap/scrap.fxml"));
                AnchorPane anchorpane = loader.load();
                mainAP.getChildren().setAll(anchorpane);
                view = "Items";
                ScrapController itemsController = loader.getController();
                arrItems = startScrap();
                itemsController.setItemList(arrItems);
                arrItems.clear();

            } catch (IOException ex) {
                Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static byte[] downloadImage(String imageUrl) throws IOException {
        try {
            // Check if the URL is absolute
            URL url = new URL(imageUrl);

            try (InputStream in = url.openStream()) {
                return readAllBytesFromInputStream(in);
            }
        } catch (MalformedURLException e) {
            // The URL is relative, prepend the base URL
            URL baseUrl = new URL("https://books.toscrape.com/");
            URL absoluteUrl = new URL(baseUrl, imageUrl);

            try (InputStream in = absoluteUrl.openStream()) {
                return readAllBytesFromInputStream(in);
            }
        }
    }

    private static byte[] readAllBytesFromInputStream(InputStream in) throws IOException {
        // Create a buffer to read the input stream in chunks
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // Read from the input stream and write to the ByteArrayOutputStream
        while ((bytesRead = in.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }

        // Convert the ByteArrayOutputStream to a byte array
        return baos.toByteArray();
    }
    private ArrayList<DTOItem> startScrap(){
        //ArrayList<DTOItem> ScrappedItems = new ArrayList<>();
        
        String url ="https://books.toscrape.com/";
        ArrayList<DTOItem> scrapingitems = new ArrayList<DTOItem>();
        try {
            Document document = Jsoup.connect(url).get();
            
            Elements imageContainers = document.select(".image_container");
            
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("Images : Web Scraper");

            List<byte[]> imageBytesList = new ArrayList<>();

            for (Element imageContainer : imageContainers) {
                // Select the <img> tag and get the src attribute
                String imageUrl = imageContainer.select("img.thumbnail").attr("src");

                // Download the image content as an array of bytes
                byte[] imageBytes = downloadImage(imageUrl);
                
                // Add the image bytes to the list
                imageBytesList.add(imageBytes);
                scrapingitems.add(new DTOItem(imageBytes));
            }

            Elements books = document.select(".product_pod");
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("Books : Web Scrapper");
            
            int i = 0;
            for ( Element bk : books) {
                String title = bk.select("h3 > a").attr("title");
                //Double price = Double.parseDouble(bk.select(".price_color").text().replaceAll("[^0-9]", "").trim())* Math.pow(10, -2);
                   
                String price = bk.select(".price_color").text().replaceAll("[^\\d.]+", "").trim().substring(0, 2);

                System.out.println(title + "  " + price);
                scrapingitems.get(i).setItemname(title);
                scrapingitems.get(i).setItemprice(Integer.parseInt(price));
                i++;
                
            }
            System.out.println("++++++++++++++++++++++++++++++");

            System.out.println(scrapingitems);
            
           
         
    }   catch (IOException ex) {
            Logger.getLogger(ItemScrapping.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            return scrapingitems;
        
}

}


