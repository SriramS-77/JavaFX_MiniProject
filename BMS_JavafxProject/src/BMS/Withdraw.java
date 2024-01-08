package BMS;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import pkg.Account;

public class Withdraw {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    void PressBack(ActionEvent ae) throws IOException{
        FlowPane Accountmenu = FXMLLoader.load(getClass().getResource("Account_Menu.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(Accountmenu);
    }

    @FXML
    void initialize() {
    }

    @FXML
    Label lbMessage ;
    @FXML
    TextField tfWithdraw ;

    DataSingleton data = DataSingleton.getInstance() ;
    Account account = data.getAccount() ;

    @FXML
    void PressWithdrawMoney(ActionEvent ae) throws IOException, CsvException {
        if(Double.parseDouble(account.balance) < Double.parseDouble(tfWithdraw.getText().trim())){
            lbMessage.setVisible(true);
            lbMessage.setTextFill(Color.RED);
            lbMessage.setText("Insufficient balance");
            return ;
        }
        CSVReader csvReader = new CSVReader(new FileReader(new File("database/Accounts.csv"))) ;
        List<String[]> allData = csvReader.readAll() ;
        for(int i=0 ; i < allData.size() ; i++){
            if(allData.get(i)[1].equals(account.accountID)){
                account.withdraw(tfWithdraw.getText());
                allData.get(i)[3] = account.balance ;
                break ;
            }
        }
        CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("database/Accounts.csv"))) ;
        csvWriter.writeAll(allData);
        csvWriter.flush() ;
        lbMessage.setVisible(true);
        lbMessage.setTextFill(Color.GREEN) ;
        lbMessage.setText("Amount Withdrawn Successfully");
        tfWithdraw.setText("");
    }
}
