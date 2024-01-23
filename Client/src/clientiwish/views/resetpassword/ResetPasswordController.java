package clientiwish.views.resetpassword;

import clientCommunication.Client;
import clientiwish.models.DTOUser;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import sharedlibraries.Response;

public class ResetPasswordController {

    @FXML
    private Button bttnUpdatePassword;
    @FXML
    private Hyperlink loginHyper;
    @FXML
    private Label dataAlert;

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private PasswordField textFieldPasswordField;
    @FXML
    private PasswordField textFieldConfirmPasswordF;

   
    @FXML
    private void updatePassword(ActionEvent event) {
        String password = textFieldPasswordField.getText();
        String confirmPassword = textFieldConfirmPasswordF.getText();
        if (password.length() < 8) {
            dataAlert.setText("Password must be more than 8 characters");
        } else if (!password.equals(confirmPassword)) {
            dataAlert.setText("");
            dataAlert.setText("Wrong password confirmation!");
        } else {
            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("RESETPASSWORD", new DTOUser(Client.getCurrentUser().getUserUniqueName(), password));

                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Response<DTOUser> responseReset = Client.getResponse();
                    Platform.runLater(() -> {
                        if (responseReset != null && responseReset.isSuccess()) {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/loginview/Login.fxml"));
                                root = loader.load();
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }else{
                            showAlert("Error Occurred", "Failed to reset password, try again later");
                        }
                    });
                }
            }.start();
        }
    }

    @FXML
    private void returnToLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/loginview/Login.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
