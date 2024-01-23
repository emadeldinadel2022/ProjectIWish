/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveriwish.admindashboardview;

import dataaccesslayer.DAOItem;
import dataaccesslayer.DAOUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import models.DTOItem;
import server.Server;

/**
 * FXML Controller class
 *
 * @author Friends
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane dashboardAP;
    @FXML
    private Label labelUsers;
    @FXML
    private Label labelItems;
    @FXML
    private ToggleButton bttnServerStatus;
    @FXML
    private TextField txtFieldItemName;
    @FXML
    private TextField txtFieldItemDescription;
    @FXML
    private TextField txtFieldItemPrice;
    @FXML
    private Button bttnAddItem;
    @FXML
    private Label labelstate;
    @FXML
    private Button bttnUploadImage;
    String countItem;
    String countUser;
    byte[] imageBytes;
    File selectedFile;
    static Server server;
    boolean serverStatus = false;

    public static Server getServer() {
        return server;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dashboardAP.setOnKeyPressed(this::handleKeyPress);
        dashboardAP.setFocusTraversable(true);
        if (server == null) {
            showAlert("Server is not running. Please start the server.");
            bttnServerStatus.setStyle("-fx-background-color: red; -fx-border-color: red;");
            bttnServerStatus.setText("OFF");
        } else if (!server.isServerRunning()) {
            showAlert("Server is not running. Please start the server.");
            bttnServerStatus.setStyle("-fx-background-color: red; -fx-border-color: red;");
            bttnServerStatus.setText("OFF");
        } else {
            getAllUsers();
            getAllItems();
        }
    }

    private void handleKeyPress(KeyEvent event) {
        // Check if F5 key is pressed
        if (event.getCode() == KeyCode.F5) {
            // Perform both getAllUsers and getAllItems
            getAllUsers();
            getAllItems();
        }
    }

    @FXML
    private void getAllUsers() {
        DAOUser uCount = new DAOUser();
        countUser = Integer.toString(uCount.countUsers());
        labelUsers.setText(countUser);
    }

    @FXML
    private void getAllItems() {
        DAOItem iCount = new DAOItem();
        countItem = Integer.toString(iCount.countItem());
        labelItems.setText(countItem);
    }

    @FXML
    private void changeServerStatus(ActionEvent event) {
        //bttnServerStatus.setSelected(!bttnServerStatus.isSelected());

        // Update the color and text based on the button state
        if (server == null || !server.isServerRunning()) {
            server = Server.getInstance();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Server Alert");
            alert.setHeaderText(null);
            alert.setContentText("Server successfully started, connected to the database, and ready to accept client sockets");
            alert.showAndWait();
            server.startServer();
            bttnServerStatus.setStyle("-fx-background-color: #00e700; -fx-border-color: #00e700;");
            bttnServerStatus.setText("ON");
            getAllUsers();
            getAllItems();
        } else {
            showAlert("Server shutdown");
            server.stopServer();
            server = null;
            bttnServerStatus.setStyle("-fx-background-color: red; -fx-border-color: red;");
            bttnServerStatus.setText("OFF");
            labelItems.setText("---");
            labelUsers.setText("---");
        }

        //bttnServerStatus.setSelected(!bttnServerStatus.isSelected());
    }

    @FXML
    void uploadImageHandler(ActionEvent event) {
        if (server == null) {
            showAlert("Server is not running. Please start the server.");
        } else if (!server.isServerRunning()) {
            showAlert("Server is not running. Please start the server.");

        } else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Image File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

            selectedFile = fileChooser.showOpenDialog(bttnUploadImage.getScene().getWindow());
            if (selectedFile != null) {
                try {
                    imageBytes = Files.readAllBytes(selectedFile.toPath());
                } catch (IOException ex) {
                    Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @FXML
    private void addUserHandler(ActionEvent event) {
        if (server == null) {
            showAlert("Server is not running. Please start the server.");
        } else if (!server.isServerRunning()) {
            showAlert("Server is not running. Please start the server.");

        } else {
            int result;
            String name = txtFieldItemName.getText();
            String description = txtFieldItemDescription.getText();
            String price = txtFieldItemPrice.getText();
            if (name.isEmpty() || description.isEmpty() || price.isEmpty() || selectedFile == null) {
                labelstate.setText("Failed! please check your entries.");
                labelstate.setTextFill(Color.RED);
            } else {
                DAOItem item = new DAOItem();
                //  if (selectedFile != null) {

                result = item.addItemWithImage(new DTOItem(name, description, Integer.parseInt(price), imageBytes));
                if (result > 0) {
                    labelstate.setText("Item has been added successfully!");
                    labelstate.setTextFill(Color.web("#00ff00"));
                    getAllUsers();
                    getAllItems();
                } else {
                    labelstate.setText("Failed! please check your entries.");
                    labelstate.setTextFill(Color.RED);
                }

            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Server Alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
 