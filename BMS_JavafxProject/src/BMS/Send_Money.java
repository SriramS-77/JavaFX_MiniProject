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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import pkg.* ;

public class Send_Money {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void PressBack(ActionEvent ae) throws IOException {
        FlowPane Accountmenu = FXMLLoader.load(getClass().getResource("Account_Menu.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(Accountmenu);
    }

    DataSingleton data = DataSingleton.getInstance() ;
    Account account = data.getAccount() ;
    AccountHolder user = data.getUser() ;

    @FXML
    TextField tfUsername , tfMoney ;
    @FXML
    Label lbMessage , lbCheckMessage ;
    @FXML
    PasswordField pfPassword ;
    @FXML
    Button btnAuthorize ;

    @FXML
    void initialize() {        
    }

    @FXML
    void PressSendMoney(ActionEvent ae) throws IOException, CsvException {
        if(tfUsername.getText().trim().equals(user.username)){
            lbMessage.setVisible(true);
            lbMessage.setText("Error! Cannot send money to yourself!") ;
            lbMessage.setTextFill(Color.RED);
            return ;
        }
        if(Double.parseDouble(tfMoney.getText().trim()) < 0){
            lbMessage.setVisible(true);
            lbMessage.setText("Invalid field entry!");
            lbMessage.setTextFill(Color.RED);
            return ;
        }
        if(Double.parseDouble(account.balance) < Double.parseDouble(tfMoney.getText().trim())){
            lbMessage.setVisible(true);
            lbMessage.setText("Insufficient balance");
            lbMessage.setTextFill(Color.RED);
            return ;
        }
        CSVReader csvReader = new CSVReader(new FileReader(new File("database/Accounts.csv"))) ;
        List<String[]> allData = csvReader.readAll() ;
        boolean recepientExists = false ;
        for(int i=0 ; i < allData.size() ; i++){
            if(allData.get(i)[1].equals(account.accountID)){
            }
            else if(allData.get(i)[0].equals(tfUsername.getText().trim())){
                recepientExists = true ;
                if(allData.get(i)[2].equals("Savings")){
                }
            }
        }
        if(!recepientExists){
            lbMessage.setVisible(true);
            lbMessage.setText("This user does not exist");
            lbMessage.setTextFill(Color.RED);
            return ;
        }
        lbMessage.setVisible(false);
        lbCheckMessage.setVisible(true);
        pfPassword.setVisible(true);
        btnAuthorize.setVisible(true);
        tfUsername.setEditable(false);
        tfMoney.setEditable(false);
    }

    @FXML
    void PressAuthorize(ActionEvent ae) throws IOException, CsvException {
        if(!(pfPassword.getText().trim().equals(user.password))){
            lbCheckMessage.setText("Incorrect Password");
            lbCheckMessage.setTextFill(Color.RED);
            return ;
        }
        CSVReader csvReader = new CSVReader(new FileReader(new File("database/Accounts.csv"))) ;
        List<String[]> allData = csvReader.readAll() ;
        int sender=0 , recepient=0 ;
        for(int i=0 ; i < allData.size() ; i++){
            if(allData.get(i)[1].equals(account.accountID)){
                sender = i ;
            }
            else if(allData.get(i)[0].equals(tfUsername.getText().trim())){
                if(allData.get(i)[2].equals("Savings")){
                    recepient = i ;
                }
            }
        }
        Account rec = new Account(allData.get(recepient)[0] , allData.get(recepient)[2], 
                                  allData.get(recepient)[3].trim() , allData.get(recepient)[1]) ;
        Transaction transaction = account.transfer(rec , tfMoney.getText()) ;
        
        allData.get(sender)[3] = account.balance ;
        allData.get(recepient)[3] = rec.balance ;
        CSVWriter csvWriter = new CSVWriter(new FileWriter(new File("database/Accounts.csv"))) ;
        csvWriter.writeAll(allData);
        csvWriter.flush() ;
        
        pfPassword.setVisible(false);
        lbCheckMessage.setVisible(false);
        btnAuthorize.setVisible(false);
        lbMessage.setVisible(true);
        lbMessage.setTextFill(Color.GREEN);
        lbMessage.setText("Transaction Successful");
        tfUsername.setEditable(true);
        tfMoney.setEditable(true);
        tfUsername.setText("");
        tfMoney.setText("");
        
        addTransaction(transaction);
        return ;
    }

    void addTransaction(Transaction t) throws IOException{
        FileWriter fw = new FileWriter("database/Transactions.csv",true) ;
        CSVWriter writer = new CSVWriter(fw) ;
        String row[] = {t.transactionID , t.amount , t.username , t.accountID , t.recepient_username , t.recepient_accountID} ;
        writer.writeNext(row,false);
        writer.close();
        return ;
    }

}
