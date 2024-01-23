package clientiwish.views.userprofileview.contribution;

import clientCommunication.Client;
import clientiwish.models.DTOContribution;
import clientiwish.models.DTOContributionList;
import clientiwish.models.DTOUser;
import clientiwish.views.userprofileview.home.UserProfileController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import sharedlibraries.Response;

public class ContributionController implements Initializable {

    @FXML
    private TableView<DTOContributionList> contributionTableView;
    @FXML
    private TableColumn<DTOContributionList, String> ownerName;
    @FXML
    private TableColumn<DTOContributionList, Integer> itemNumber;
    @FXML
    private TableColumn<DTOContributionList, String> itemName;
    @FXML
    private TableColumn<DTOContributionList, Integer> itemPrice;
    @FXML
    private TableColumn<DTOContributionList, Integer> contributionAmount;
    @FXML
    private Label labelViewName;
    @FXML
    private ImageView iconImageView;
    @FXML
    private RadioButton redioBttnComplete;
    @FXML
    private ToggleGroup contribuiton;
    @FXML
    private RadioButton redioBttnIncomplete;

    ObservableList<DTOContributionList> contributionList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<DTOContributionList, String> status;
    @FXML
    private Button bttnCancelContribution;
    @FXML
    private Label enterBalanceLabel;
    @FXML
    private Label yourbalance;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        yourbalance.setText("$ " + String.valueOf(Client.getCurrentUser().getBalance()));
    }

    @FXML
    private void getCompleteContribution(ActionEvent event) {
        filterContributionList("Complete");

    }

    @FXML
    private void getIncompleteContribution(ActionEvent event) {
        filterContributionList("Incomplete");
    }

    private void filterContributionList(String status) {
        ObservableList<DTOContributionList> filteredList = FXCollections.observableArrayList();

        for (DTOContributionList contribution : contributionList) {
            if (contribution.getStatus().equals(status)) {
                filteredList.add(contribution);
            }
        }

        contributionTableView.setItems(filteredList);
    }

    public void setContributionItems(ArrayList<DTOContributionList> contributions) {
        System.out.println("2: " + contributions);
        contributionList.setAll(contributions);

        contributionTableView.setItems(contributionList);

        ownerName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getOwnerName());
        });

        itemNumber.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemID()).asObject();
        });

        itemName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getItemName());
        });

        itemPrice.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getItemPrice()).asObject();
        });

        contributionAmount.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getTotalAmountContribution()).asObject();
        });

        status.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getStatus());
        });

    }

    @FXML
    private void cancelContribution(ActionEvent event) {
        DTOContributionList selectedItem = contributionTableView.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Contribution List Status");
            alert.setHeaderText(null);
            alert.setContentText("Failed to cancel contribution , because there is not item has been selected");

            alert.showAndWait();
        }else if(selectedItem.getStatus().equals("Complete")){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Contribution List Status");
            alert.setHeaderText(null);
            alert.setContentText("Failed to cancel contribution , because you select a completed contribution");

            alert.showAndWait();
        }else {
            DTOContribution deletedContribution = new DTOContribution(Client.getCurrentUser().getUserUniqueName(),
                    selectedItem.getOwnerName(), selectedItem.getItemID(), selectedItem.getTotalAmountContribution());
            int amount = selectedItem.getTotalAmountContribution();
            contributionList.remove(selectedItem);
            new Thread() {
                @Override
                public void run() {
                    Client.updateResponse(null);
                    Client.sendRequest("CANCELCONTRIBUTION", deletedContribution);

                    while (Client.getResponse() == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    
                    Platform.runLater(() -> {
                         FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientiwish/views/userprofileview/home/UserProfile.fxml"));

                    UserProfileController userProfileController = loader.getController();
                    Response<DTOUser> responseCancelContribution = Client.getResponse();
                    DTOUser receivedUser = (DTOUser) responseCancelContribution.getData();
                    Client.getCurrentUser().setBalance(receivedUser.getBalance());
                    yourbalance.setText("$ " +Client.getCurrentUser().getBalance());
                    System.out.println(Client.getCurrentUser().getBalance());
                        if (responseCancelContribution != null && responseCancelContribution.isSuccess()) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);

                            alert.setTitle("Cancel Contribution");
                            alert.setHeaderText(null);
                            alert.setContentText("Contribution Canceled Successfully");

                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);

                            alert.setTitle("Cancel Contribution");
                            alert.setHeaderText(null);
                            alert.setContentText("Failed to cancel contribution");

                            alert.showAndWait();
                        }
                    });

                }

            }.start();
        }

    }

}
