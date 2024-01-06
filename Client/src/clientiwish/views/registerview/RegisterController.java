/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.views.registerview;

import clientiwish.services.Register;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


/**
 *
 * @author Software
 */
public class RegisterController{
    @FXML
    private TextField txtFieldUserFullName;
    @FXML
    private TextField txtFieldUniqueName;
    @FXML
    private DatePicker datePickerDOB;
    @FXML
    private TextField txtfFieldEmail;
    @FXML
    private PasswordField passFieldUserPassword;
    @FXML
    private PasswordField passFieldConfirmPassword;
    @FXML
    private RadioButton radioBttnMale, radioBttnFemale;
    @FXML
    private CheckBox chexkboxPolicy;

    
    private static Register regData = new Register();
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        String userFullName = txtFieldUserFullName.getText();
        String userUniqueName = txtFieldUniqueName.getText();
        LocalDate localDate = datePickerDOB.getValue();
        String userEmail = txtfFieldEmail.getText();
        String userPassword =passFieldUserPassword.getText();
        String confirmUserPassword = passFieldConfirmPassword.getText();
        
        
        if(userFullName.length() == 0 || userUniqueName.length() == 0 || userPassword.length() == 0 
                || userFullName.length() == 0 || confirmUserPassword.length() == 0 || localDate == null){
            System.out.println("please enter your data");
        }
        
        
        //confirmUserPassword
        else if(!userPassword.equals(confirmUserPassword)){
            System.out.println("wrong password confirmation!");
        }
        
        //user password check
        if(userPassword.length() < 8){
            System.out.println("password must be greater than 8 chars");
        } 
        
        
        
       regData = new Register(userFullName, userUniqueName, localDate, ' ', userEmail, userPassword);
        
    }
    
    @FXML
    public void justBttnHandler(ActionEvent actionEvent) {
        if (chexkboxPolicy.isSelected()) {
            chexkboxPolicy.setSelected(true);
        } else {
            chexkboxPolicy.setSelected(false);
        }
    }

    
    @FXML
    public void getGender(ActionEvent actionEvent){
        if (radioBttnMale.isSelected()) {
            regData.setUserGender('M');
        } else if (radioBttnFemale.isSelected()) {
            regData.setUserGender('F');
        }      
    }
    
    
    
}
