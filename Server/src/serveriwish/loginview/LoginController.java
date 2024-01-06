/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveriwish.loginview;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Friends
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtFieldAdminName;
    @FXML
    private TextField txtFieldAdminPassword;
    @FXML
    private Button bttnLogin;
    @FXML
    private Hyperlink hyperLinkPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleLogin(ActionEvent event) {
    }

    @FXML
    private void handleForgetPassword(ActionEvent event) {
    }
    
}
