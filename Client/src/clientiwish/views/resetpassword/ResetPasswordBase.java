package clientiwish.views.resetpassword;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public abstract class ResetPasswordBase extends BorderPane {

    protected final AnchorPane anchorPane;
    protected final Button bttnUpdatePassword;
    protected final Label label;
    protected final Hyperlink loginHyper;
    protected final Label dataAlert;
    protected final ImageView imageView;
    protected final PasswordField textFieldPasswordField;
    protected final PasswordField textFieldConfirmPasswordF;
    protected final ImageView imageView0;
    protected final AnchorPane anchorPane0;
    protected final ImageView imageView1;
    protected final DropShadow dropShadow;

    public ResetPasswordBase() {

        anchorPane = new AnchorPane();
        bttnUpdatePassword = new Button();
        label = new Label();
        loginHyper = new Hyperlink();
        dataAlert = new Label();
        imageView = new ImageView();
        textFieldPasswordField = new PasswordField();
        textFieldConfirmPasswordF = new PasswordField();
        imageView0 = new ImageView();
        anchorPane0 = new AnchorPane();
        imageView1 = new ImageView();
        dropShadow = new DropShadow();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(800.0);

        anchorPane.setPrefHeight(600.0);
        anchorPane.setPrefWidth(545.0);
        anchorPane.setStyle("-fx-background-color: #ffffff;");
        anchorPane.getStylesheets().add("/clientiwish/views/resetpassword/../resources/loginandregister/pic.css");

        bttnUpdatePassword.setLayoutX(117.0);
        bttnUpdatePassword.setLayoutY(461.0);
        bttnUpdatePassword.setMnemonicParsing(false);
        bttnUpdatePassword.setOnAction(this::updatePassword);
        bttnUpdatePassword.setPrefHeight(51.0);
        bttnUpdatePassword.setPrefWidth(230.0);
        bttnUpdatePassword.setStyle("-fx-background-color: #F6B63DD6; -fx-border-radius: 80;");
        bttnUpdatePassword.setText("Update Password");
        bttnUpdatePassword.setTextFill(javafx.scene.paint.Color.WHITE);
        bttnUpdatePassword.setOpaqueInsets(new Insets(10.0, 0.0, 0.0, 0.0));
        bttnUpdatePassword.setFont(new Font(24.0));

        label.setLayoutX(79.0);
        label.setLayoutY(64.0);
        label.setPrefHeight(156.0);
        label.setPrefWidth(342.0);
        label.getStylesheets().add("/clientiwish/views/resetpassword/../resources/loginandregister/pic.css");
        label.setText("Reset Password");
        label.setFont(new Font("Aldhabi", 68.0));

        loginHyper.setLayoutX(186.0);
        loginHyper.setLayoutY(555.0);
        loginHyper.setOnAction(this::returnToLogin);
        loginHyper.setStyle("-: #F6B63DD6;");
        loginHyper.getStylesheets().add("/clientiwish/views/resetpassword/../resources/loginandregister/pic.css");
        loginHyper.setText("Return to login");
        loginHyper.setTextFill(javafx.scene.paint.Color.valueOf("#f6b63d"));

        dataAlert.setLayoutX(158.0);
        dataAlert.setLayoutY(426.0);
        dataAlert.setTextFill(javafx.scene.paint.Color.RED);
        dataAlert.setFont(new Font(15.0));

        imageView.setFitHeight(34.0);
        imageView.setFitWidth(32.0);
        imageView.setLayoutX(90.0);
        imageView.setLayoutY(284.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("../resources/loginandregister/lock-svgrepo-com%20(1).png").toExternalForm()));

        textFieldPasswordField.setLayoutX(129.0);
        textFieldPasswordField.setLayoutY(275.0);
        textFieldPasswordField.setPrefHeight(50.0);
        textFieldPasswordField.setPrefWidth(215.0);
        textFieldPasswordField.setPromptText("Password...");
        textFieldPasswordField.setStyle("-fx-background-color: transparent; -fx-border-color: #00000099; -fx-border-width: 0px 0px 2px 0px;");
        textFieldPasswordField.setFont(new Font(18.0));

        textFieldConfirmPasswordF.setLayoutX(129.0);
        textFieldConfirmPasswordF.setLayoutY(336.0);
        textFieldConfirmPasswordF.setPrefHeight(50.0);
        textFieldConfirmPasswordF.setPrefWidth(215.0);
        textFieldConfirmPasswordF.setPromptText("Confirm Password...");
        textFieldConfirmPasswordF.setStyle("-fx-background-color: transparent; -fx-border-color: #00000099; -fx-border-width: 0px 0px 2px 0px;");
        textFieldConfirmPasswordF.setFont(new Font(18.0));

        imageView0.setFitHeight(34.0);
        imageView0.setFitWidth(32.0);
        imageView0.setLayoutX(90.0);
        imageView0.setLayoutY(345.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("../resources/loginandregister/security.png").toExternalForm()));
        setCenter(anchorPane);

        BorderPane.setAlignment(anchorPane0, javafx.geometry.Pos.CENTER);
        anchorPane0.setPrefHeight(600.0);
        anchorPane0.setPrefWidth(371.0);
        anchorPane0.setStyle("-fx-background-color: #ffffff;");

        imageView1.setFitHeight(756.0);
        imageView1.setFitWidth(572.0);
        imageView1.setLayoutX(-123.0);
        imageView1.setLayoutY(-19.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setRotate(13.0);
        imageView1.setImage(new Image(getClass().getResource("../resources/loginandregister/gift.jpg").toExternalForm()));

        dropShadow.setRadius(16.22);
        dropShadow.setWidth(45.88);
        imageView1.setEffect(dropShadow);
        setLeft(anchorPane0);

        anchorPane.getChildren().add(bttnUpdatePassword);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(loginHyper);
        anchorPane.getChildren().add(dataAlert);
        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(textFieldPasswordField);
        anchorPane.getChildren().add(textFieldConfirmPasswordF);
        anchorPane.getChildren().add(imageView0);
        anchorPane0.getChildren().add(imageView1);

    }

    protected abstract void updatePassword(javafx.event.ActionEvent actionEvent);

    protected abstract void returnToLogin(javafx.event.ActionEvent actionEvent);

}
