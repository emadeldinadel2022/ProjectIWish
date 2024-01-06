
package clientiwish.views.loginview;

import clientiwish.services.Login;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController implements Initializable {
    
    @FXML
    private TextField textFieldUserName;
    @FXML 
    private PasswordField textFieldPasswordField;
    private Stage stage;
    private Scene scene;
    private Parent root;
	
    private Login loginDate;
    
    @FXML
    private void handleLoginAction(ActionEvent event) {
        String username = textFieldUserName.getText();
        String password = textFieldPasswordField.getText();
        
        if (password.length() < 8){
            System.out.println("password must be greater than 8 chars");
        }
        System.out.println(username);
        System.out.println(password);
        
        loginDate = new Login(username, password);
    }
    
    @FXML
    private void handleRegisterHyper(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/registerview/Register.fxml"));	
        root = loader.load();
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
		
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
