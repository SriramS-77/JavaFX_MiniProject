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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import pkg.Account;
import pkg.AccountHolder;

public class Delete_Account {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBack;

    @FXML
    private ListView<RadioButton> listView;

    @FXML
    void PressBack(ActionEvent ae) throws IOException{
        FlowPane menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(menu);
    }

    DataSingleton data = DataSingleton.getInstance() ;
    AccountHolder user = data.getUser() ;
    ToggleGroup tg ;
    Account account ;

    @FXML
    PasswordField pfPassword ;
    @FXML
    Label lbWarning, lbPassword ;
    @FXML
    Button btnConfirm, btnDeleteAccount ;

    @FXML
    void initialize() throws IOException, CsvException {
        tg = new ToggleGroup() ;
        listView.setEditable(false);
        String message ;
        Account acc ;
        RadioButton btn ;
        CSVReader csvReader = new CSVReader(new FileReader(new File("database/Accounts.csv"))) ;
        List<String[]> allData = csvReader.readAll() ;
        for(int i=0 ; i < allData.size() ; i++){
            if(allData.get(i)[0].equals(user.username)){
                acc = new Account(allData.get(i)[0], allData.get(i)[2], allData.get(i)[3], allData.get(i)[1]) ;
                message = allData.get(i)[1]+"  |  "+allData.get(i)[2]+" Account  |  Balance : "+Account.convert(allData.get(i)[3])+" rupees" ;
                btn = new RadioButton(message) ;
                btn.setUserData(acc);
                btn.setToggleGroup(tg);
                listView.getItems().add(btn) ;
            }
        }
    }

    @FXML
    void PressDeleteAccount(ActionEvent ae) {
        account = (Account) tg.getSelectedToggle().getUserData() ;
        lbWarning.setVisible(true);
        lbPassword.setVisible(true);
        pfPassword.setVisible(true);
        btnConfirm.setVisible(true);
        List<Node> rarr = listView.getChildrenUnmodifiable() ;
        for(int i=0 ; i<rarr.size() ; i++){
            rarr.get(i).setDisable(true);
        }
        lbWarning.setText("Warning : "+Account.convert(account.balance)+" rupees will be withdrawn from account");
        btnDeleteAccount.setDisable(true);
    }

    @FXML
    void PressConfirm(ActionEvent ae) throws IOException, CsvException{
        btnDeleteAccount.setDisable(true);
        if(pfPassword.getText().trim().equals("")){
            lbPassword.setText("Please fill the password field");
            lbPassword.setTextFill(Color.RED);
            return ;
        }
        else if(! pfPassword.getText().trim().equals(user.password)){
            lbPassword.setText("Incorrect Password");
            lbPassword.setTextFill(Color.RED);
            return ;
        }
        CSVReader csvReader = new CSVReader(new FileReader(new File("database/Accounts.csv"))) ;
        List<String[]> allData = csvReader.readAll() ;
        int row = -1 ;
        for(int i = 0 ; i < allData.size() ; i++){
            if(allData.get(i)[1].equals(account.accountID)){
                row = i ;
                break ;
            }
        }
        if(row != -1){
            allData.remove(row) ;
            CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("database/Accounts.csv"))) ;
            csvWriter.writeAll(allData);
            csvWriter.flush();
            pfPassword.setVisible(false);
            btnConfirm.setVisible(false);
            lbWarning.setText("Account deleted successfully");
            lbWarning.setTextFill(Color.GREEN);
            lbPassword.setText(Account.convert(account.balance) + " rupees withdrawn from deleted account");
            lbPassword.setMaxSize(350, 0);
            lbPassword.setTextFill(Color.GREEN);
        }
    }

}
