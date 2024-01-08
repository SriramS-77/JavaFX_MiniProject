package BMS;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pkg.Account;

public class View_Transactions {

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
    ListView<Label> listView ;

    DataSingleton data = DataSingleton.getInstance() ;
    Account account = data.getAccount() ;

    @FXML
    void initialize() throws IOException, CsvException {
        listView.setEditable(false);
        String message ;
        Label lb ;
        CSVReader csvReader = new CSVReader(new FileReader(new File("database/Transactions.csv"))) ;
        List<String[]> allData = csvReader.readAll() ;
        for(int i=0 ; i < allData.size() ; i++){
            if(allData.get(i)[3].equals(account.accountID)){
                message = allData.get(i)[0]+"  |  Sent "+Account.convert(allData.get(i)[1])+" rupees to "+allData.get(i)[4] ;
                lb = new Label(message) ;
                lb.setTextFill(Color.RED);
                lb.setFont(new Font("System",13));
                listView.getItems().add(lb) ;
            }
            else if(allData.get(i)[5].trim().equals(account.accountID)){
                message = allData.get(i)[0]+"  |  Received "+Account.convert(allData.get(i)[1])+" rupees from "+allData.get(i)[2] ;
                lb = new Label(message) ;
                lb.setTextFill(Color.GREEN);
                lb.setFont(new Font("System",13));
                listView.getItems().add(lb) ;
            }
        }
    }
}
