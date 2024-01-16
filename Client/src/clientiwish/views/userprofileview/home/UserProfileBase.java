package clientiwish.views.userprofileview.home;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    protected final Button bttnUserImage;
    protected final ImageView userImage;
    protected final Label labelUserName;
    protected final Label labelUserUniqueName;
    protected final Label labelUserEmail;
    protected final Button homeButton;
    protected final Button wishlistButton;
    protected final Button friendlistButton;
    protected final Button friendRequetButton;
    protected final Button notificationButton;
    protected final ImageView imageView1;
    protected final ImageView imageView2;
    protected final ImageView imageView3;
    protected final ImageView imageView4;
    protected final ImageView imageView5;
    protected final Button bttnEditProfile;
    protected final ImageView imageView6;
    protected final Button bttnPendingList;
    protected final Button bttnLogOut;
    protected final AnchorPane anchorPane0;
    protected final TextField searchTextField;
    protected final Label labelUserBalance;
    protected final Label labelViewName;
    protected final Separator separator;
    protected final ImageView iconImageView;
    protected final ScrollBar scrollBar;
    protected final AnchorPane viewAnchorPane;
    protected final ListView searchListView;
    protected final Button bttnRecharge;
    protected final Label labelUserDOB;

    public UserProfileBase() {

        anchorPane = new AnchorPane();
        imageView = new ImageView();
        rectangle = new Rectangle();
        vBox = new VBox();
        imageView0 = new ImageView();
        bttnUserImage = new Button();
        userImage = new ImageView();
        labelUserName = new Label();
        labelUserUniqueName = new Label();
        labelUserEmail = new Label();
        homeButton = new Button();
        wishlistButton = new Button();
        friendlistButton = new Button();
        friendRequetButton = new Button();
        notificationButton = new Button();
        imageView1 = new ImageView();
        imageView2 = new ImageView();
        imageView3 = new ImageView();
        imageView4 = new ImageView();
        imageView5 = new ImageView();
        bttnEditProfile = new Button();
        imageView6 = new ImageView();
        bttnPendingList = new Button();
        bttnLogOut = new Button();
        anchorPane0 = new AnchorPane();
        searchTextField = new TextField();
        labelUserBalance = new Label();
        labelViewName = new Label();
        separator = new Separator();
        iconImageView = new ImageView();
        scrollBar = new ScrollBar();
        viewAnchorPane = new AnchorPane();
        searchListView = new ListView();
        bttnRecharge = new Button();
        labelUserDOB = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(800.0);
        setPrefWidth(1200.0);

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(774.0);
        anchorPane.setPrefWidth(284.0);

        imageView.setFitHeight(804.0);
        imageView.setFitWidth(303.0);
        imageView.setLayoutX(4.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("../../resources/userprofile/gift(1).jpg").toExternalForm()));

        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setHeight(806.0);
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

        bttnUserImage.setMnemonicParsing(false);
        bttnUserImage.setOnAction(this::uploadImage);
        bttnUserImage.setText("Upload Image");

        userImage.setFitHeight(133.0);
        userImage.setFitWidth(125.0);
        userImage.setPickOnBounds(true);
        userImage.setPreserveRatio(true);
        userImage.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-user-100%20(2).png").toExternalForm()));

        labelUserName.setText("Ahmed Hatem");
        labelUserName.setTextFill(javafx.scene.paint.Color.WHITE);
        labelUserName.setFont(new Font("Calibri Bold", 28.0));

        labelUserUniqueName.setText("ahmedhatem");
        labelUserUniqueName.setTextFill(javafx.scene.paint.Color.WHITE);
        labelUserUniqueName.setFont(new Font("System Bold Italic", 20.0));

        labelUserEmail.setText("ahmed@gmail.com");
        labelUserEmail.setTextFill(javafx.scene.paint.Color.WHITE);
        labelUserEmail.setFont(new Font("System Bold Italic", 20.0));

        homeButton.setLayoutX(4.0);
        homeButton.setLayoutY(403.0);
        homeButton.setMnemonicParsing(false);
        homeButton.setOnAction(this::viewHome);
        homeButton.setPrefHeight(63.0);
        homeButton.setPrefWidth(306.0);
        homeButton.getStyleClass().add("button-color");
        homeButton.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        homeButton.setText("Home");
        homeButton.setTextFill(javafx.scene.paint.Color.WHITE);
        homeButton.setFont(new Font("System Bold", 26.0));

        wishlistButton.setLayoutX(4.0);
        wishlistButton.setLayoutY(457.0);
        wishlistButton.setMnemonicParsing(false);
        wishlistButton.setOnAction(this::viewMywishlist);
        wishlistButton.setPrefHeight(63.0);
        wishlistButton.setPrefWidth(306.0);
        wishlistButton.getStyleClass().add("button-color");
        wishlistButton.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        wishlistButton.setText("Wish List");
        wishlistButton.setTextFill(javafx.scene.paint.Color.WHITE);
        wishlistButton.setFont(new Font("System Bold", 26.0));

        friendlistButton.setLayoutY(520.0);
        friendlistButton.setMnemonicParsing(false);
        friendlistButton.setOnAction(this::viewMyFriendList);
        friendlistButton.setPrefHeight(63.0);
        friendlistButton.setPrefWidth(306.0);
        friendlistButton.getStyleClass().add("button-color");
        friendlistButton.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        friendlistButton.setText("Friends List");
        friendlistButton.setTextFill(javafx.scene.paint.Color.WHITE);
        friendlistButton.setFont(new Font("System Bold", 26.0));

        friendRequetButton.setLayoutX(14.0);
        friendRequetButton.setLayoutY(583.0);
        friendRequetButton.setMnemonicParsing(false);
        friendRequetButton.setOnAction(this::viewFriendRequest);
        friendRequetButton.setPrefHeight(63.0);
        friendRequetButton.setPrefWidth(307.0);
        friendRequetButton.getStyleClass().add("button-color");
        friendRequetButton.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        friendRequetButton.setText("Friend Requests");
        friendRequetButton.setTextFill(javafx.scene.paint.Color.WHITE);
        friendRequetButton.setFont(new Font("System Bold", 26.0));

        notificationButton.setLayoutX(1.0);
        notificationButton.setLayoutY(637.0);
        notificationButton.setMnemonicParsing(false);
        notificationButton.setOnAction(this::viewNotification);
        notificationButton.setPrefHeight(63.0);
        notificationButton.setPrefWidth(307.0);
        notificationButton.getStyleClass().add("button-color");
        notificationButton.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        notificationButton.setText("Notifications");
        notificationButton.setTextFill(javafx.scene.paint.Color.WHITE);
        notificationButton.setFont(new Font("System Bold", 26.0));

        imageView1.setFitHeight(32.0);
        imageView1.setFitWidth(43.0);
        imageView1.setLayoutX(43.0);
        imageView1.setLayoutY(419.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-home-100.png").toExternalForm()));

        imageView2.setFitHeight(33.0);
        imageView2.setFitWidth(53.0);
        imageView2.setLayoutX(43.0);
        imageView2.setLayoutY(472.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);
        imageView2.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-wishlist-100.png").toExternalForm()));

        imageView3.setFitHeight(36.0);
        imageView3.setFitWidth(46.0);
        imageView3.setLayoutX(33.0);
        imageView3.setLayoutY(534.0);
        imageView3.setPickOnBounds(true);
        imageView3.setPreserveRatio(true);
        imageView3.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-friends-100.png").toExternalForm()));

        imageView4.setFitHeight(55.0);
        imageView4.setFitWidth(33.0);
        imageView4.setLayoutX(27.0);
        imageView4.setLayoutY(591.0);
        imageView4.setPickOnBounds(true);
        imageView4.setPreserveRatio(true);
        imageView4.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-add-friends-100.png").toExternalForm()));

        imageView5.setFitHeight(36.0);
        imageView5.setFitWidth(37.0);
        imageView5.setLayoutX(26.0);
        imageView5.setLayoutY(651.0);
        imageView5.setPickOnBounds(true);
        imageView5.setPreserveRatio(true);
        imageView5.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-community-grants-100.png").toExternalForm()));

        bttnEditProfile.setLayoutX(91.0);
        bttnEditProfile.setLayoutY(362.0);
        bttnEditProfile.setMnemonicParsing(false);
        bttnEditProfile.setOnAction(this::editProfile);
        bttnEditProfile.setPrefHeight(44.0);
        bttnEditProfile.setPrefWidth(134.0);
        bttnEditProfile.getStyleClass().add("editprofilebutton-color");
        bttnEditProfile.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        bttnEditProfile.setText("Edit Profile");
        bttnEditProfile.setTextFill(javafx.scene.paint.Color.WHITE);
        bttnEditProfile.setFont(new Font("System Bold", 20.0));

        imageView6.setFitHeight(35.0);
        imageView6.setFitWidth(33.0);
        imageView6.setLayoutX(49.0);
        imageView6.setLayoutY(368.0);
        imageView6.setPickOnBounds(true);
        imageView6.setPreserveRatio(true);
        imageView6.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-edit-user-100.png").toExternalForm()));

        bttnPendingList.setLayoutY(687.0);
        bttnPendingList.setMnemonicParsing(false);
        bttnPendingList.setOnAction(this::viewPendingList);
        bttnPendingList.setPrefHeight(63.0);
        bttnPendingList.setPrefWidth(307.0);
        bttnPendingList.getStyleClass().add("button-color");
        bttnPendingList.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        bttnPendingList.setText("Pending List");
        bttnPendingList.setTextFill(javafx.scene.paint.Color.WHITE);
        bttnPendingList.setFont(new Font("System Bold", 26.0));

        bttnLogOut.setLayoutX(1.0);
        bttnLogOut.setLayoutY(741.0);
        bttnLogOut.setMnemonicParsing(false);
        bttnLogOut.setOnAction(this::logOut);
        bttnLogOut.setPrefHeight(63.0);
        bttnLogOut.setPrefWidth(307.0);
        bttnLogOut.getStyleClass().add("button-color");
        bttnLogOut.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        bttnLogOut.setText("Log Out");
        bttnLogOut.setTextFill(javafx.scene.paint.Color.WHITE);
        bttnLogOut.setFont(new Font("System Bold", 26.0));
        setLeft(anchorPane);

        BorderPane.setAlignment(anchorPane0, javafx.geometry.Pos.CENTER);
        anchorPane0.setPrefHeight(781.0);
        anchorPane0.setPrefWidth(890.0);

        searchTextField.setLayoutX(33.0);
        searchTextField.setLayoutY(27.0);
        searchTextField.setOnKeyTyped(this::handleSearchTextField);
        searchTextField.setPrefHeight(40.0);
        searchTextField.setPrefWidth(649.0);
        searchTextField.setPromptText("Search for friends..");
        searchTextField.getStyleClass().add("rounded-text-field");
        searchTextField.getStylesheets().add("/clientiwish/views/userprofileview/home/../../resources/userprofile/style.css");
        searchTextField.setFont(new Font("System Italic", 15.0));

        labelUserBalance.setLayoutX(699.0);
        labelUserBalance.setLayoutY(20.0);
        labelUserBalance.setPrefHeight(54.0);
        labelUserBalance.setPrefWidth(67.0);
        labelUserBalance.setText("\\$0.00");
        labelUserBalance.setTextFill(javafx.scene.paint.Color.valueOf("#f6b63d"));
        labelUserBalance.setFont(new Font(26.0));

        labelViewName.setLayoutX(86.0);
        labelViewName.setLayoutY(87.0);
        labelViewName.setPrefHeight(61.0);
        labelViewName.setPrefWidth(129.0);
        labelViewName.setText("My Shop");
        labelViewName.setTextFill(javafx.scene.paint.Color.valueOf("#f6b63d"));
        labelViewName.setFont(new Font("System Bold", 30.0));

        separator.setLayoutX(14.0);
        separator.setLayoutY(148.0);
        separator.setPrefHeight(28.0);
        separator.setPrefWidth(827.0);

        iconImageView.setFitHeight(53.0);
        iconImageView.setFitWidth(47.0);
        iconImageView.setLayoutX(22.0);
        iconImageView.setLayoutY(94.0);
        iconImageView.setPickOnBounds(true);
        iconImageView.setPreserveRatio(true);
        iconImageView.setImage(new Image(getClass().getResource("../../resources/userprofile/icons8-shop-100.png").toExternalForm()));

        scrollBar.setLayoutX(849.0);
        scrollBar.setLayoutY(165.0);
        scrollBar.setOrientation(javafx.geometry.Orientation.VERTICAL);
        scrollBar.setPrefHeight(635.0);
        scrollBar.setPrefWidth(18.0);

        viewAnchorPane.setLayoutX(30.0);
        viewAnchorPane.setLayoutY(178.0);
        viewAnchorPane.setPrefHeight(591.0);
        viewAnchorPane.setPrefWidth(815.0);

        searchListView.setLayoutX(35.0);
        searchListView.setLayoutY(70.0);
        searchListView.setPrefHeight(22.0);
        searchListView.setPrefWidth(646.0);

        bttnRecharge.setLayoutX(772.0);
        bttnRecharge.setLayoutY(34.0);
        bttnRecharge.setMnemonicParsing(false);
        bttnRecharge.setOnAction(this::recharageBalance);
        bttnRecharge.setText("Recharge");

        labelUserDOB.setLayoutX(715.0);
        labelUserDOB.setLayoutY(73.0);
        labelUserDOB.setText("01-01-2000");
        labelUserDOB.setTextFill(javafx.scene.paint.Color.valueOf("#f6b63d"));
        labelUserDOB.setFont(new Font("System Bold Italic", 20.0));
        setCenter(anchorPane0);

        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(rectangle);
        vBox.getChildren().add(imageView0);
        vBox.getChildren().add(bttnUserImage);
        vBox.getChildren().add(userImage);
        vBox.getChildren().add(labelUserName);
        vBox.getChildren().add(labelUserUniqueName);
        vBox.getChildren().add(labelUserEmail);
        anchorPane.getChildren().add(vBox);
        anchorPane.getChildren().add(homeButton);
        anchorPane.getChildren().add(wishlistButton);
        anchorPane.getChildren().add(friendlistButton);
        anchorPane.getChildren().add(friendRequetButton);
        anchorPane.getChildren().add(notificationButton);
        anchorPane.getChildren().add(imageView1);
        anchorPane.getChildren().add(imageView2);
        anchorPane.getChildren().add(imageView3);
        anchorPane.getChildren().add(imageView4);
        anchorPane.getChildren().add(imageView5);
        anchorPane.getChildren().add(bttnEditProfile);
        anchorPane.getChildren().add(imageView6);
        anchorPane.getChildren().add(bttnPendingList);
        anchorPane.getChildren().add(bttnLogOut);
        anchorPane0.getChildren().add(searchTextField);
        anchorPane0.getChildren().add(labelUserBalance);
        anchorPane0.getChildren().add(labelViewName);
        anchorPane0.getChildren().add(separator);
        anchorPane0.getChildren().add(iconImageView);
        anchorPane0.getChildren().add(scrollBar);
        anchorPane0.getChildren().add(viewAnchorPane);
        anchorPane0.getChildren().add(searchListView);
        anchorPane0.getChildren().add(bttnRecharge);
        anchorPane0.getChildren().add(labelUserDOB);

    }

    protected abstract void uploadImage(javafx.event.ActionEvent actionEvent);

    protected abstract void viewHome(javafx.event.ActionEvent actionEvent);

    protected abstract void viewMywishlist(javafx.event.ActionEvent actionEvent);

    protected abstract void viewMyFriendList(javafx.event.ActionEvent actionEvent);

    protected abstract void viewFriendRequest(javafx.event.ActionEvent actionEvent);

    protected abstract void viewNotification(javafx.event.ActionEvent actionEvent);

    protected abstract void editProfile(javafx.event.ActionEvent actionEvent);

    protected abstract void viewPendingList(javafx.event.ActionEvent actionEvent);

    protected abstract void logOut(javafx.event.ActionEvent actionEvent);

    protected abstract void handleSearchTextField(javafx.scene.input.KeyEvent keyEvent);

    protected abstract void recharageBalance(javafx.event.ActionEvent actionEvent);

}
