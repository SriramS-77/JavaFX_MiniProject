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
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import pkg.Account;
import pkg.AccountHolder;

public class View_Accounts {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    void PressBack(ActionEvent ae) throws IOException {
        FlowPane menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(menu);
    }

    DataSingleton data = DataSingleton.getInstance();
    AccountHolder user = data.getUser();
    @FXML
    ListView<String> listView ;

    @FXML
    void initialize() throws IOException, CsvException {
        listView.setEditable(false);
        String message ;
        CSVReader csvReader = new CSVReader(new FileReader(new File("database/Accounts.csv"))) ;
        List<String[]> allData = csvReader.readAll() ;
        for(int i=0 ; i < allData.size() ; i++){
            if(allData.get(i)[0].equals(user.username)){
                message = allData.get(i)[1]+"  |  "+allData.get(i)[2]+" Account  |  Balance : "+Account.convert(allData.get(i)[3])+" rupees" ;
                listView.getItems().add(message) ;
            }
        }
    }

}
