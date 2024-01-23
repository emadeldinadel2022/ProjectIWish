package clientiwish.views.userprofileview.editbalance;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public abstract class EditBalanceBase extends AnchorPane {

    protected final Label enterBalanceLabel;
    protected final TextField enterBalanceTextField;
    protected final Button confirmButton;
    protected final Label balanceSuccessLabel;
    protected final Label balanceFailureLabel;

    public EditBalanceBase() {

        enterBalanceLabel = new Label();
        enterBalanceTextField = new TextField();
        confirmButton = new Button();
        balanceSuccessLabel = new Label();
        balanceFailureLabel = new Label();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        enterBalanceLabel.setAlignment(javafx.geometry.Pos.CENTER);
        enterBalanceLabel.setLayoutX(51.0);
        enterBalanceLabel.setLayoutY(182.0);
        enterBalanceLabel.setPrefHeight(36.0);
        enterBalanceLabel.setPrefWidth(208.0);
        enterBalanceLabel.setText("Enter your new balance");
        enterBalanceLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        enterBalanceLabel.setFont(new Font("System Bold", 18.0));

        enterBalanceTextField.setLayoutX(301.0);
        enterBalanceTextField.setLayoutY(185.0);
        enterBalanceTextField.setOnAction(this::handleEnterBalanceTextField);
        enterBalanceTextField.setPrefHeight(30.0);
        enterBalanceTextField.setPrefWidth(249.0);

        confirmButton.setLayoutX(249.0);
        confirmButton.setLayoutY(251.0);
        confirmButton.setMnemonicParsing(false);
        confirmButton.setOnAction(this::handleConfirmButton);
        confirmButton.setPrefHeight(39.0);
        confirmButton.setPrefWidth(103.0);
        confirmButton.getStyleClass().add("editprofilebutton-color");
        confirmButton.getStylesheets().add("/clientiwish/views/userprofileview/editbalance/../../resources/userprofile/style.css");
        confirmButton.setText("Confirm");
        confirmButton.setFont(new Font("System Bold", 18.0));

        balanceSuccessLabel.setAlignment(javafx.geometry.Pos.CENTER);
        balanceSuccessLabel.setLayoutX(245.0);
        balanceSuccessLabel.setLayoutY(295.0);
        balanceSuccessLabel.setPrefHeight(17.0);
        balanceSuccessLabel.setPrefWidth(111.0);
        balanceSuccessLabel.setTextFill(javafx.scene.paint.Color.valueOf("#43bc1b"));
        balanceSuccessLabel.setFont(new Font(14.0));

        balanceFailureLabel.setAlignment(javafx.geometry.Pos.CENTER);
        balanceFailureLabel.setLayoutX(245.0);
        balanceFailureLabel.setLayoutY(295.0);
        balanceFailureLabel.setPrefHeight(17.0);
        balanceFailureLabel.setPrefWidth(111.0);
        balanceFailureLabel.setTextFill(javafx.scene.paint.Color.RED);
        balanceFailureLabel.setFont(new Font(14.0));

        getChildren().add(enterBalanceLabel);
        getChildren().add(enterBalanceTextField);
        getChildren().add(confirmButton);
        getChildren().add(balanceSuccessLabel);
        getChildren().add(balanceFailureLabel);

    }

    protected abstract void handleEnterBalanceTextField(javafx.event.ActionEvent actionEvent);

    protected abstract void handleConfirmButton(javafx.event.ActionEvent actionEvent);

}
