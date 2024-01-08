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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import pkg.* ;

public class Choose_Account{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void PressBack(ActionEvent ae) throws IOException {
        FlowPane menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        ((javafx.scene.Node)ae.getSource()).getScene().setRoot(menu);
    }    

    @FXML
    FlowPane flowNode ;
    DataSingleton data = DataSingleton.getInstance() ;
    AccountHolder user ;

    @FXML
    public void initialize() throws CsvException {
        user = data.getUser() ;
        String arr[] = new String[4] ;
        Button btn ;
        try{
            CSVReader csvReader = new CSVReader(new FileReader(new File("database/Accounts.csv"))) ;
            List<String[]> allData = csvReader.readAll() ;
            for(int i = 0 ; i < allData.size() ; i++) {  
                if(allData.get(i)[0].equals(user.username)){
                    arr = allData.get(i) ;
                    Account account = new Account(arr[0],arr[2],arr[3].trim(),arr[1]) ;
                    btn = new Button(arr[2] + " Account : " + arr[1]) ;
                    btn.setUserData(account);
                    flowNode.getChildren().add(btn) ;
                    btn.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent ae){
                            Stage myStage = (Stage)(((Node)(ae.getSource())).getScene().getWindow()) ;
                            myStage.setUserData((Account) (((Node) ae.getSource()).getUserData())) ;
                            data.setAccount((Account)(((Node)ae.getSource()).getUserData()));
                            try{
                                FlowPane accountMenu = FXMLLoader.load(getClass().getResource("Account_Menu.fxml"));
                                ((javafx.scene.Node)ae.getSource()).getScene().setRoot(accountMenu);
                            }
                            catch(IOException e){}
                        }
                    });
                } 
            }                   
        }
        catch(IOException e){}
    }

}
