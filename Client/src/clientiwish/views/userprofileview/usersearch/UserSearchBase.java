package clientiwish.views.userprofileview.usersearch;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public abstract class UserSearchBase extends AnchorPane {

    protected final TableView searchTableView;
    protected final TableColumn userNameColumn;
    protected final TableColumn emailColumn;
    protected final TableColumn birthdateColumn;
    protected final Button button;

    public UserSearchBase() {

        searchTableView = new TableView();
        userNameColumn = new TableColumn();
        emailColumn = new TableColumn();
        birthdateColumn = new TableColumn();
        button = new Button();

        setId("AnchorPane");
        setPrefHeight(653.0);
        setPrefWidth(815.0);

        searchTableView.setLayoutX(-3.0);
        searchTableView.setLayoutY(60.0);
        searchTableView.setPrefHeight(595.0);
        searchTableView.setPrefWidth(815.0);

        userNameColumn.setPrefWidth(272.33333587646484);
        userNameColumn.setText("User Name");

        emailColumn.setPrefWidth(275.0);
        emailColumn.setText("User Email");

        birthdateColumn.setPrefWidth(268.0);
        birthdateColumn.setText("User Birthdate");

        button.setLayoutX(355.0);
        button.setLayoutY(14.0);
        button.setMnemonicParsing(false);
        button.setOpacity(0.68);
        button.setPrefHeight(37.0);
        button.setPrefWidth(133.0);
        button.setStyle("-fx-background-color: blue;");
        button.setText("Add Friend");
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setFont(new Font("System Bold", 15.0));

        searchTableView.getColumns().add(userNameColumn);
        searchTableView.getColumns().add(emailColumn);
        searchTableView.getColumns().add(birthdateColumn);
        getChildren().add(searchTableView);
        getChildren().add(button);

    }
}
