package BMS;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import pkg.Account;

public class View_Account_Details {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Label lbAccountID;

    @FXML
    private Label lbBalance;

    @FXML
    private Label lbType ;

    @FXML
    private Label lbInterest;

    @FXML
    void PressBack(ActionEvent ae) throws IOException{
        FlowPane Accountmenu = FXMLLoader.load(getClass().getResource("Account_Menu.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(Accountmenu);
    }

    DataSingleton data = DataSingleton.getInstance() ;
    Account account = data.getAccount() ;

    @FXML
    void initialize() {
        lbAccountID.setText(account.accountID);
        lbType.setText(account.type);
        lbBalance.setText(account.balance);
        lbInterest.setText(account.balance);
    }

}
