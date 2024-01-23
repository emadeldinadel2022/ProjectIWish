package clientiwish.views.userprofileview.editbalance;

import clientCommunication.Client;
import clientiwish.models.DTOUser;
import clientiwish.views.userprofileview.home.UserProfileController;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sharedlibraries.Response;

public class EditBalanceController implements Initializable {

    @FXML
    private AnchorPane balanceAnchorPane;

    @FXML
    private Label enterBalanceLabel;

    @FXML
    private TextField enterBalanceTextField;

    @FXML
    private Button confirmButton;

    private Label balanceSuccessLabel;

    private Label balanceFailureLabel;

    @FXML
    private ImageView iconImageView;
    @FXML
    private Label labelViewName;
    @FXML
    private Label enterBalanceLabel1;
    @FXML
    private Label yourbalance;
    @FXML
    private Label dataAlert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        yourbalance.setText("$ " + String.valueOf(Client.getCurrentUser().getBalance()));
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    void handleConfirmButton(ActionEvent event) {
        if (enterBalanceTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Failed");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the amount you want to add to your balance!");
            alert.showAndWait();
        } else if (Integer.parseInt(enterBalanceTextField.getText()) == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Failed");
            alert.setHeaderText(null);
            alert.setContentText("Please enter the amount you want to add to your balance!");
            alert.showAndWait();
        } else if (isNumeric(enterBalanceTextField.getText()) && Integer.parseInt(enterBalanceTextField.getText()) > 0) {
            int recharge = Integer.parseInt(enterBalanceTextField.getText());
            DTOUser addBalance = new DTOUser(Client.getCurrentUser().getUserUniqueName(), recharge);
            new Thread() {
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("Add_balance", addBalance);
                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(EditBalanceController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    Response<DTOUser> responseAddBalance = Client.getResponse();
                    if (responseAddBalance != null && responseAddBalance.isSuccess()) {
                        Platform.runLater(new Runnable() {
                            public void run() {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/home/UserProfile.fxml"));

                                UserProfileController userProfileController = loader.getController();
                                DTOUser receivedUser = (DTOUser) responseAddBalance.getData();
                                Client.getCurrentUser().setBalance(receivedUser.getBalance());
                                yourbalance.setText("$ " + Client.getCurrentUser().getBalance());

                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Recharge");
                                alert.setHeaderText(null);
                                alert.setContentText("Your balance has been recharged successfully!");
                                alert.showAndWait();
                                System.out.println(receivedUser.getBalance());
                            }
                        });
                    } else {
                        dataAlert.setText("Failed edit");
                        dataAlert.setText("");
                    }
                }
            }.start();
        } else {
            dataAlert.setText("Failed edit");
            dataAlert.setText("");
        }
    }

}
