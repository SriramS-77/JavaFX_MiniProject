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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import pkg.* ;

public class mainframe {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    public mainframe(){}

    @FXML
    void initialize() {
    }

    @FXML
    TextField usernameTF;

    @FXML
    PasswordField passwordPF;

    @FXML
    Label lbLogin;

    DataSingleton data = DataSingleton.getInstance() ;

    @FXML
    void PressLogin(ActionEvent ae) throws IOException, ClassNotFoundException, CsvException{
        boolean foundUsername = false ;
        AccountHolder obj ;
        String arr[] = new String[4] ;        
        CSVReader csvReader = new CSVReader(new FileReader(new File("database/Account_Holders.csv"))) ;
        List<String[]> allData = csvReader.readAll() ;
        for(int i = 0 ; i < allData.size() ; i++){
            if(allData.get(i)[2].equals(usernameTF.getText().trim())){
                foundUsername = true ;
                arr = allData.get(i) ;
                break ;
            }
        }

        if(!foundUsername){
            lbLogin.setVisible(true) ;
        }
        else{
            if(arr[3].trim().equals(passwordPF.getText().trim())){
                lbLogin.setVisible(false);
                obj = new AccountHolder(arr[0],arr[1],arr[2],arr[3].trim()) ;
                ((javafx.scene.Node)ae.getSource()).getScene().setUserData(obj);
                data.setUser(obj) ;
                FlowPane menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                ((javafx.scene.Node)ae.getSource()).getScene().setRoot(menu);
            }
            else{
                lbLogin.setVisible(true);
            }
        }        
    }

    @FXML
    void PressSignUp(ActionEvent ae) throws IOException{
        FlowPane signup = FXMLLoader.load(getClass().getResource("Sign_Up_Page.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(signup);
    }

}
