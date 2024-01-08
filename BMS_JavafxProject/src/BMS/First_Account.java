package BMS;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.opencsv.CSVWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import pkg.* ;

public class First_Account {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
    }
    @FXML
    Label lbWarning ;
    @FXML
    TextField tfMoney ;

    AccountHolder user ;
    @FXML
    void PressCreateAccount(ActionEvent ae) throws IOException{
        if(Double.parseDouble(tfMoney.getText().trim()) < 2000){
            lbWarning.setVisible(true);
        }
        else{
            try{
                FileWriter fw = new FileWriter("database/Accounts.csv" , true) ;
                CSVWriter writer = new CSVWriter(fw) ;
                user = (AccountHolder) ((javafx.scene.Node)ae.getSource()).getScene().getUserData();
                Account obj = new Account(user.username , "Savings" , tfMoney.getText()) ;
                String row[] = {obj.username , obj.accountID , "Savings" , obj.balance} ;
                writer.writeNext(row , false);
                writer.close();
                FlowPane menu = FXMLLoader.load(getClass().getResource("Menu.fxml")) ;
                ((javafx.scene.Node)ae.getSource()).getScene().setRoot(menu) ;
            }
            catch(IOException e){}
        }
    }

}
