package serveriwish.admindashboardview;

import java.lang.String;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public abstract class AdminDashboardBase extends BorderPane {

    protected final VBox vBox;
    protected final HBox hBox;
    protected final ImageView imageView;
    protected final Label label;
    protected final HBox hBox0;
    protected final ImageView imageView0;
    protected final Label label0;
    protected final HBox hBox1;
    protected final ImageView imageView1;
    protected final Label label1;
    protected final VBox vBox0;
    protected final HBox hBox2;
    protected final Pane pane;
    protected final HBox hBox3;
    protected final ImageView imageView2;
    protected final TextField textField;
    protected final ImageView imageView3;
    protected final ImageView imageView4;
    protected final VBox vBox1;
    protected final VBox vBox2;
    protected final Label label2;
    protected final HBox hBox4;
    protected final VBox vBox3;
    protected final HBox hBox5;
    protected final Label label3;
    protected final Pane pane0;
    protected final ImageView imageView5;
    protected final Label label4;
    protected final VBox vBox4;
    protected final HBox hBox6;
    protected final Label label5;
    protected final Pane pane1;
    protected final ImageView imageView6;
    protected final Label label6;
    protected final Line line;
    protected final HBox hBox7;
    protected final VBox vBox5;
    protected final Label label7;
    protected final ToggleButton bttnServerStatus;
    protected final Line line0;
    protected final VBox vBox6;
    protected final Label label8;
    protected final HBox hBox8;
    protected final Label label9;
    protected final TextField textField0;
    protected final HBox hBox9;
    protected final Label label10;
    protected final TextField textField1;
    protected final HBox hBox10;
    protected final Label label11;
    protected final TextField textField2;
    protected final HBox hBox11;
    protected final Label label12;
    protected final TextField textField3;
    protected final HBox hBox12;
    protected final Label label13;
    protected final TextField textField4;
    protected final HBox hBox13;
    protected final Label label14;
    protected final TextField textField5;
    protected final Button button;

    public AdminDashboardBase() {

        vBox = new VBox();
        hBox = new HBox();
        imageView = new ImageView();
        label = new Label();
        hBox0 = new HBox();
        imageView0 = new ImageView();
        label0 = new Label();
        hBox1 = new HBox();
        imageView1 = new ImageView();
        label1 = new Label();
        vBox0 = new VBox();
        hBox2 = new HBox();
        pane = new Pane();
        hBox3 = new HBox();
        imageView2 = new ImageView();
        textField = new TextField();
        imageView3 = new ImageView();
        imageView4 = new ImageView();
        vBox1 = new VBox();
        vBox2 = new VBox();
        label2 = new Label();
        hBox4 = new HBox();
        vBox3 = new VBox();
        hBox5 = new HBox();
        label3 = new Label();
        pane0 = new Pane();
        imageView5 = new ImageView();
        label4 = new Label();
        vBox4 = new VBox();
        hBox6 = new HBox();
        label5 = new Label();
        pane1 = new Pane();
        imageView6 = new ImageView();
        label6 = new Label();
        line = new Line();
        hBox7 = new HBox();
        vBox5 = new VBox();
        label7 = new Label();
        bttnServerStatus = new ToggleButton();
        line0 = new Line();
        vBox6 = new VBox();
        label8 = new Label();
        hBox8 = new HBox();
        label9 = new Label();
        textField0 = new TextField();
        hBox9 = new HBox();
        label10 = new Label();
        textField1 = new TextField();
        hBox10 = new HBox();
        label11 = new Label();
        textField2 = new TextField();
        hBox11 = new HBox();
        label12 = new Label();
        textField3 = new TextField();
        hBox12 = new HBox();
        label13 = new Label();
        textField4 = new TextField();
        hBox13 = new HBox();
        label14 = new Label();
        textField5 = new TextField();
        button = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(800.0);
        setPrefWidth(1200.0);

        vBox.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        vBox.setFillWidth(false);
        vBox.setMaxHeight(USE_PREF_SIZE);
        vBox.setPrefHeight(1200.0);
        vBox.setPrefWidth(250.0);
        vBox.setSpacing(50.0);
        vBox.getStyleClass().add("white_background");
        vBox.getStylesheets().add("/serveriwish/admindashboardview/../resources/admindashboard/style.css");

        hBox.setAlignment(javafx.geometry.Pos.CENTER);
        hBox.setPrefHeight(45.0);
        hBox.setPrefWidth(250.0);

        imageView.setDisable(true);
        imageView.setFitHeight(37.0);
        imageView.setFitWidth(47.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("../resources/admindashboard/icons8-app-50.png").toExternalForm()));
        HBox.setMargin(imageView, new Insets(5.0, 0.0, 0.0, 5.0));

        label.setPrefHeight(44.0);
        label.setPrefWidth(150.0);
        label.setText("DASHBOARD");
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label.setTextOverrun(javafx.scene.control.OverrunStyle.CENTER_ELLIPSIS);
        label.setFont(new Font("Segoe UI Semibold", 23.0));
        HBox.setMargin(label, new Insets(0.0, 0.0, 0.0, 20.0));

        hBox0.setAlignment(javafx.geometry.Pos.CENTER);
        hBox0.setLayoutX(10.0);
        hBox0.setLayoutY(388.0);
        hBox0.setPrefHeight(45.0);
        hBox0.setPrefWidth(250.0);

        imageView0.setDisable(true);
        imageView0.setFitHeight(37.0);
        imageView0.setFitWidth(47.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("../resources/admindashboard/icons8-users-96.png").toExternalForm()));
        HBox.setMargin(imageView0, new Insets(5.0, 0.0, 0.0, 5.0));

        label0.setPrefHeight(44.0);
        label0.setPrefWidth(150.0);
        label0.setText("USERS");
        label0.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label0.setFont(new Font("Segoe UI Light", 20.0));
        HBox.setMargin(label0, new Insets(0.0, 0.0, 0.0, 20.0));

        hBox1.setAlignment(javafx.geometry.Pos.CENTER);
        hBox1.setLayoutX(10.0);
        hBox1.setLayoutY(410.0);
        hBox1.setPrefHeight(45.0);
        hBox1.setPrefWidth(250.0);

        imageView1.setDisable(true);
        imageView1.setFitHeight(37.0);
        imageView1.setFitWidth(47.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("../resources/admindashboard/icons8-user-96.png").toExternalForm()));
        HBox.setMargin(imageView1, new Insets(5.0, 0.0, 0.0, 5.0));

        label1.setPrefHeight(44.0);
        label1.setPrefWidth(150.0);
        label1.setText("Account");
        label1.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label1.setFont(new Font("Segoe UI Light", 20.0));
        HBox.setMargin(label1, new Insets(0.0, 0.0, 0.0, 20.0));
        setLeft(vBox);

        BorderPane.setAlignment(vBox0, javafx.geometry.Pos.CENTER);
        vBox0.setPrefHeight(200.0);
        vBox0.setPrefWidth(100.0);

        hBox2.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        hBox2.setPrefHeight(55.0);
        hBox2.setPrefWidth(200.0);
        hBox2.getStyleClass().add("white_background");
        hBox2.getStylesheets().add("/serveriwish/admindashboardview/../resources/admindashboard/style.css");

        HBox.setHgrow(pane, javafx.scene.layout.Priority.ALWAYS);
        pane.setPrefHeight(200.0);
        pane.setPrefWidth(200.0);

        hBox3.setAlignment(javafx.geometry.Pos.CENTER);
        hBox3.setMaxHeight(USE_PREF_SIZE);
        hBox3.setPrefHeight(35.0);
        hBox3.setPrefWidth(250.0);
        hBox3.getStyleClass().add("search-container");
        hBox3.getStylesheets().add("/serveriwish/admindashboardview/../resources/admindashboard/style.css");

        imageView2.setDisable(true);
        imageView2.setFitHeight(37.0);
        imageView2.setFitWidth(47.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);
        imageView2.setImage(new Image(getClass().getResource("../resources/admindashboard/icons8-search-128.png").toExternalForm()));

        textField.setPrefHeight(31.0);
        textField.setPrefWidth(178.0);
        textField.setPromptText("Search....");
        textField.getStyleClass().add("transparent");
        textField.getStylesheets().add("/serveriwish/admindashboardview/../resources/admindashboard/style.css");
        textField.setFont(new Font(15.0));
        HBox.setMargin(textField, new Insets(0.0));

        imageView3.setDisable(true);
        imageView3.setFitHeight(37.0);
        imageView3.setFitWidth(47.0);
        imageView3.setPickOnBounds(true);
        imageView3.setPreserveRatio(true);
        imageView3.setImage(new Image(getClass().getResource("../resources/admindashboard/icons8-admin-96.png").toExternalForm()));
        HBox.setMargin(imageView3, new Insets(0.0, 0.0, 0.0, 20.0));

        imageView4.setDisable(true);
        imageView4.setFitHeight(29.0);
        imageView4.setFitWidth(18.0);
        imageView4.setPickOnBounds(true);
        imageView4.setPreserveRatio(true);
        imageView4.setImage(new Image(getClass().getResource("../resources/admindashboard/icons8-arrow-down-96.png").toExternalForm()));
        HBox.setMargin(imageView4, new Insets(15.0, 20.0, 0.0, 0.0));

        VBox.setVgrow(vBox1, javafx.scene.layout.Priority.ALWAYS);
        vBox1.setMaxHeight(USE_PREF_SIZE);
        vBox1.setPrefHeight(120.0);
        vBox1.setPrefWidth(100.0);

        VBox.setVgrow(vBox2, javafx.scene.layout.Priority.ALWAYS);
        vBox2.setPrefHeight(120.0);
        vBox2.setPrefWidth(100.0);

        label2.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label2.setText("Dashboard");
        VBox.setMargin(label2, new Insets(0.0, 0.0, 5.0, 0.0));
        label2.setFont(new Font("Segoe UI Semibold", 18.0));

        hBox4.setAlignment(javafx.geometry.Pos.CENTER);
        hBox4.setMinHeight(USE_PREF_SIZE);
        hBox4.setPrefHeight(120.0);
        hBox4.setPrefWidth(200.0);
        hBox4.setSpacing(220.0);

        vBox3.setAlignment(javafx.geometry.Pos.CENTER);
        vBox3.setPrefHeight(200.0);
        vBox3.setPrefWidth(240.0);
        vBox3.getStyleClass().add("card");
        vBox3.getStylesheets().add("/serveriwish/admindashboardview/../resources/admindashboard/style.css");

        hBox5.setPrefHeight(43.0);
        hBox5.setPrefWidth(240.0);

        label3.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label3.setPrefHeight(27.0);
        label3.setPrefWidth(177.0);
        label3.setText("USERS");
        label3.setFont(new Font("Segoe UI Semilight", 18.0));
        HBox.setMargin(label3, new Insets(10.0, 0.0, 0.0, 10.0));

        pane0.setPrefHeight(200.0);
        pane0.setPrefWidth(200.0);

        imageView5.setDisable(true);
        imageView5.setFitHeight(52.0);
        imageView5.setFitWidth(48.0);
        imageView5.setPickOnBounds(true);
        imageView5.setPreserveRatio(true);
        imageView5.setImage(new Image(getClass().getResource("../resources/admindashboard/icons8-group-of-business-team-leading-for-sales-and-finance-96.png").toExternalForm()));
        HBox.setMargin(imageView5, new Insets(0.0, 20.0, 0.0, 0.0));

        label4.setPrefHeight(76.0);
        label4.setPrefWidth(242.0);
        label4.setText("100");
        label4.setFont(new Font("Segoe UI Black", 40.0));
        VBox.setMargin(label4, new Insets(0.0, 0.0, 0.0, 25.0));
        HBox.setMargin(vBox3, new Insets(0.0, 0.0, 0.0, -150.0));

        vBox4.setAlignment(javafx.geometry.Pos.CENTER);
        vBox4.setLayoutX(10.0);
        vBox4.setLayoutY(10.0);
        vBox4.setPrefHeight(200.0);
        vBox4.setPrefWidth(240.0);
        vBox4.getStyleClass().add("card");
        vBox4.getStylesheets().add("/serveriwish/admindashboardview/../resources/admindashboard/style.css");

        hBox6.setPrefHeight(43.0);
        hBox6.setPrefWidth(240.0);

        label5.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label5.setPrefHeight(27.0);
        label5.setPrefWidth(177.0);
        label5.setText("ITEMS");
        label5.setFont(new Font("Segoe UI Semilight", 18.0));
        HBox.setMargin(label5, new Insets(10.0, 0.0, 0.0, 10.0));

        pane1.setPrefHeight(200.0);
        pane1.setPrefWidth(200.0);

        imageView6.setDisable(true);
        imageView6.setFitHeight(52.0);
        imageView6.setFitWidth(48.0);
        imageView6.setPickOnBounds(true);
        imageView6.setPreserveRatio(true);
        imageView6.setImage(new Image(getClass().getResource("../resources/admindashboard/icons8-gifts-64.png").toExternalForm()));
        HBox.setMargin(imageView6, new Insets(0.0, 20.0, 0.0, 0.0));

        label6.setPrefHeight(76.0);
        label6.setPrefWidth(242.0);
        label6.setText("100");
        label6.setFont(new Font("Segoe UI Black", 40.0));
        VBox.setMargin(label6, new Insets(0.0, 0.0, 0.0, 25.0));
        VBox.setMargin(vBox2, new Insets(30.0));

        line.setEndX(405.29290771484375);
        line.setEndY(-0.41421353816986084);
        line.setStartX(-100.0);
        VBox.setMargin(line, new Insets(0.0, 0.0, 0.0, 210.0));

        hBox7.setPrefHeight(322.0);
        hBox7.setPrefWidth(950.0);

        vBox5.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        vBox5.setMaxWidth(USE_PREF_SIZE);
        vBox5.setMinWidth(USE_PREF_SIZE);
        vBox5.setPrefHeight(322.0);
        vBox5.setPrefWidth(360.0);
        vBox5.setSpacing(30.0);
        vBox5.getStyleClass().add("card");
        vBox5.getStylesheets().add("/serveriwish/admindashboardview/../resources/admindashboard/style.css");
        HBox.setMargin(vBox5, new Insets(0.0, 0.0, 0.0, 70.0));

        label7.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label7.setText("Server Status");
        label7.setFont(new Font("Segoe UI Semibold", 24.0));
        VBox.setMargin(label7, new Insets(5.0, 0.0, 0.0, 0.0));

        bttnServerStatus.setMnemonicParsing(false);
        bttnServerStatus.setOnAction(this::handltServerStatus);
        bttnServerStatus.setPrefWidth(163.0);
        bttnServerStatus.getStylesheets().add("/serveriwish/admindashboardview/../resources/admindashboard/style.css");
        bttnServerStatus.setText("ON");
        bttnServerStatus.setTextFill(javafx.scene.paint.Color.WHITE);
        bttnServerStatus.setFont(new Font("Inter SemiBold", 18.0));
        VBox.setMargin(bttnServerStatus, new Insets(70.0, 0.0, 0.0, 0.0));

        line0.setEndX(-98.75733947753906);
        line0.setEndY(238.95957946777344);
        line0.setStartX(-100.0);
        HBox.setMargin(line0, new Insets(40.0, 0.0, 0.0, 50.0));

        vBox6.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        vBox6.setLayoutX(100.0);
        vBox6.setLayoutY(10.0);
        vBox6.setMaxWidth(USE_PREF_SIZE);
        vBox6.setMinWidth(USE_PREF_SIZE);
        vBox6.setPrefHeight(322.0);
        vBox6.setPrefWidth(360.0);
        vBox6.setSpacing(10.0);
        vBox6.getStyleClass().add("card");
        vBox6.getStylesheets().add("/serveriwish/admindashboardview/../resources/admindashboard/style.css");

        label8.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label8.setText("Add User");
        label8.setFont(new Font("Segoe UI Semibold", 24.0));
        VBox.setMargin(label8, new Insets(5.0, 0.0, 0.0, 0.0));

        hBox8.setPrefHeight(100.0);
        hBox8.setPrefWidth(200.0);
        hBox8.setSpacing(48.0);

        label9.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label9.setText("Username");
        label9.setFont(new Font("Segoe UI Semibold", 17.0));

        textField0.setPromptText("username...");
        VBox.setMargin(hBox8, new Insets(0.0, 0.0, 0.0, 60.0));

        hBox9.setPrefHeight(100.0);
        hBox9.setPrefWidth(200.0);
        hBox9.setSpacing(84.0);

        label10.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label10.setText("Email");
        label10.setFont(new Font("Segoe UI Semibold", 17.0));

        textField1.setPromptText("email...");
        VBox.setMargin(hBox9, new Insets(0.0, 0.0, 0.0, 60.0));

        hBox10.setPrefHeight(100.0);
        hBox10.setPrefWidth(200.0);
        hBox10.setSpacing(51.0);

        label11.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label11.setText("Full name");
        label11.setFont(new Font("Segoe UI Semibold", 17.0));

        textField2.setPromptText("Full name...");
        VBox.setMargin(hBox10, new Insets(0.0, 0.0, 0.0, 60.0));

        hBox11.setPrefHeight(100.0);
        hBox11.setPrefWidth(200.0);
        hBox11.setSpacing(70.0);

        label12.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label12.setText("Gender");
        label12.setFont(new Font("Segoe UI Semibold", 17.0));

        textField3.setPromptText("Gender...");
        VBox.setMargin(hBox11, new Insets(0.0, 0.0, 0.0, 60.0));

        hBox12.setPrefHeight(100.0);
        hBox12.setPrefWidth(200.0);
        hBox12.setSpacing(52.0);

        label13.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label13.setText("Password");
        label13.setFont(new Font("Segoe UI Semibold", 17.0));
        HBox.setMargin(label13, new Insets(0.0));

        textField4.setPromptText("Password...");
        VBox.setMargin(hBox12, new Insets(0.0, 0.0, 0.0, 60.0));

        hBox13.setPrefHeight(100.0);
        hBox13.setPrefWidth(200.0);
        hBox13.setSpacing(20.0);

        label14.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label14.setText("Comfirm password");
        label14.setFont(new Font("Segoe UI Semibold", 17.0));

        textField5.setPromptText("Retype the password...");
        VBox.setMargin(hBox13, new Insets(0.0, 0.0, 5.0, 20.0));

        button.setMnemonicParsing(false);
        button.getStylesheets().add("/serveriwish/admindashboardview/../resources/admindashboard/style.css");
        button.setText("ADD");
        VBox.setMargin(button, new Insets(0.0, 0.0, 5.0, 0.0));
        HBox.setMargin(vBox6, new Insets(0.0, 0.0, 0.0, 50.0));
        VBox.setMargin(hBox7, new Insets(70.0, 0.0, 0.0, 5.0));
        setCenter(vBox0);

        hBox.getChildren().add(imageView);
        hBox.getChildren().add(label);
        vBox.getChildren().add(hBox);
        hBox0.getChildren().add(imageView0);
        hBox0.getChildren().add(label0);
        vBox.getChildren().add(hBox0);
        hBox1.getChildren().add(imageView1);
        hBox1.getChildren().add(label1);
        vBox.getChildren().add(hBox1);
        hBox2.getChildren().add(pane);
        hBox3.getChildren().add(imageView2);
        hBox3.getChildren().add(textField);
        hBox2.getChildren().add(hBox3);
        hBox2.getChildren().add(imageView3);
        hBox2.getChildren().add(imageView4);
        vBox0.getChildren().add(hBox2);
        vBox2.getChildren().add(label2);
        hBox5.getChildren().add(label3);
        hBox5.getChildren().add(pane0);
        hBox5.getChildren().add(imageView5);
        vBox3.getChildren().add(hBox5);
        vBox3.getChildren().add(label4);
        hBox4.getChildren().add(vBox3);
        hBox6.getChildren().add(label5);
        hBox6.getChildren().add(pane1);
        hBox6.getChildren().add(imageView6);
        vBox4.getChildren().add(hBox6);
        vBox4.getChildren().add(label6);
        hBox4.getChildren().add(vBox4);
        vBox2.getChildren().add(hBox4);
        vBox1.getChildren().add(vBox2);
        vBox1.getChildren().add(line);
        vBox0.getChildren().add(vBox1);
        vBox5.getChildren().add(label7);
        vBox5.getChildren().add(bttnServerStatus);
        hBox7.getChildren().add(vBox5);
        hBox7.getChildren().add(line0);
        vBox6.getChildren().add(label8);
        hBox8.getChildren().add(label9);
        hBox8.getChildren().add(textField0);
        vBox6.getChildren().add(hBox8);
        hBox9.getChildren().add(label10);
        hBox9.getChildren().add(textField1);
        vBox6.getChildren().add(hBox9);
        hBox10.getChildren().add(label11);
        hBox10.getChildren().add(textField2);
        vBox6.getChildren().add(hBox10);
        hBox11.getChildren().add(label12);
        hBox11.getChildren().add(textField3);
        vBox6.getChildren().add(hBox11);
        hBox12.getChildren().add(label13);
        hBox12.getChildren().add(textField4);
        vBox6.getChildren().add(hBox12);
        hBox13.getChildren().add(label14);
        hBox13.getChildren().add(textField5);
        vBox6.getChildren().add(hBox13);
        vBox6.getChildren().add(button);
        hBox7.getChildren().add(vBox6);
        vBox0.getChildren().add(hBox7);

    }

    protected abstract void handltServerStatus(javafx.event.ActionEvent actionEvent);

}
