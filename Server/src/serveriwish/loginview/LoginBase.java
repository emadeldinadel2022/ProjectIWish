package serveriwish.loginview;

import java.lang.String;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public abstract class LoginBase extends StackPane {

    protected final AnchorPane anchorPane;
    protected final ImageView imageView;
    protected final Label label;
    protected final TextField txtFieldAdminName;
    protected final TextField txtFieldAdminPassword;
    protected final Button bttnLogin;
    protected final Hyperlink hyperLinkPass;
    protected final ImageView imageView0;
    protected final ImageView imageView1;

    public LoginBase() {

        anchorPane = new AnchorPane();
        imageView = new ImageView();
        label = new Label();
        txtFieldAdminName = new TextField();
        txtFieldAdminPassword = new TextField();
        bttnLogin = new Button();
        hyperLinkPass = new Hyperlink();
        imageView0 = new ImageView();
        imageView1 = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(500.0);
        setPrefWidth(350.0);

        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);

        imageView.setFitHeight(100.0);
        imageView.setFitWidth(100.0);
        imageView.setLayoutX(125.0);
        imageView.setLayoutY(66.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("../resources/login/4919646.png").toExternalForm()));

        label.setLayoutX(98.0);
        label.setLayoutY(166.0);
        label.setText("Admin Login");
        label.setFont(new Font("Aldhabi", 48.0));

        txtFieldAdminName.setLayoutX(101.0);
        txtFieldAdminName.setLayoutY(269.0);
        txtFieldAdminName.setPrefHeight(25.0);
        txtFieldAdminName.setPrefWidth(203.0);
        txtFieldAdminName.setPromptText("User name...");

        txtFieldAdminPassword.setLayoutX(101.0);
        txtFieldAdminPassword.setLayoutY(327.0);
        txtFieldAdminPassword.setPrefHeight(25.0);
        txtFieldAdminPassword.setPrefWidth(203.0);
        txtFieldAdminPassword.setPromptText("Password...");

        bttnLogin.setLayoutX(127.0);
        bttnLogin.setLayoutY(417.0);
        bttnLogin.setMnemonicParsing(false);
        bttnLogin.setOnAction(this::handleLogin);
        bttnLogin.setPrefHeight(45.0);
        bttnLogin.setPrefWidth(100.0);
        bttnLogin.getStylesheets().add("/serveriwish/loginview/../resources/admindashboard/style.css");
        bttnLogin.setText("Login");
        bttnLogin.setFont(new Font("Bell MT", 19.0));

        hyperLinkPass.setLayoutX(116.0);
        hyperLinkPass.setLayoutY(384.0);
        hyperLinkPass.setOnAction(this::handleForgetPassword);
        hyperLinkPass.setText("Forget your password?");

        imageView0.setFitHeight(49.0);
        imageView0.setFitWidth(45.0);
        imageView0.setLayoutX(27.0);
        imageView0.setLayoutY(256.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("../resources/admindashboard/icons8-user-96.png").toExternalForm()));

        imageView1.setFitHeight(56.0);
        imageView1.setFitWidth(59.0);
        imageView1.setLayoutX(27.0);
        imageView1.setLayoutY(315.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("../resources/login/16205-200.png").toExternalForm()));

        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(txtFieldAdminName);
        anchorPane.getChildren().add(txtFieldAdminPassword);
        anchorPane.getChildren().add(bttnLogin);
        anchorPane.getChildren().add(hyperLinkPass);
        anchorPane.getChildren().add(imageView0);
        anchorPane.getChildren().add(imageView1);
        getChildren().add(anchorPane);

    }

    protected abstract void handleLogin(javafx.event.ActionEvent actionEvent);

    protected abstract void handleForgetPassword(javafx.event.ActionEvent actionEvent);

}
