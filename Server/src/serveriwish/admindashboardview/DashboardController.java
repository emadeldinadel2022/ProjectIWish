/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveriwish.admindashboardview;

import dataaccesslayer.DAOItem;
import dataaccesslayer.DAOUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import models.DTOItem;

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
    String countItem;
    String countUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dashboardAP.setOnKeyPressed(this::handleKeyPress);
        dashboardAP.setFocusTraversable(true);
        getAllUsers();
        getAllItems();
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
    private void changeServerSatus(ActionEvent event) {
    }

    @FXML
    private void addUserHandler(ActionEvent event) {
        int result;
        String name = txtFieldItemName.getText();
        String description = txtFieldItemDescription.getText();
        String price = txtFieldItemPrice.getText();
        if (name.isEmpty() || description.isEmpty() || price.isEmpty()) {
            labelstate.setText("Failed! please check your entries.");
            labelstate.setTextFill(Color.RED);
        } else {
            DAOItem item = new DAOItem();
            result = item.addItem(new DTOItem(name, description, Integer.parseInt(price)));
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
