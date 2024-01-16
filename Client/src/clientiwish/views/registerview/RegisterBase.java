package clientiwish.views.registerview;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public abstract class RegisterBase extends BorderPane {

    protected final AnchorPane anchorPane;
    protected final TextField txtfFieldEmail;
    protected final TextField txtFieldUniqueName;
    protected final TextField txtFieldUserFullName;
    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final Label label4;
    protected final Label label5;
    protected final Button bttnSignUp;
    protected final DatePicker datePickerDOB;
    protected final PasswordField passFieldUserPassword;
    protected final PasswordField passFieldConfirmPassword;
    protected final Label label6;
    protected final Label label7;
    protected final RadioButton radioBttnMale;
    protected final ToggleGroup gender;
    protected final RadioButton radioBttnFemale;
    protected final Hyperlink logHyper;
    protected final Label dataAlert;
    protected final Label confirmationAlert;
    protected final Label passwordAlert;
    protected final Label emailLabel;
    protected final AnchorPane anchorPane0;
    protected final ImageView imageView;
    protected final DropShadow dropShadow;

    public RegisterBase() {

        anchorPane = new AnchorPane();
        txtfFieldEmail = new TextField();
        txtFieldUniqueName = new TextField();
        txtFieldUserFullName = new TextField();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        label4 = new Label();
        label5 = new Label();
        bttnSignUp = new Button();
        datePickerDOB = new DatePicker();
        passFieldUserPassword = new PasswordField();
        passFieldConfirmPassword = new PasswordField();
        label6 = new Label();
        label7 = new Label();
        radioBttnMale = new RadioButton();
        gender = new ToggleGroup();
        radioBttnFemale = new RadioButton();
        logHyper = new Hyperlink();
        dataAlert = new Label();
        confirmationAlert = new Label();
        passwordAlert = new Label();
        emailLabel = new Label();
        anchorPane0 = new AnchorPane();
        imageView = new ImageView();
        dropShadow = new DropShadow();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(800.0);

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setMaxHeight(USE_PREF_SIZE);
        anchorPane.setMaxWidth(900.0);
        anchorPane.setPrefHeight(600.0);
        anchorPane.setPrefWidth(900.0);
        anchorPane.setStyle("-fx-background-color: #ffffff;");

        txtfFieldEmail.setLayoutX(74.0);
        txtfFieldEmail.setLayoutY(296.0);
        txtfFieldEmail.setPrefHeight(30.0);
        txtfFieldEmail.setPrefWidth(272.0);
        txtfFieldEmail.setPromptText("Enter your email....");
        txtfFieldEmail.setStyle("-fx-border-color: #000000; -fx-background-color: transparent; -fx-border-width: 0px 0px 2px 0px;");

        txtFieldUniqueName.setLayoutX(81.0);
        txtFieldUniqueName.setLayoutY(208.0);
        txtFieldUniqueName.setPrefHeight(30.0);
        txtFieldUniqueName.setPrefWidth(141.0);
        txtFieldUniqueName.setPromptText("Enter unique name ....");
        txtFieldUniqueName.setStyle("-fx-border-color: #000000; -fx-background-color: transparent; -fx-border-width: 0px 0px 2px 0px;");

        txtFieldUserFullName.setLayoutX(83.0);
        txtFieldUserFullName.setLayoutY(140.0);
        txtFieldUserFullName.setPrefHeight(30.0);
        txtFieldUserFullName.setPrefWidth(272.0);
        txtFieldUserFullName.setPromptText("Enter your full name...");
        txtFieldUserFullName.setStyle("-fx-border-color: #000000; -fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent;");

        label.setLayoutX(117.0);
        label.setLayoutY(-15.0);
        label.setPrefHeight(137.0);
        label.setPrefWidth(221.0);
        label.setText("SIGN UP");
        label.setFont(new Font("Aldhabi", 79.0));

        label0.setLayoutX(92.0);
        label0.setLayoutY(77.0);
        label0.setText("Join I-Wish to start sharing and reciving gifts");
        label0.setTextFill(javafx.scene.paint.Color.valueOf("#f5be5a"));
        label0.setFont(new Font("Baskerville Old Face", 13.0));

        label1.setLayoutX(81.0);
        label1.setLayoutY(115.0);
        label1.setPrefHeight(22.0);
        label1.setPrefWidth(152.0);
        label1.setText("Your Full Name");
        label1.setFont(new Font("Baskerville Old Face", 18.0));

        label2.setLayoutX(80.0);
        label2.setLayoutY(179.0);
        label2.setText("Unique Name");
        label2.setFont(new Font("Baskerville Old Face", 18.0));

        label3.setLayoutX(82.0);
        label3.setLayoutY(274.0);
        label3.setText("Email");
        label3.setFont(new Font("Baskerville Old Face", 18.0));

        label4.setLayoutX(71.0);
        label4.setLayoutY(348.0);
        label4.setText("Create a Password");
        label4.setFont(new Font("Baskerville Old Face", 18.0));

        label5.setLayoutX(70.0);
        label5.setLayoutY(428.0);
        label5.setText("Confirm Your Password");
        label5.setFont(new Font("Baskerville Old Face", 18.0));

        bttnSignUp.setLayoutX(136.0);
        bttnSignUp.setLayoutY(542.0);
        bttnSignUp.setMnemonicParsing(false);
        bttnSignUp.setOnAction(this::handleButtonAction);
        bttnSignUp.setPrefHeight(44.0);
        bttnSignUp.setPrefWidth(183.0);
        bttnSignUp.setStyle("-fx-background-color: #f6b63d;");
        bttnSignUp.setText("Sign up");
        bttnSignUp.setTextFill(javafx.scene.paint.Color.WHITE);
        bttnSignUp.setFont(new Font("Baskerville Old Face", 24.0));

        datePickerDOB.setLayoutX(246.0);
        datePickerDOB.setLayoutY(211.0);
        datePickerDOB.setPrefHeight(30.0);
        datePickerDOB.setPrefWidth(141.0);
        datePickerDOB.setPromptText("choose a date...");
        datePickerDOB.setStyle("-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-width: 0px 0px 2px 0px;");

        passFieldUserPassword.setLayoutX(74.0);
        passFieldUserPassword.setLayoutY(370.0);
        passFieldUserPassword.setPrefHeight(30.0);
        passFieldUserPassword.setPrefWidth(272.0);
        passFieldUserPassword.setPromptText("Enter your password...");
        passFieldUserPassword.setStyle("-fx-border-color: #000000; -fx-background-color: transparent; -fx-border-width: 0px 0px 2px 0px;");

        passFieldConfirmPassword.setLayoutX(77.0);
        passFieldConfirmPassword.setLayoutY(453.0);
        passFieldConfirmPassword.setPrefHeight(3.0);
        passFieldConfirmPassword.setPrefWidth(272.0);
        passFieldConfirmPassword.setPromptText("Confirm your password...");
        passFieldConfirmPassword.setStyle("-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-width: 0px 0px 2px 0px;");

        label6.setLayoutX(252.0);
        label6.setLayoutY(179.0);
        label6.setText("Birthdate");
        label6.setFont(new Font("Baskerville Old Face", 18.0));

        label7.setLayoutX(76.0);
        label7.setLayoutY(252.0);
        label7.setText("Gender");
        label7.setFont(new Font("Baskerville Old Face", 18.0));

        radioBttnMale.setLayoutX(162.0);
        radioBttnMale.setLayoutY(256.0);
        radioBttnMale.setMnemonicParsing(false);
        radioBttnMale.setOnAction(this::getGender);
        radioBttnMale.setText("Male");

        radioBttnMale.setToggleGroup(gender);

        radioBttnFemale.setLayoutX(273.0);
        radioBttnFemale.setLayoutY(256.0);
        radioBttnFemale.setMnemonicParsing(false);
        radioBttnFemale.setOnAction(this::getGender);
        radioBttnFemale.setText("Female");
        radioBttnFemale.setToggleGroup(gender);

        logHyper.setLayoutX(138.0);
        logHyper.setLayoutY(513.0);
        logHyper.setOnAction(this::handleLoginHyper);
        logHyper.setText("Already have an account, Sign in");
        logHyper.setTextFill(javafx.scene.paint.Color.valueOf("#f6b63d"));
        logHyper.setFont(new Font("Baskerville Old Face", 13.0));

        dataAlert.setLayoutX(148.0);
        dataAlert.setLayoutY(500.0);
        dataAlert.setTextFill(javafx.scene.paint.Color.RED);
        dataAlert.setFont(new Font("Baskerville Old Face", 15.0));

        confirmationAlert.setLayoutX(76.0);
        confirmationAlert.setLayoutY(483.0);
        confirmationAlert.setTextFill(javafx.scene.paint.Color.RED);
        confirmationAlert.setFont(new Font("Baskerville Old Face", 12.0));

        passwordAlert.setLayoutX(78.0);
        passwordAlert.setLayoutY(401.0);
        passwordAlert.setTextFill(javafx.scene.paint.Color.RED);
        passwordAlert.setFont(new Font("Baskerville Old Face", 12.0));

        emailLabel.setLayoutX(78.0);
        emailLabel.setLayoutY(331.0);
        emailLabel.setTextFill(javafx.scene.paint.Color.RED);
        emailLabel.setFont(new Font("Baskerville Old Face", 12.0));
        setCenter(anchorPane);

        BorderPane.setAlignment(anchorPane0, javafx.geometry.Pos.CENTER);
        anchorPane0.setPrefHeight(200.0);
        anchorPane0.setPrefWidth(200.0);
        anchorPane0.setStyle("-fx-background-color: #ffffff;");

        imageView.setFitHeight(730.0);
        imageView.setFitWidth(484.0);
        imageView.setLayoutX(-109.0);
        imageView.setLayoutY(6.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setRotate(13.0);
        imageView.setImage(new Image(getClass().getResource("../resources/loginandregister/gift.jpg").toExternalForm()));

        imageView.setEffect(dropShadow);
        setLeft(anchorPane0);

        anchorPane.getChildren().add(txtfFieldEmail);
        anchorPane.getChildren().add(txtFieldUniqueName);
        anchorPane.getChildren().add(txtFieldUserFullName);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(label1);
        anchorPane.getChildren().add(label2);
        anchorPane.getChildren().add(label3);
        anchorPane.getChildren().add(label4);
        anchorPane.getChildren().add(label5);
        anchorPane.getChildren().add(bttnSignUp);
        anchorPane.getChildren().add(datePickerDOB);
        anchorPane.getChildren().add(passFieldUserPassword);
        anchorPane.getChildren().add(passFieldConfirmPassword);
        anchorPane.getChildren().add(label6);
        anchorPane.getChildren().add(label7);
        anchorPane.getChildren().add(radioBttnMale);
        anchorPane.getChildren().add(radioBttnFemale);
        anchorPane.getChildren().add(logHyper);
        anchorPane.getChildren().add(dataAlert);
        anchorPane.getChildren().add(confirmationAlert);
        anchorPane.getChildren().add(passwordAlert);
        anchorPane.getChildren().add(emailLabel);
        anchorPane0.getChildren().add(imageView);

    }

    protected abstract void handleButtonAction(javafx.event.ActionEvent actionEvent);

    protected abstract void getGender(javafx.event.ActionEvent actionEvent);


    protected abstract void handleLoginHyper(javafx.event.ActionEvent actionEvent);

}
