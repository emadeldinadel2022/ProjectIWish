package clientiwish.views.resetpassword;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public abstract class SecurityQuestionsBase extends BorderPane {

    protected final AnchorPane anchorPane;
    protected final TextField textFieldUserName;
    protected final Button bttnVerify;
    protected final Label label;
    protected final ImageView imageView;
    protected final Hyperlink loginHyper;
    protected final DatePicker datePickerDOB;
    protected final ImageView imageView0;
    protected final TextField textFieldBalance;
    protected final ImageView imageView1;
    protected final Label dataAlert;
    protected final AnchorPane anchorPane0;
    protected final ImageView imageView2;
    protected final DropShadow dropShadow;

    public SecurityQuestionsBase() {

        anchorPane = new AnchorPane();
        textFieldUserName = new TextField();
        bttnVerify = new Button();
        label = new Label();
        imageView = new ImageView();
        loginHyper = new Hyperlink();
        datePickerDOB = new DatePicker();
        imageView0 = new ImageView();
        textFieldBalance = new TextField();
        imageView1 = new ImageView();
        dataAlert = new Label();
        anchorPane0 = new AnchorPane();
        imageView2 = new ImageView();
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

        textFieldUserName.setLayoutX(134.0);
        textFieldUserName.setLayoutY(228.0);
        textFieldUserName.setPrefHeight(48.0);
        textFieldUserName.setPrefWidth(211.0);
        textFieldUserName.setPromptText("User Name...");
        textFieldUserName.setStyle("-fx-background-color: transparent; -fx-border-color: #00000099; -fx-border-width: 0px 0px 2px 0px;");
        textFieldUserName.setFont(new Font(18.0));

        bttnVerify.setLayoutX(144.0);
        bttnVerify.setLayoutY(463.0);
        bttnVerify.setMnemonicParsing(false);
        bttnVerify.setOnAction(this::verifyData);
        bttnVerify.setPrefHeight(51.0);
        bttnVerify.setPrefWidth(159.0);
        bttnVerify.setStyle("-fx-background-color: #F6B63DD6; -fx-border-radius: 80;");
        bttnVerify.setText("Verify");
        bttnVerify.setTextFill(javafx.scene.paint.Color.WHITE);
        bttnVerify.setOpaqueInsets(new Insets(10.0, 0.0, 0.0, 0.0));
        bttnVerify.setFont(new Font(24.0));

        label.setLayoutX(79.0);
        label.setLayoutY(64.0);
        label.setPrefHeight(156.0);
        label.setPrefWidth(342.0);
        label.getStylesheets().add("/clientiwish/views/resetpassword/../resources/loginandregister/pic.css");
        label.setText("Security Questions");
        label.setFont(new Font("Aldhabi", 68.0));

        imageView.setFitHeight(32.0);
        imageView.setFitWidth(33.0);
        imageView.setLayoutX(95.0);
        imageView.setLayoutY(244.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("../resources/loginandregister/user-svgrepo-com.png").toExternalForm()));

        loginHyper.setLayoutX(186.0);
        loginHyper.setLayoutY(555.0);
        loginHyper.setOnAction(this::returnToLogin);
        loginHyper.setStyle("-: #F6B63DD6;");
        loginHyper.getStylesheets().add("/clientiwish/views/resetpassword/../resources/loginandregister/pic.css");
        loginHyper.setText("Return to login");
        loginHyper.setTextFill(javafx.scene.paint.Color.valueOf("#f6b63d"));

        datePickerDOB.setLayoutX(135.0);
        datePickerDOB.setLayoutY(291.0);
        datePickerDOB.setPrefHeight(41.0);
        datePickerDOB.setPrefWidth(210.0);
        datePickerDOB.setPromptText("choose your birthdate...");
        datePickerDOB.setStyle("-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-width: 0px 0px 2px 0px;");

        imageView0.setFitHeight(32.0);
        imageView0.setFitWidth(33.0);
        imageView0.setLayoutX(95.0);
        imageView0.setLayoutY(296.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("../resources/loginandregister/calendar.png").toExternalForm()));

        textFieldBalance.setLayoutX(134.0);
        textFieldBalance.setLayoutY(345.0);
        textFieldBalance.setPrefHeight(48.0);
        textFieldBalance.setPrefWidth(211.0);
        textFieldBalance.setPromptText("Balance....");
        textFieldBalance.setStyle("-fx-background-color: transparent; -fx-border-color: #00000099; -fx-border-width: 0px 0px 2px 0px;");
        textFieldBalance.setFont(new Font(18.0));

        imageView1.setFitHeight(32.0);
        imageView1.setFitWidth(33.0);
        imageView1.setLayoutX(95.0);
        imageView1.setLayoutY(353.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("../resources/loginandregister/wallet.png").toExternalForm()));

        dataAlert.setLayoutX(158.0);
        dataAlert.setLayoutY(426.0);
        dataAlert.setTextFill(javafx.scene.paint.Color.RED);
        dataAlert.setFont(new Font(15.0));
        setCenter(anchorPane);

        BorderPane.setAlignment(anchorPane0, javafx.geometry.Pos.CENTER);
        anchorPane0.setPrefHeight(600.0);
        anchorPane0.setPrefWidth(371.0);
        anchorPane0.setStyle("-fx-background-color: #ffffff;");

        imageView2.setFitHeight(756.0);
        imageView2.setFitWidth(572.0);
        imageView2.setLayoutX(-123.0);
        imageView2.setLayoutY(-19.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);
        imageView2.setRotate(13.0);
        imageView2.setImage(new Image(getClass().getResource("../resources/loginandregister/gift.jpg").toExternalForm()));

        dropShadow.setRadius(16.22);
        dropShadow.setWidth(45.88);
        imageView2.setEffect(dropShadow);
        setLeft(anchorPane0);

        anchorPane.getChildren().add(textFieldUserName);
        anchorPane.getChildren().add(bttnVerify);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(loginHyper);
        anchorPane.getChildren().add(datePickerDOB);
        anchorPane.getChildren().add(imageView0);
        anchorPane.getChildren().add(textFieldBalance);
        anchorPane.getChildren().add(imageView1);
        anchorPane.getChildren().add(dataAlert);
        anchorPane0.getChildren().add(imageView2);

    }

    protected abstract void verifyData(javafx.event.ActionEvent actionEvent);

    protected abstract void returnToLogin(javafx.event.ActionEvent actionEvent);

}
