package clientiwish.views.userprofileview.home;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public abstract class UserProfileBase extends BorderPane {

    protected final AnchorPane anchorPane;
    protected final ImageView imageView;
    protected final Rectangle rectangle;
    protected final VBox vBox;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final Label label;
    protected final Label label0;
    protected final Button button;
    protected final Button button0;
    protected final Button button1;
    protected final Button button2;
    protected final Button button3;
    protected final ImageView imageView2;
    protected final ImageView imageView3;
    protected final ImageView imageView4;
    protected final ImageView imageView5;
    protected final ImageView imageView6;
    protected final Button button4;
    protected final ImageView imageView7;
    protected final AnchorPane anchorPane0;
    protected final TextField textField;
    protected final Label label1;
    protected final ImageView imageView8;
    protected final Label label2;
    protected final Separator separator;
    protected final ImageView imageView9;
    protected final ScrollBar scrollBar;
    protected final AnchorPane anchorPane1;
    protected final ImageView imageView10;
    protected final Button button5;
    protected final ImageView imageView11;
    protected final Label label3;
    protected final Button button6;
    protected final ImageView imageView12;

    public UserProfileBase() {

        anchorPane = new AnchorPane();
        imageView = new ImageView();
        rectangle = new Rectangle();
        vBox = new VBox();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        label = new Label();
        label0 = new Label();
        button = new Button();
        button0 = new Button();
        button1 = new Button();
        button2 = new Button();
        button3 = new Button();
        imageView2 = new ImageView();
        imageView3 = new ImageView();
        imageView4 = new ImageView();
        imageView5 = new ImageView();
        imageView6 = new ImageView();
        button4 = new Button();
        imageView7 = new ImageView();
        anchorPane0 = new AnchorPane();
        textField = new TextField();
        label1 = new Label();
        imageView8 = new ImageView();
        label2 = new Label();
        separator = new Separator();
        imageView9 = new ImageView();
        scrollBar = new ScrollBar();
        anchorPane1 = new AnchorPane();
        imageView10 = new ImageView();
        button5 = new Button();
        imageView11 = new ImageView();
        label3 = new Label();
        button6 = new Button();
        imageView12 = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(850.0);
        setPrefWidth(1200.0);

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(850.0);
        anchorPane.setPrefWidth(291.0);

        imageView.setFitHeight(850.0);
        imageView.setFitWidth(332.0);
        imageView.setLayoutX(-14.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("../../resources/userprofile/gift(1).jpg").toExternalForm()));

        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setHeight(850.0);
        rectangle.setOpacity(0.69);
        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle.setWidth(306.0);

        vBox.setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);
        vBox.setPrefHeight(299.0);
        vBox.setPrefWidth(306.0);

        imageView0.setFitHeight(111.0);
        imageView0.setFitWidth(120.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-wish-100.png").toExternalForm()));

        imageView1.setFitHeight(133.0);
        imageView1.setFitWidth(125.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-user-100%20(2).png").toExternalForm()));

        label.setText("Ahmed Hatem");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Calibri Bold", 28.0));

        label0.setText("\\@ahmedhatem");
        label0.setTextFill(javafx.scene.paint.Color.WHITE);
        label0.setFont(new Font("System Bold Italic", 20.0));

        button.setLayoutX(3.0);
        button.setLayoutY(394.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(63.0);
        button.setPrefWidth(306.0);
        button.getStyleClass().add("button-color");
        button.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        button.setText("Home");
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setFont(new Font("System Bold", 26.0));

        button0.setLayoutY(476.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(63.0);
        button0.setPrefWidth(306.0);
        button0.getStyleClass().add("button-color");
        button0.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        button0.setText("Wish List");
        button0.setTextFill(javafx.scene.paint.Color.WHITE);
        button0.setFont(new Font("System Bold", 26.0));

        button1.setLayoutY(560.0);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(63.0);
        button1.setPrefWidth(306.0);
        button1.getStyleClass().add("button-color");
        button1.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        button1.setText("Friends List");
        button1.setTextFill(javafx.scene.paint.Color.WHITE);
        button1.setFont(new Font("System Bold", 26.0));

        button2.setLayoutX(3.0);
        button2.setLayoutY(639.0);
        button2.setMnemonicParsing(false);
        button2.setPrefHeight(63.0);
        button2.setPrefWidth(307.0);
        button2.getStyleClass().add("button-color");
        button2.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        button2.setText("Friend Requests");
        button2.setTextFill(javafx.scene.paint.Color.WHITE);
        button2.setFont(new Font("System Bold", 26.0));

        button3.setLayoutY(725.0);
        button3.setMnemonicParsing(false);
        button3.setPrefHeight(63.0);
        button3.setPrefWidth(307.0);
        button3.getStyleClass().add("button-color");
        button3.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        button3.setText("Contribute ");
        button3.setTextFill(javafx.scene.paint.Color.WHITE);
        button3.setFont(new Font("System Bold", 26.0));

        imageView2.setFitHeight(32.0);
        imageView2.setFitWidth(43.0);
        imageView2.setLayoutX(32.0);
        imageView2.setLayoutY(411.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);
        imageView2.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-home-100.png").toExternalForm()));

        imageView3.setFitHeight(33.0);
        imageView3.setFitWidth(53.0);
        imageView3.setLayoutX(30.0);
        imageView3.setLayoutY(492.0);
        imageView3.setPickOnBounds(true);
        imageView3.setPreserveRatio(true);
        imageView3.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-wishlist-100.png").toExternalForm()));

        imageView4.setFitHeight(36.0);
        imageView4.setFitWidth(46.0);
        imageView4.setLayoutX(18.0);
        imageView4.setLayoutY(574.0);
        imageView4.setPickOnBounds(true);
        imageView4.setPreserveRatio(true);
        imageView4.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-friends-100.png").toExternalForm()));

        imageView5.setFitHeight(55.0);
        imageView5.setFitWidth(33.0);
        imageView5.setLayoutX(20.0);
        imageView5.setLayoutY(654.0);
        imageView5.setPickOnBounds(true);
        imageView5.setPreserveRatio(true);
        imageView5.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-add-friends-100.png").toExternalForm()));

        imageView6.setFitHeight(36.0);
        imageView6.setFitWidth(37.0);
        imageView6.setLayoutX(18.0);
        imageView6.setLayoutY(739.0);
        imageView6.setPickOnBounds(true);
        imageView6.setPreserveRatio(true);
        imageView6.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-community-grants-100.png").toExternalForm()));

        button4.setLayoutX(86.0);
        button4.setLayoutY(313.0);
        button4.setMnemonicParsing(false);
        button4.setPrefHeight(44.0);
        button4.setPrefWidth(134.0);
        button4.getStyleClass().add("editprofilebutton-color");
        button4.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        button4.setText("Edit Profile");
        button4.setTextFill(javafx.scene.paint.Color.WHITE);
        button4.setFont(new Font("System Bold", 20.0));

        imageView7.setFitHeight(35.0);
        imageView7.setFitWidth(33.0);
        imageView7.setLayoutX(41.0);
        imageView7.setLayoutY(319.0);
        imageView7.setPickOnBounds(true);
        imageView7.setPreserveRatio(true);
        imageView7.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-edit-user-100.png").toExternalForm()));
        setLeft(anchorPane);

        BorderPane.setAlignment(anchorPane0, javafx.geometry.Pos.CENTER);
        anchorPane0.setPrefHeight(200.0);
        anchorPane0.setPrefWidth(200.0);

        textField.setLayoutX(33.0);
        textField.setLayoutY(27.0);
        textField.setPrefHeight(40.0);
        textField.setPrefWidth(649.0);
        textField.setPromptText("Search for Items..");
        textField.getStyleClass().add("rounded-text-field");
        textField.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        textField.setFont(new Font("System Italic", 15.0));

        label1.setLayoutX(699.0);
        label1.setLayoutY(20.0);
        label1.setPrefHeight(54.0);
        label1.setPrefWidth(67.0);
        label1.setText("\\$0.00");
        label1.setTextFill(javafx.scene.paint.Color.valueOf("#f6b63d"));
        label1.setFont(new Font(26.0));

        imageView8.setFitHeight(54.0);
        imageView8.setFitWidth(53.0);
        imageView8.setLayoutX(776.0);
        imageView8.setLayoutY(21.0);
        imageView8.setPickOnBounds(true);
        imageView8.setPreserveRatio(true);
        imageView8.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-cart-100.png").toExternalForm()));

        label2.setLayoutX(86.0);
        label2.setLayoutY(87.0);
        label2.setPrefHeight(61.0);
        label2.setPrefWidth(129.0);
        label2.setText("My Shop");
        label2.setTextFill(javafx.scene.paint.Color.valueOf("#f6b63d"));
        label2.setFont(new Font("System Bold", 30.0));

        separator.setLayoutX(14.0);
        separator.setLayoutY(148.0);
        separator.setPrefHeight(28.0);
        separator.setPrefWidth(827.0);

        imageView9.setFitHeight(53.0);
        imageView9.setFitWidth(47.0);
        imageView9.setLayoutX(22.0);
        imageView9.setLayoutY(94.0);
        imageView9.setPickOnBounds(true);
        imageView9.setPreserveRatio(true);
        imageView9.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-shop-100.png").toExternalForm()));

        scrollBar.setLayoutX(858.0);
        scrollBar.setLayoutY(13.0);
        scrollBar.setOrientation(javafx.geometry.Orientation.VERTICAL);
        scrollBar.setPrefHeight(824.0);
        scrollBar.setPrefWidth(18.0);

        anchorPane1.setLayoutX(20.0);
        anchorPane1.setLayoutY(176.0);
        anchorPane1.setPrefHeight(653.0);
        anchorPane1.setPrefWidth(815.0);

        imageView10.setFitHeight(205.0);
        imageView10.setFitWidth(159.0);
        imageView10.setLayoutX(23.0);
        imageView10.setLayoutY(14.0);
        imageView10.setPickOnBounds(true);
        imageView10.setPreserveRatio(true);
        imageView10.setImage(new Image(getClass().getResource("../../resources/userprofile/watch.jpg").toExternalForm()));

        button5.setLayoutX(30.0);
        button5.setLayoutY(267.0);
        button5.setMnemonicParsing(false);
        button5.setPrefHeight(42.0);
        button5.setPrefWidth(56.0);
        button5.getStyleClass().add("button-color");
        button5.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");

        imageView11.setFitHeight(42.0);
        imageView11.setFitWidth(39.0);
        imageView11.setLayoutX(38.0);
        imageView11.setLayoutY(267.0);
        imageView11.setPickOnBounds(true);
        imageView11.setPreserveRatio(true);
        imageView11.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-love-100.png").toExternalForm()));

        label3.setLayoutX(48.0);
        label3.setLayoutY(218.0);
        label3.setPrefHeight(30.0);
        label3.setPrefWidth(110.0);
        label3.setText("Smart Watch");
        label3.setFont(new Font("System Bold", 18.0));

        button6.setLayoutX(117.0);
        button6.setLayoutY(267.0);
        button6.setMnemonicParsing(false);
        button6.setPrefHeight(42.0);
        button6.setPrefWidth(56.0);
        button6.getStyleClass().add("button-color");
        button6.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");

        imageView12.setFitHeight(44.0);
        imageView12.setFitWidth(43.0);
        imageView12.setLayoutX(123.0);
        imageView12.setLayoutY(265.0);
        imageView12.setPickOnBounds(true);
        imageView12.setPreserveRatio(true);
        imageView12.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-cart-100.png").toExternalForm()));
        setCenter(anchorPane0);

        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(rectangle);
        vBox.getChildren().add(imageView0);
        vBox.getChildren().add(imageView1);
        vBox.getChildren().add(label);
        vBox.getChildren().add(label0);
        anchorPane.getChildren().add(vBox);
        anchorPane.getChildren().add(button);
        anchorPane.getChildren().add(button0);
        anchorPane.getChildren().add(button1);
        anchorPane.getChildren().add(button2);
        anchorPane.getChildren().add(button3);
        anchorPane.getChildren().add(imageView2);
        anchorPane.getChildren().add(imageView3);
        anchorPane.getChildren().add(imageView4);
        anchorPane.getChildren().add(imageView5);
        anchorPane.getChildren().add(imageView6);
        anchorPane.getChildren().add(button4);
        anchorPane.getChildren().add(imageView7);
        anchorPane0.getChildren().add(textField);
        anchorPane0.getChildren().add(label1);
        anchorPane0.getChildren().add(imageView8);
        anchorPane0.getChildren().add(label2);
        anchorPane0.getChildren().add(separator);
        anchorPane0.getChildren().add(imageView9);
        anchorPane0.getChildren().add(scrollBar);
        anchorPane1.getChildren().add(imageView10);
        anchorPane1.getChildren().add(button5);
        anchorPane1.getChildren().add(imageView11);
        anchorPane1.getChildren().add(label3);
        anchorPane1.getChildren().add(button6);
        anchorPane1.getChildren().add(imageView12);
        anchorPane0.getChildren().add(anchorPane1);

    }
}
