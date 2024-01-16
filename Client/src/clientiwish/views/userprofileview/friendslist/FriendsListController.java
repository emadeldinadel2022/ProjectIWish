package clientiwish.views.userprofileview.friendslist;

import clientiwish.models.DTOItem;
import clientiwish.models.DTOUser;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class FriendsListController {

    @FXML
    private AnchorPane viewFriendList;
    @FXML
    private TableView<DTOUser> fListTableView;
    @FXML
    private TableColumn<DTOUser, String> FriendName;
    @FXML
    private TableColumn<DTOUser, String> friendEmail;
    @FXML
    private TableColumn<DTOUser, Date> friendBirthdate;

    ObservableList<DTOUser> friendsList = FXCollections.observableArrayList();

    public void setFriendsList(ArrayList<DTOUser> friends) {
        System.out.println("2: " + friends);
        friendsList.setAll(friends);
        fListTableView.setItems(friendsList);

        FriendName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUserUniqueName()));
        friendEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        friendBirthdate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDob()));
    }
}
