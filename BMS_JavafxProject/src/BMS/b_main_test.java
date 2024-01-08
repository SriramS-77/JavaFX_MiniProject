package BMS;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class b_main_test {
    public static void main(String args[]) throws IOException, ClassNotFoundException{
            Scanner sc = new Scanner(new File("database/Account_Holders.csv"));  
           sc.useDelimiter("\n");   //sets the delimiter pattern  
            while (sc.hasNext()) {  
                System.out.println(sc.next());  //find and returns the next complete token from this scanner  
                System.out.println();
            }   
    }    
}
