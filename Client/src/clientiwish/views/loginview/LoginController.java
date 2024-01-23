package clientiwish.views.loginview;

import clientCommunication.Client;
import clientiwish.models.DTOUser;
import clientiwish.views.userprofileview.home.UserProfileController;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sharedlibraries.Response;

public class LoginController{

    @FXML
    private TextField textFieldUserName;
    @FXML
    private PasswordField textFieldPasswordField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label loginPasswordAlert;
    @FXML
    private ImageView userImage;
    @FXML
    private Label loginDataAlert;
    @FXML
    private Button bttnLogin;
    @FXML
    private Hyperlink forgetPasswordHyper;
    @FXML
    private Hyperlink regHyper;
    
    
    
    private Image getImageFromBytes(byte[] imageData) {
        ByteArrayInputStream stream = new ByteArrayInputStream(imageData);
        return new Image(stream);
    }

    @FXML
    private void handleLoginAction(ActionEvent event) {
        String username = textFieldUserName.getText();
        String password = textFieldPasswordField.getText();
        if (username.length() == 0 || password.length() == 0) {
            loginDataAlert.setText("Complete login data");
        } else if (username.length() > 0 && password.length() < 8) {
            loginPasswordAlert.setText("Password must be at least 8 chars");
            loginDataAlert.setText("");
        } else {

            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("LOGIN", new DTOUser(username, password));
                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100); 
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Response<DTOUser> response = Client.getResponse();
                    if (response != null && response.isSuccess()) {

                        Platform.runLater(new Runnable() {
                            public void run() {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/home/UserProfile.fxml"));
                                Parent root;
                                try {
                                    root = loader.load();
                                    UserProfileController userProfileController = loader.getController();
                                    DTOUser receivedUser = (DTOUser) response.getData();
                                    Client.setCurrentUser(receivedUser);

                                    userProfileController.getLabelUserName().setText(receivedUser.getName());
                                    userProfileController.getLabelUserUniqueName().setText(receivedUser.getUserUniqueName());
                                    userProfileController.getLabelUserEmail().setText(receivedUser.getEmail());
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
                                    String formattedDate = dateFormat.format(receivedUser.getDob());
                                    userProfileController.getLabelUserDOB().setText(formattedDate);
                                    if (receivedUser.getImage() != null) {
                                    Image image = getImageFromBytes(receivedUser.getImage());
                                    
                                        ImageView imageView = new ImageView(image);
                                    
                                        userProfileController.getUserImage().setImage(image);
                                    }
                                    

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
                                loginPasswordAlert.setText("");
                                loginPasswordAlert.setText("Incorrect username or password");
                            }
                        });
                    }
                }
            }.start();
        }
    }

    @FXML
    private void handleRegisterHyper(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/registerview/Register.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void forgetPassword(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/resetpassword/SecurityQuestions.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    
    

}
