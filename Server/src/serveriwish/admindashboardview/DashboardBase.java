package serveriwish.admindashboardview;

import java.lang.String;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public abstract class DashboardBase extends AnchorPane {

    protected final VBox vBox;
    protected final VBox vBox0;
    protected final Label label;
    protected final HBox hBox;
    protected final VBox vBox1;
    protected final HBox hBox0;
    protected final Label label0;
    protected final Pane pane;
    protected final ImageView imageView;
    protected final Label labelUsers;
    protected final VBox vBox2;
    protected final HBox hBox1;
    protected final Label label1;
    protected final Pane pane0;
    protected final ImageView imageView0;
    protected final Label labelItems;
    protected final Line line;
    protected final HBox hBox2;
    protected final VBox vBox3;
    protected final Label label2;
    protected final ToggleButton bttnServerStatus;
    protected final Line line0;
    protected final VBox vBox4;
    protected final Label label3;
    protected final HBox hBox3;
    protected final Label label4;
    protected final TextField txtFieldItemName;
    protected final HBox hBox4;
    protected final Label label5;
    protected final TextField txtFieldItemDescription;
    protected final HBox hBox5;
    protected final Label label6;
    protected final TextField txtFieldItemPrice;
    protected final Button bttnAddItem;

    public DashboardBase() {

        vBox = new VBox();
        vBox0 = new VBox();
        label = new Label();
        hBox = new HBox();
        vBox1 = new VBox();
        hBox0 = new HBox();
        label0 = new Label();
        pane = new Pane();
        imageView = new ImageView();
        labelUsers = new Label();
        vBox2 = new VBox();
        hBox1 = new HBox();
        label1 = new Label();
        pane0 = new Pane();
        imageView0 = new ImageView();
        labelItems = new Label();
        line = new Line();
        hBox2 = new HBox();
        vBox3 = new VBox();
        label2 = new Label();
        bttnServerStatus = new ToggleButton();
        line0 = new Line();
        vBox4 = new VBox();
        label3 = new Label();
        hBox3 = new HBox();
        label4 = new Label();
        txtFieldItemName = new TextField();
        hBox4 = new HBox();
        label5 = new Label();
        txtFieldItemDescription = new TextField();
        hBox5 = new HBox();
        label6 = new Label();
        txtFieldItemPrice = new TextField();
        bttnAddItem = new Button();

        setMinWidth(950.0);

        AnchorPane.setBottomAnchor(vBox, 0.0);
        AnchorPane.setLeftAnchor(vBox, 0.0);
        AnchorPane.setRightAnchor(vBox, 0.0);
        AnchorPane.setTopAnchor(vBox, 0.0);
        vBox.setLayoutY(-5.0);
        vBox.setMaxHeight(USE_PREF_SIZE);
        vBox.setPrefHeight(727.0);

        VBox.setVgrow(vBox0, javafx.scene.layout.Priority.ALWAYS);
        vBox0.setPrefHeight(699.0);
        vBox0.setPrefWidth(891.0);

        label.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label.setText("Dashboard");
        VBox.setMargin(label, new Insets(0.0, 0.0, 5.0, 0.0));
        label.setFont(new Font("Segoe UI Semibold", 18.0));

        hBox.setAlignment(javafx.geometry.Pos.CENTER);
        hBox.setMinHeight(USE_PREF_SIZE);
        hBox.setPrefHeight(120.0);
        hBox.setPrefWidth(200.0);
        hBox.setSpacing(220.0);

        vBox1.setAlignment(javafx.geometry.Pos.CENTER);
        vBox1.setPrefHeight(200.0);
        vBox1.setPrefWidth(240.0);
        vBox1.getStyleClass().add("card");
        vBox1.getStylesheets().add("/serveriwish/admindashboardview/../resources/admindashboard/style.css");

        hBox0.setPrefHeight(43.0);
        hBox0.setPrefWidth(240.0);

        label0.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label0.setPrefHeight(27.0);
        label0.setPrefWidth(177.0);
        label0.setText("USERS");
        label0.setFont(new Font("Segoe UI Semilight", 18.0));
        HBox.setMargin(label0, new Insets(10.0, 0.0, 0.0, 10.0));

        pane.setPrefHeight(200.0);
        pane.setPrefWidth(200.0);

        imageView.setDisable(true);
        imageView.setFitHeight(52.0);
        imageView.setFitWidth(48.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("../resources/admindashboard/icons8-group-of-business-team-leading-for-sales-and-finance-96.png").toExternalForm()));
        HBox.setMargin(imageView, new Insets(0.0, 20.0, 0.0, 0.0));

        labelUsers.setOnKeyPressed(this::getAllUsers);
        labelUsers.setPrefHeight(76.0);
        labelUsers.setPrefWidth(242.0);
        labelUsers.setText("100");
        labelUsers.setFont(new Font("Segoe UI Black", 40.0));
        VBox.setMargin(labelUsers, new Insets(0.0, 0.0, 0.0, 25.0));
        HBox.setMargin(vBox1, new Insets(0.0, 0.0, 0.0, -50.0));

        vBox2.setAlignment(javafx.geometry.Pos.CENTER);
        vBox2.setLayoutX(10.0);
        vBox2.setLayoutY(10.0);
        vBox2.setPrefHeight(200.0);
        vBox2.setPrefWidth(240.0);
        vBox2.getStyleClass().add("card");
        vBox2.getStylesheets().add("/serveriwish/admindashboardview/../resources/admindashboard/style.css");

        hBox1.setPrefHeight(43.0);
        hBox1.setPrefWidth(240.0);

        label1.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label1.setPrefHeight(27.0);
        label1.setPrefWidth(177.0);
        label1.setText("ITEMS");
        label1.setFont(new Font("Segoe UI Semilight", 18.0));
        HBox.setMargin(label1, new Insets(10.0, 0.0, 0.0, 10.0));

        pane0.setPrefHeight(200.0);
        pane0.setPrefWidth(200.0);

        imageView0.setDisable(true);
        imageView0.setFitHeight(52.0);
        imageView0.setFitWidth(48.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("../resources/admindashboard/icons8-gifts-64.png").toExternalForm()));
        HBox.setMargin(imageView0, new Insets(0.0, 20.0, 0.0, 0.0));

        labelItems.setOnKeyPressed(this::getAllItems);
        labelItems.setPrefHeight(76.0);
        labelItems.setPrefWidth(242.0);
        labelItems.setText("100");
        labelItems.setFont(new Font("Segoe UI Black", 40.0));
        VBox.setMargin(labelItems, new Insets(0.0, 0.0, 0.0, 25.0));
        VBox.setMargin(hBox, new Insets(20.0, 0.0, 0.0, 0.0));

        line.setEndX(405.29290771484375);
        line.setEndY(-0.41421353816986084);
        line.setStartX(-100.0);
        VBox.setMargin(line, new Insets(20.0, 0.0, 0.0, 210.0));
        VBox.setMargin(vBox0, new Insets(30.0));

        AnchorPane.setBottomAnchor(hBox2, 100.0);
        AnchorPane.setLeftAnchor(hBox2, 0.0);
        AnchorPane.setTopAnchor(hBox2, 200.0);
        hBox2.setPrefHeight(322.0);
        hBox2.setPrefWidth(950.0);

        vBox3.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        vBox3.setMaxHeight(USE_PREF_SIZE);
        vBox3.setMaxWidth(USE_PREF_SIZE);
        vBox3.setMinWidth(USE_PREF_SIZE);
        vBox3.setPrefHeight(300.0);
        vBox3.setPrefWidth(360.0);
        vBox3.setSpacing(30.0);
        vBox3.getStyleClass().add("card");
        vBox3.getStylesheets().add("/serveriwish/admindashboardview/../resources/admindashboard/style.css");
        HBox.setMargin(vBox3, new Insets(0.0, 0.0, 0.0, 70.0));

        label2.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label2.setText("Server Status");
        label2.setFont(new Font("Segoe UI Semibold", 24.0));
        VBox.setMargin(label2, new Insets(20.0, 0.0, 0.0, 0.0));

        bttnServerStatus.setMnemonicParsing(false);
        bttnServerStatus.setOnAction(this::changeServerSatus);
        bttnServerStatus.setPrefWidth(163.0);
        bttnServerStatus.setText("ON");
        bttnServerStatus.setTextFill(javafx.scene.paint.Color.WHITE);
        bttnServerStatus.setFont(new Font("Inter SemiBold", 18.0));
        VBox.setMargin(bttnServerStatus, new Insets(70.0, 0.0, 0.0, 0.0));

        line0.setEndX(-98.75733947753906);
        line0.setEndY(238.95957946777344);
        line0.setStartX(-100.0);
        HBox.setMargin(line0, new Insets(40.0, 0.0, 0.0, 50.0));

        vBox4.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        vBox4.setLayoutX(100.0);
        vBox4.setLayoutY(10.0);
        vBox4.setMaxHeight(USE_PREF_SIZE);
        vBox4.setMaxWidth(USE_PREF_SIZE);
        vBox4.setMinWidth(USE_PREF_SIZE);
        vBox4.setPrefHeight(300.0);
        vBox4.setPrefWidth(360.0);
        vBox4.getStyleClass().add("card");
        vBox4.getStylesheets().add("/serveriwish/admindashboardview/../resources/admindashboard/style.css");

        label3.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label3.setText("Add Item");
        label3.setFont(new Font("Segoe UI Semibold", 24.0));
        VBox.setMargin(label3, new Insets(20.0, 0.0, 20.0, 0.0));

        hBox3.setPrefHeight(73.0);
        hBox3.setPrefWidth(340.0);
        hBox3.setSpacing(48.0);

        label4.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label4.setText("Item name");
        label4.setFont(new Font("Segoe UI Semibold", 17.0));

        txtFieldItemName.setPrefHeight(25.0);
        txtFieldItemName.setPrefWidth(180.0);
        txtFieldItemName.setPromptText("item name...");
        VBox.setMargin(hBox3, new Insets(0.0, 0.0, 0.0, 20.0));

        hBox4.setPrefHeight(100.0);
        hBox4.setPrefWidth(347.0);
        VBox.setMargin(hBox4, new Insets(-20.0, 0.0, 10.0, 20.0));

        label5.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label5.setPrefHeight(26.0);
        label5.setPrefWidth(127.0);
        label5.setText("Description");
        label5.setFont(new Font("Segoe UI Semibold", 17.0));

        txtFieldItemDescription.setPrefHeight(68.0);
        txtFieldItemDescription.setPrefWidth(185.0);
        txtFieldItemDescription.setPromptText("Item description.....");
        HBox.setMargin(txtFieldItemDescription, new Insets(0.0));

        hBox5.setPrefHeight(57.0);
        hBox5.setPrefWidth(345.0);
        hBox5.setSpacing(70.0);

        label6.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label6.setText("Price");
        label6.setFont(new Font("Segoe UI Semibold", 17.0));

        txtFieldItemPrice.setPrefHeight(25.0);
        txtFieldItemPrice.setPrefWidth(185.0);
        txtFieldItemPrice.setPromptText("item price");
        HBox.setMargin(txtFieldItemPrice, new Insets(0.0, 0.0, 0.0, 20.0));
        VBox.setMargin(hBox5, new Insets(-20.0, 0.0, 0.0, 20.0));

        bttnAddItem.setMnemonicParsing(false);
        bttnAddItem.setOnAction(this::addUserHandler);
        bttnAddItem.getStylesheets().add("/serveriwish/admindashboardview/../resources/admindashboard/style.css");
        bttnAddItem.setText("ADD");
        VBox.setMargin(bttnAddItem, new Insets(0.0, 0.0, 20.0, 0.0));
        HBox.setMargin(vBox4, new Insets(0.0, 0.0, 0.0, 50.0));
        hBox2.setPadding(new Insets(50.0, 0.0, 0.0, 0.0));

        vBox0.getChildren().add(label);
        hBox0.getChildren().add(label0);
        hBox0.getChildren().add(pane);
        hBox0.getChildren().add(imageView);
        vBox1.getChildren().add(hBox0);
        vBox1.getChildren().add(labelUsers);
        hBox.getChildren().add(vBox1);
        hBox1.getChildren().add(label1);
        hBox1.getChildren().add(pane0);
        hBox1.getChildren().add(imageView0);
        vBox2.getChildren().add(hBox1);
        vBox2.getChildren().add(labelItems);
        hBox.getChildren().add(vBox2);
        vBox0.getChildren().add(hBox);
        vBox0.getChildren().add(line);
        vBox.getChildren().add(vBox0);
        getChildren().add(vBox);
        vBox3.getChildren().add(label2);
        vBox3.getChildren().add(bttnServerStatus);
        hBox2.getChildren().add(vBox3);
        hBox2.getChildren().add(line0);
        vBox4.getChildren().add(label3);
        hBox3.getChildren().add(label4);
        hBox3.getChildren().add(txtFieldItemName);
        vBox4.getChildren().add(hBox3);
        hBox4.getChildren().add(label5);
        hBox4.getChildren().add(txtFieldItemDescription);
        vBox4.getChildren().add(hBox4);
        hBox5.getChildren().add(label6);
        hBox5.getChildren().add(txtFieldItemPrice);
        vBox4.getChildren().add(hBox5);
        vBox4.getChildren().add(bttnAddItem);
        hBox2.getChildren().add(vBox4);
        getChildren().add(hBox2);

    }

    protected abstract void getAllUsers(javafx.scene.input.KeyEvent keyEvent);

    protected abstract void getAllItems(javafx.scene.input.KeyEvent keyEvent);

    protected abstract void changeServerSatus(javafx.event.ActionEvent actionEvent);

    protected abstract void addUserHandler(javafx.event.ActionEvent actionEvent);

}
