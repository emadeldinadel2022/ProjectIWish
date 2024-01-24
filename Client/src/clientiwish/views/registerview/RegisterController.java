package clientiwish.views.registerview;

import clientCommunication.Client;
import clientiwish.models.DTOUser;
import java.io.IOException;
import java.time.LocalDate;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import sharedlibraries.Response;

public class RegisterController {

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
    private CheckBox chexkboxPolicy;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Hyperlink logHyper;

    @FXML
    private Label dataAlert;

    @FXML
    private Label confirmationAlert;

    @FXML
    private Label passwordAlert;
    @FXML
    private Button bttnSignUp;
    @FXML
    private ToggleGroup gender;
    @FXML
    private Label emailLabel;

    @FXML
    void handleLoginHyper(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/loginview/Login.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private static DTOUser regData = new DTOUser();

    @FXML
    public void handleButtonAction(ActionEvent event) {
         if (!Client.isServerConnected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connection Error");
            alert.setHeaderText(null);
            alert.setContentText("Server is out of serving right now, try again later");
            alert.showAndWait();
            return;
        }

        String userFullName = txtFieldUserFullName.getText();
        String userUniqueName = txtFieldUniqueName.getText();
        LocalDate userDate = datePickerDOB.getValue();
        String userEmail = txtfFieldEmail.getText();
        String userPassword = passFieldUserPassword.getText();
        String confirmUserPassword = passFieldConfirmPassword.getText();
        
        if (Client.getSocket().isClosed()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Server Alert");
            alert.setHeaderText(null);
            alert.setContentText("Server is disconnected, try again later");
            alert.showAndWait();
        }
                else if (userFullName.length() == 0 || userUniqueName.length() == 0
                || userFullName.length() == 0 || userDate == null) {
            dataAlert.setText("please, complete your data");
        } else {
            dataAlert.setText("");

            if (!isValidEmail(userEmail)) {
                emailLabel.setText("Invalid email format");
            } else {

                emailLabel.setText("");
                if (userPassword.length() < 8) {
                    passwordAlert.setText("Password must be greater than 8 chars");
                } else {
                    passwordAlert.setText("");
                    if (!userPassword.equals(confirmUserPassword)) {
                        confirmationAlert.setText("Wrong password confirmation!");
                    } else {
                        confirmationAlert.setText("");
                        RadioButton selectedRadioButton = (RadioButton) gender.getSelectedToggle();
                        if (selectedRadioButton != null) {
                            char selectedGender = selectedRadioButton.getText().charAt(0);
                            new Thread() {
                                @Override
                                public void run() {
                                    Client.updateResponse(null);
                                    Client.sendRequest("REGISTER", new DTOUser(userUniqueName, userEmail, userFullName, userPassword, userDate, selectedGender));

                                    while (Client.getResponse() == null) {
                                        try {
                                            Thread.sleep(100);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    Response<DTOUser> responseRegister = Client.getResponse();
                                    Platform.runLater(() -> {
                                        if (responseRegister != null && responseRegister.isSuccess()) {
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
                                        } else {
                                            Platform.runLater(new Runnable() {
                                                @Override
                                                public void run() {
                                                    dataAlert.setText("User already exists, please change the username.");
                                                }
                                            });

                                        }
                                    });
                                }
                            }.start();
                        } else {
                            dataAlert.setText("plese select your gender");
                        }
                    }
                }

            }
        }
    }

    @FXML
    public void getGender(ActionEvent actionEvent) {
        if (radioBttnMale.isSelected()) {
            regData.setGender('M');
        } else if (radioBttnFemale.isSelected()) {
            regData.setGender('F');
        }
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

        return email.matches(emailPattern);
    }

}
