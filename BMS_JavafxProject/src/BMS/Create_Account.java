package BMS;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.opencsv.CSVWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import pkg.Account;
import pkg.AccountHolder;

public class Create_Account {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private Label lbMessage;

    @FXML
    RadioButton btnSavings , btnBusiness , btnFunds ;

    @FXML
    private TextField tfMoney;

    @FXML
    void PressBack(ActionEvent ae) throws IOException {
        FlowPane menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(menu);
    }

    DataSingleton data = DataSingleton.getInstance();
    AccountHolder user = data.getUser();
    ToggleGroup tg ;

    @FXML
    void initialize() {
        tg = new ToggleGroup() ;
        btnSavings.setToggleGroup(tg);
        btnBusiness.setToggleGroup(tg);
        btnFunds.setToggleGroup(tg);
    }

    @FXML
    void PressCreateAccount(ActionEvent ae) throws IOException{
        if(tfMoney.getText().equals("") || tg.getSelectedToggle()==null){
            lbMessage.setVisible(true);
            lbMessage.setText("Please fill all the fields") ;
            return ;
        }
        else if(Double.parseDouble(tfMoney.getText().trim()) < 2000){
            lbMessage.setVisible(true);
            lbMessage.setText("Minimum amount of 2000 rupees required");
            return ;
        }
        FileWriter fw = new FileWriter("database/Accounts.csv" , true) ;
        CSVWriter writer = new CSVWriter(fw) ;
        String row[] ;
        if(tg.getSelectedToggle()==btnSavings){
            Account obj = new Account(user.username , "Savings" , tfMoney.getText().trim()) ;
            row = new String[]{obj.username , obj.accountID , obj.type , obj.balance} ;
        }
        else if(tg.getSelectedToggle()==btnBusiness){
            Account obj = new Account(user.username , "Business" , tfMoney.getText().trim()) ;
            row = new String[]{obj.username , obj.accountID , obj.type , obj.balance} ;
        }
        else{
            Account obj = new Account(user.username , "Funds" , tfMoney.getText().trim()) ;
            row = new String[]{obj.username , obj.accountID , obj.type , obj.balance} ;
        }
 
        writer.writeNext(row , false);
        writer.close();
        lbMessage.setVisible(true);
        lbMessage.setTextFill(Color.GREEN);
        lbMessage.setText("Account created successfully");
        tfMoney.setText("");
        tg.selectToggle(null);
    }

}
