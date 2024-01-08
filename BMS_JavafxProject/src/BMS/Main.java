package BMS;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException{      
        FlowPane splash = FXMLLoader.load(getClass().getResource("mainframe.fxml")) ;
        primaryStage.setTitle("Bank Management System");
        Scene sc = new Scene(splash) ;
        primaryStage.setScene(sc);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}