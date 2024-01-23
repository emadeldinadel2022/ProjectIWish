/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientiwish.views.userprofileview.editprofile;

import clientCommunication.Client;
import static clientCommunication.Client.getCurrentUser;
import clientiwish.models.DTOUser;
import clientiwish.views.userprofileview.editbalance.EditBalanceController;
import clientiwish.views.userprofileview.home.UserProfileController;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sharedlibraries.Response;

/**
 * FXML Controller class
 *
 * @author Sherif
 */
public class EditprofileController implements Initializable {

    @FXML
    private AnchorPane editAnchorPane;
    @FXML
    private Label fullnameLabel;
    @FXML
    private TextField fullnameTextField;
    @FXML
    private Button confirmButton;
    @FXML
    private Label editLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label confirmpasswordLabel;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField confirmpasswordTextField;
    String st;
    @FXML
    private ImageView iconImageView;
    @FXML
    private Label labelViewName;
    @FXML
    private Label enterBalanceLabel1;
    @FXML
    private Label yourbalance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editLabel.setText("");
    }

    @FXML
    private void handleConfirmButton(ActionEvent event) {
        DTOUser editUser = getCurrentUser();

        if (fullnameTextField.getText().isEmpty() && passwordTextField.getText().isEmpty() && confirmpasswordTextField.getText().isEmpty()) {
            editLabel.setText("Please enter the fields you want to edit!");
            editLabel.setTextFill(Color.RED);
        } //edit user full name
        else if (fullnameTextField.getText().isEmpty() == false && passwordTextField.getText().isEmpty() && confirmpasswordTextField.getText().isEmpty()) {
            editUser.setName(fullnameTextField.getText());
            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("Edit_name", editUser);
                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(EditprofileController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    // Handle the response from the server
                    Response<DTOUser> responseEditName = Client.getResponse();
                    Platform.runLater(() -> {
                        st = fullnameTextField.getText();
                        System.out.println(st);
                        if (responseEditName != null && responseEditName.isSuccess()) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/home/UserProfile.fxml"));
                            Parent root;
                            AnchorPane newAnchorPane;
                            try {
                                root = loader.load();
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                                FXMLLoader APloader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/editprofile/editprofile.fxml"));
                                newAnchorPane = APloader.load();
                                UserProfileController userProfileController = loader.getController();
                                userProfileController.setviewAnchorPane(newAnchorPane);
                                userProfileController.getLabelUserName().setText(st);
                                userProfileController.getLabelUserUniqueName().setText(Client.getCurrentUser().getUserUniqueName());
                                userProfileController.getLabelUserEmail().setText(Client.getCurrentUser().getEmail());
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                String formattedDate = dateFormat.format(Client.getCurrentUser().getDob());
                                userProfileController.getLabelUserDOB().setText(formattedDate);

                            } catch (IOException ex) {
                                Logger.getLogger(EditprofileController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            editLabel.setText("Your Name has been changed!");
                            editLabel.setTextFill(Color.web("#43bc1b"));
                            fullnameTextField.clear();
                        } else {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    editLabel.setText("Please try again!");
                                    editLabel.setTextFill(Color.RED);
                                }
                            });

                        }
                    });
                }
            }.start();
        } // edit password
        else if (fullnameTextField.getText().isEmpty() && passwordTextField.getText().isEmpty() == false && confirmpasswordTextField.getText().isEmpty() == false) {
            if (passwordTextField.getText().equals(confirmpasswordTextField.getText()) && passwordTextField.getText().length() >= 8) {
                editUser.setPassword(confirmpasswordTextField.getText());

                new Thread() {
                    public void run() {
                        Client.updateResponse(null);
                        Client.sendRequest("Edit_passward", editUser);

                        while (Client.getResponse() == null) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(EditprofileController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        // Handle the response from the server
                        Response<ArrayList<DTOUser>> responseEditPassword = Client.getResponse();
                        Platform.runLater(() -> {
                            if (responseEditPassword != null && responseEditPassword.isSuccess()) {
                                try {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    
                                    alert.setTitle("Contribution Status");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Your password had been changed successfully, please login again!");
                                    
                                    alert.showAndWait();
                                    passwordTextField.clear();
                                    confirmpasswordTextField.clear();
                                    Parent root;
                                    
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/loginview/Login.fxml"));
                                    root = loader.load();
                                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    Scene scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();
                                } catch (IOException ex) {
                                    Logger.getLogger(EditprofileController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            } else {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        editLabel.setTextFill(Color.RED);
                                        editLabel.setText("Please try again!");
                                    }
                                });
                            }
                        });
                    }
                }.start();
            } else {
                editLabel.setTextFill(Color.RED);
                editLabel.setText("Passwords do not match.");
                System.out.println("here 1");
            }
        } // Changing username and password
        else if (fullnameTextField.getText().isEmpty() == false && passwordTextField.getText().isEmpty() == false && confirmpasswordTextField.getText().isEmpty() == false) {
            if (passwordTextField.getText().equals(confirmpasswordTextField.getText()) && passwordTextField.getText().length() >= 8) {
                editUser.setName(fullnameTextField.getText());
                editUser.setPassword(confirmpasswordTextField.getText());

                new Thread() {
                    public void run() {
                        Client.updateResponse(null);
                        Client.sendRequest("Edit_user", editUser);
                        while (Client.getResponse() == null) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(EditprofileController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        // Handle the response from the server
                        Response<DTOUser> responseEditUser = Client.getResponse();
                        Platform.runLater(() -> {
                            if (responseEditUser != null && responseEditUser.isSuccess()) {
                                try {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    
                                    alert.setTitle("Contribution Status");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Your name and password had been changed successfully, please login again!");
                                    
                                    alert.showAndWait();
                                    passwordTextField.clear();
                                    confirmpasswordTextField.clear();
                                    Parent root;
                                    
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/loginview/Login.fxml"));
                                    root = loader.load();
                                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    Scene scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();
                                    
                                    editLabel.setTextFill(Color.web("#43bc1b"));
                                    editLabel.setText("Your name and password have been changed!");
                                    passwordTextField.clear();
                                    confirmpasswordTextField.clear();
                                    fullnameTextField.clear();
                                } catch (IOException ex) {
                                    Logger.getLogger(EditprofileController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        editLabel.setTextFill(Color.RED);
                                        editLabel.setText("Please try again!");
                                    }
                                });
                            }
                        });
                    }
                }.start();
            } else {
                editLabel.setTextFill(Color.RED);
                editLabel.setText("Passwords do not match.");
                System.out.println("here 2");

            }
        }
    }
}
