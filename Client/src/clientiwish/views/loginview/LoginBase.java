package clientiwish.views.loginview;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public abstract class LoginBase extends BorderPane {

    protected final AnchorPane anchorPane;
    protected final TextField textFieldUserName;
    protected final PasswordField textFieldPasswordField;
    protected final Button bttnLogin;
    protected final Hyperlink hyperlink;
    protected final Label label;
    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final Hyperlink regHyper;
    protected final AnchorPane anchorPane0;
    protected final ImageView imageView1;
    protected final DropShadow dropShadow;

    public LoginBase() {

        anchorPane = new AnchorPane();
        textFieldUserName = new TextField();
        textFieldPasswordField = new PasswordField();
        bttnLogin = new Button();
        hyperlink = new Hyperlink();
        label = new Label();
        imageView = new ImageView();
        imageView0 = new ImageView();
        regHyper = new Hyperlink();
        anchorPane0 = new AnchorPane();
        imageView1 = new ImageView();
        dropShadow = new DropShadow();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(900.0);

        anchorPane.setPrefHeight(600.0);
        anchorPane.setPrefWidth(545.0);
        anchorPane.setStyle("-fx-background-color: #ffffff;");
        anchorPane.getStylesheets().add("/clientiwish/views/loginview/../resources/loginandregister/pic.css");

        textFieldUserName.setLayoutX(144.0);
        textFieldUserName.setLayoutY(252.0);
        textFieldUserName.setPrefHeight(48.0);
        textFieldUserName.setPrefWidth(211.0);
        textFieldUserName.setPromptText("User Name...");
        textFieldUserName.setStyle("-fx-background-color: transparent; -fx-border-color: #00000099; -fx-border-width: 0px 0px 2px 0px;");
        textFieldUserName.setFont(new Font(18.0));

        textFieldPasswordField.setLayoutX(142.0);
        textFieldPasswordField.setLayoutY(310.0);
        textFieldPasswordField.setPrefHeight(50.0);
        textFieldPasswordField.setPrefWidth(215.0);
        textFieldPasswordField.setPromptText("Password...");
        textFieldPasswordField.setStyle("-fx-background-color: transparent; -fx-border-color: #00000099; -fx-border-width: 0px 0px 2px 0px;");
        textFieldPasswordField.setFont(new Font(18.0));

        bttnLogin.setLayoutX(197.0);
        bttnLogin.setLayoutY(414.0);
        bttnLogin.setMnemonicParsing(false);
        bttnLogin.setOnAction(this::handleLoginAction);
        bttnLogin.setPrefHeight(41.0);
        bttnLogin.setPrefWidth(105.0);
        bttnLogin.setStyle("-fx-background-color: #F6B63DD6; -fx-border-radius: 80;");
        bttnLogin.setText("Login");
        bttnLogin.setTextFill(javafx.scene.paint.Color.WHITE);
        bttnLogin.setOpaqueInsets(new Insets(10.0, 0.0, 0.0, 0.0));
        bttnLogin.setFont(new Font(24.0));

        hyperlink.setLayoutX(145.0);
        hyperlink.setLayoutY(367.0);
        hyperlink.setStyle("-: #F6B63DD6;");
        hyperlink.setText("Forgot Password?");
        hyperlink.setTextFill(javafx.scene.paint.Color.valueOf("#f6b63d"));

        label.setLayoutX(165.0);
        label.setLayoutY(59.0);
        label.setPrefHeight(132.0);
        label.setPrefWidth(199.0);
        label.getStylesheets().add("/clientiwish/views/loginview/../resources/loginandregister/pic.css");
        label.setText("LOGIN");
        label.setFont(new Font("Aldhabi", 90.0));

        imageView.setFitHeight(32.0);
        imageView.setFitWidth(33.0);
        imageView.setLayoutX(105.0);
        imageView.setLayoutY(260.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("../resources/loginandregister/user-svgrepo-com.png").toExternalForm()));

        imageView0.setFitHeight(34.0);
        imageView0.setFitWidth(32.0);
        imageView0.setLayoutX(105.0);
        imageView0.setLayoutY(319.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("../resources/loginandregister/lock-svgrepo-com%20(1).png").toExternalForm()));

        regHyper.setLayoutX(129.0);
        regHyper.setLayoutY(524.0);
        regHyper.setOnAction(this::handleRegisterHyper);
        regHyper.setStyle("-: #F6B63DD6;");
        regHyper.getStylesheets().add("/clientiwish/views/loginview/../resources/loginandregister/pic.css");
        regHyper.setText("Don't have account? Register now..");
        regHyper.setTextFill(javafx.scene.paint.Color.valueOf("#f6b63d"));
        setCenter(anchorPane);

        BorderPane.setAlignment(anchorPane0, javafx.geometry.Pos.CENTER);
        anchorPane0.setPrefHeight(600.0);
        anchorPane0.setPrefWidth(371.0);
        anchorPane0.setStyle("-fx-background-color: #ffffff;");

        imageView1.setFitHeight(756.0);
        imageView1.setFitWidth(572.0);
        imageView1.setLayoutX(-84.0);
        imageView1.setLayoutY(-19.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setRotate(13.0);
        imageView1.setImage(new Image(getClass().getResource("../resources/loginandregister/gift.jpg").toExternalForm()));

        dropShadow.setRadius(16.22);
        dropShadow.setWidth(45.88);
        imageView1.setEffect(dropShadow);
        setLeft(anchorPane0);

        anchorPane.getChildren().add(textFieldUserName);
        anchorPane.getChildren().add(textFieldPasswordField);
        anchorPane.getChildren().add(bttnLogin);
        anchorPane.getChildren().add(hyperlink);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(imageView0);
        anchorPane.getChildren().add(regHyper);
        anchorPane0.getChildren().add(imageView1);

    }

    protected abstract void handleLoginAction(javafx.event.ActionEvent actionEvent);

    protected abstract void handleRegisterHyper(javafx.event.ActionEvent actionEvent);

}
