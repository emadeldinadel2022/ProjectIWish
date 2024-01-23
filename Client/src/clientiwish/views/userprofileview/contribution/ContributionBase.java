package clientiwish.views.userprofileview.contribution;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public abstract class ContributionBase extends AnchorPane {

    protected final TableView contributionTableView;
    protected final TableColumn ownerName;
    protected final TableColumn itemNumber;
    protected final TableColumn itemName;
    protected final TableColumn itemPrice;
    protected final TableColumn contributionAmount;
    protected final Label labelViewName;
    protected final ImageView iconImageView;
    protected final RadioButton redioBttnComplete;
    protected final ToggleGroup contribuiton;
    protected final RadioButton redioBttnIncomplete;

    public ContributionBase() {

        contributionTableView = new TableView();
        ownerName = new TableColumn();
        itemNumber = new TableColumn();
        itemName = new TableColumn();
        itemPrice = new TableColumn();
        contributionAmount = new TableColumn();
        labelViewName = new Label();
        iconImageView = new ImageView();
        redioBttnComplete = new RadioButton();
        contribuiton = new ToggleGroup();
        redioBttnIncomplete = new RadioButton();

        setId("AnchorPane");
        setPrefHeight(653.0);
        setPrefWidth(815.0);

        contributionTableView.setLayoutX(-8.0);
        contributionTableView.setLayoutY(124.0);
        contributionTableView.setPrefHeight(528.0);
        contributionTableView.setPrefWidth(815.0);

        ownerName.setPrefWidth(163.0);
        ownerName.setText("Friend Name");

        itemNumber.setPrefWidth(163.0);
        itemNumber.setText("Item Number");

        itemName.setPrefWidth(163.0);
        itemName.setText("Item Name");

        itemPrice.setPrefWidth(163.0);
        itemPrice.setText("Item Price");

        contributionAmount.setPrefWidth(163.0);
        contributionAmount.setText("Contribution Amount");

        labelViewName.setLayoutX(68.0);
        labelViewName.setLayoutY(9.0);
        labelViewName.setPrefHeight(54.0);
        labelViewName.setPrefWidth(223.0);
        labelViewName.setText("Contributions");
        labelViewName.setTextFill(javafx.scene.paint.Color.valueOf("#f6b63d"));
        labelViewName.setFont(new Font("System Bold", 30.0));

        iconImageView.setFitHeight(53.0);
        iconImageView.setFitWidth(47.0);
        iconImageView.setLayoutX(14.0);
        iconImageView.setLayoutY(9.0);
        iconImageView.setPickOnBounds(true);
        iconImageView.setPreserveRatio(true);
        iconImageView.setImage(new Image(getClass().getResource("../../resources/userprofile/notificationyellow.png").toExternalForm()));

        redioBttnComplete.setLayoutX(194.0);
        redioBttnComplete.setLayoutY(75.0);
        redioBttnComplete.setMnemonicParsing(false);
        redioBttnComplete.setOnAction(this::getCompleteContribution);
        redioBttnComplete.setText("Complete Contribution");

        redioBttnComplete.setToggleGroup(contribuiton);

        redioBttnIncomplete.setLayoutX(495.0);
        redioBttnIncomplete.setLayoutY(75.0);
        redioBttnIncomplete.setMnemonicParsing(false);
        redioBttnIncomplete.setOnAction(this::getIncompleteContribution);
        redioBttnIncomplete.setText("Incomplete Contribution");
        redioBttnIncomplete.setToggleGroup(contribuiton);

        contributionTableView.getColumns().add(ownerName);
        contributionTableView.getColumns().add(itemNumber);
        contributionTableView.getColumns().add(itemName);
        contributionTableView.getColumns().add(itemPrice);
        contributionTableView.getColumns().add(contributionAmount);
        getChildren().add(contributionTableView);
        getChildren().add(labelViewName);
        getChildren().add(iconImageView);
        getChildren().add(redioBttnComplete);
        getChildren().add(redioBttnIncomplete);

    }

    protected abstract void getCompleteContribution(javafx.event.ActionEvent actionEvent);

    protected abstract void getIncompleteContribution(javafx.event.ActionEvent actionEvent);

}
