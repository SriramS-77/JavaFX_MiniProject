package BMS ;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.opencsv.CSVWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import pkg.AccountHolder;

public class Sign_Up_Page {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {
    }

    @FXML
    Label lbMessage ;
    @FXML
    TextField tfName, tfMobNo, tfUsername, tfPassword ;

    DataSingleton data = DataSingleton.getInstance() ;
    
    @FXML
    void PressSignUp(ActionEvent ae){
        if(tfName.getText().isBlank() || tfMobNo.getText().isBlank() || tfUsername.getText().isBlank() || tfPassword.getText().isBlank()){
            lbMessage.setText("Please fill all the fields");
            lbMessage.setVisible(true);
        }
        else{
            boolean uniqueUsername = true ;
            String arr[] = new String[4] ;
            try{
                Scanner sc = new Scanner(new File("database/Account_Holders.csv"));
                sc.useDelimiter("\n");
                while (sc.hasNext()) {  
                    arr = sc.next().split(",") ; 
                    if(arr[2].equals(tfUsername.getText().trim())){
                        uniqueUsername = false ;
                        break ;
                    } 
                }
            }
            catch(Exception e){}
            if(!uniqueUsername){
                lbMessage.setText("Username already taken");
                lbMessage.setVisible(true);
            }
            else{
                try{
                    FileWriter fw = new FileWriter("database/Account_Holders.csv",true) ;
                    CSVWriter writer = new CSVWriter(fw) ;
                    String row[] = {tfName.getText().trim(),tfMobNo.getText().trim(),tfUsername.getText().trim(),tfPassword.getText().trim()} ;
                    writer.writeNext(row,false);
                    writer.close();
                    AccountHolder obj = new AccountHolder(tfName.getText().trim(),tfMobNo.getText().trim(),tfUsername.getText().trim(),tfPassword.getText().trim()) ;
                    ((javafx.scene.Node)ae.getSource()).getScene().setUserData(obj);
                    data.setUser(obj) ;
                    FlowPane first_account = FXMLLoader.load(getClass().getResource("First_Account.fxml")) ;
                    ((javafx.scene.Node)ae.getSource()).getScene().setRoot(first_account) ;                    
                }
                catch(IOException e){}
            }
        }
    }
    @FXML
    void PressBack(ActionEvent ae) throws IOException{
        FlowPane back = FXMLLoader.load(getClass().getResource("mainframe.fxml")) ;
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(back) ;
    }

}
