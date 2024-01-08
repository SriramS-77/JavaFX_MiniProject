package BMS;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;

public class Account_Menu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void PressBack(ActionEvent ae) throws IOException{
        FlowPane chooseAccount = FXMLLoader.load(getClass().getResource("Choose_Account.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(chooseAccount);
    }

    @FXML
    void PressDeposit(ActionEvent ae) throws IOException{
        FlowPane depositMoney = FXMLLoader.load(getClass().getResource("Deposit.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(depositMoney);
    }

    @FXML
    void PressWithdraw(ActionEvent ae) throws IOException {
        FlowPane withdrawMoney = FXMLLoader.load(getClass().getResource("Withdraw.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(withdrawMoney);
    }

    @FXML
    void PressSendMoney(ActionEvent ae) throws IOException {
        FlowPane sendMoney = FXMLLoader.load(getClass().getResource("Send_Money.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(sendMoney);
    }

    @FXML
    void PressViewTransactions(ActionEvent ae) throws IOException {
        FlowPane viewTransactions = FXMLLoader.load(getClass().getResource("View_Transactions.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(viewTransactions);
    }

    @FXML
    void initialize() {
    }

}
