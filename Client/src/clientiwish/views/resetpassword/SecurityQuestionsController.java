package clientiwish.views.resetpassword;

import clientCommunication.Client;
import clientiwish.models.DTOUser;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sharedlibraries.Response;

public class SecurityQuestionsController{

    @FXML
    private TextField textFieldUserName;
    @FXML
    private Hyperlink loginHyper;
    @FXML
    private DatePicker datePickerDOB;
    @FXML
    private TextField textFieldBalance;
    @FXML
    private Label dataAlert;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button bttnVerify;

   
    @FXML
public void verifyData(ActionEvent event) {
     if (!Client.isServerConnected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connection Error");
            alert.setHeaderText(null);
            alert.setContentText("Server is out of serving right now, try again later");
            alert.showAndWait();
            return;
        }

    String userUniqueName = textFieldUserName.getText();
    LocalDate userDate = datePickerDOB.getValue();

    try {
        int balance = Integer.parseInt(textFieldBalance.getText());

         if (Client.getSocket().isClosed()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Server Alert");
            alert.setHeaderText(null);
            alert.setContentText("Server is disconnected, try again later");
            alert.showAndWait();
        }
         else if (userUniqueName.isEmpty() || userDate == null || balance == 0 || textFieldBalance.getText().equals("")) {
            dataAlert.setText("Incomplete Data");
        } else {
            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("SECURITYQUESTION", new DTOUser(userUniqueName, balance, userDate));
                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Response<DTOUser> response = Client.getResponse();
                    if (response != null && response.isSuccess()) {
                        Client.setCurrentUser(new DTOUser());
                        Client.getCurrentUser().setUser_name(userUniqueName);
                        Platform.runLater(new Runnable() {
                            public void run() {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/resetpassword/ResetPassword.fxml"));
                                Parent root;
                                try {
                                    root = loader.load();
                                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    Scene scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                    } else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                dataAlert.setText("");
                                dataAlert.setText("Invalid entered data");
                            }
                        });
                    }
                }
            }.start();
        }
    } catch (NumberFormatException e) {
        showAlert("Invalid Balance Format", "Balance must be a valid integer.");
    }
}

        @FXML
        private void returnToLogin
        (ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/loginview/Login.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
