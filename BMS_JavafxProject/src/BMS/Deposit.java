package BMS;

import java.io.File;
import java.io.FileNotFoundException;
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
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import pkg.* ;

public class Deposit {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void PressBack(ActionEvent ae) throws IOException {
        FlowPane Accountmenu = FXMLLoader.load(getClass().getResource("Account_Menu.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(Accountmenu);
    }

    @FXML
    Label lbMessage ;
    @FXML
    TextField tfDeposit ;

    DataSingleton data = DataSingleton.getInstance() ;
    Account account = data.getAccount() ;

    @FXML
    void initialize() {
    }

    @FXML
    void PressDepositMoney(ActionEvent ae) throws IOException, CsvException, FileNotFoundException {
        if(tfDeposit.getText().trim().equals("") || Double.parseDouble(tfDeposit.getText().trim()) < 0){
            lbMessage.setVisible(true);
            lbMessage.setText("Input proper amount to deposit");
            return ;
        }
        CSVReader csvReader = new CSVReader(new FileReader(new File("database/Accounts.csv"))) ;
        List<String[]> allData = csvReader.readAll() ;
        for(int i=0 ; i < allData.size() ; i++){
            if(allData.get(i)[1].equals(account.accountID)){
                account.deposit(tfDeposit.getText());
                allData.get(i)[3] = account.balance ;
                break ;
            }
        }
        CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("database/Accounts.csv"))) ;
        csvWriter.writeAll(allData);
        csvWriter.flush() ;
        lbMessage.setVisible(true);
        lbMessage.setText("Amount Deposited Successfully");
        tfDeposit.setText("");
    }

}
