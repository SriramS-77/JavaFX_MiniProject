package BMS;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;

public class Menu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
    }

    @FXML 
    void PressChooseAccount(ActionEvent ae) throws IOException{
        FlowPane chooseAccount = FXMLLoader.load(getClass().getResource("Choose_Account.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(chooseAccount);
    }

    @FXML
    void PressViewTransactions(ActionEvent ae) throws IOException{
        FlowPane allTransactionsPage = FXMLLoader.load(getClass().getResource("View_All_Transactions.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(allTransactionsPage);        
    }
    @FXML
    void PressViewAccounts(ActionEvent ae) throws IOException{
        FlowPane accountsPage = FXMLLoader.load(getClass().getResource("View_Accounts.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(accountsPage);        
    }
    @FXML
    void PressViewProfile(ActionEvent ae) throws IOException{
        FlowPane profilePage = FXMLLoader.load(getClass().getResource("View_Profile.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(profilePage);        
    }
    @FXML
    void PressCreateAccount(ActionEvent ae) throws IOException{
        FlowPane createAccount = FXMLLoader.load(getClass().getResource("Create_Account.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(createAccount);        
    }
    @FXML
    void PressDeleteAccount(ActionEvent ae) throws IOException{
        FlowPane deleteAccount = FXMLLoader.load(getClass().getResource("Delete_Account.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(deleteAccount);        
    }
    @FXML
    void PressLogOut(ActionEvent ae) throws IOException{
        FlowPane loginPage = FXMLLoader.load(getClass().getResource("mainframe.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(loginPage);
    }

}
