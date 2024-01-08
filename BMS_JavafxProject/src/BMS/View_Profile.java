package BMS;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import pkg.AccountHolder;

public class View_Profile {

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
    Label lbUsername , lbMobNo , lbName ;

    DataSingleton data = DataSingleton.getInstance();
    AccountHolder user = data.getUser() ;

    @FXML
    void initialize() {
        lbName.setText("Name  :  " + user.name);
        lbMobNo.setText("Mobile Number  :  " + ((Long)user.mobNo).toString());
        lbUsername.setText("Username  :  " + user.username);
    }

}
