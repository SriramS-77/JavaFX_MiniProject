package pkg ;

import java.io.Serializable;
import java.util.Random;

public class Account implements Serializable {
    public String username ;
    public String accountID ;
    public String type ;
    public String balance ;
    public String interest ;
    static double savingsAPR = 0.05 ;
    static double businessAPR ;
    static double fundsAPR = 0.08 ;
    public Account(String username , String type , String balance , String accountID){
        this.username=username ;
        this.type=type ;
        this.balance = doubleFormat(balance.trim()) ;
        this.accountID=accountID ;
    }
    public Account(String username , String type , String balance){
        this.username=username ;
        this.type=type ;
        this.balance = doubleFormat(balance.trim()) ;
        this.accountID=generateAccountID() ;
    }
    public void disp(){
        System.out.println(username) ;
        System.out.println(accountID) ;
        System.out.println(type) ;
        System.out.println(balance) ;
    }
    public Transaction transfer(Account recepient , String str_amount){
        double amount = Double.parseDouble(str_amount.trim()) ;
        if(amount > Double.parseDouble(balance)){
            throw new RuntimeException() ;
        }
        this.withdraw(str_amount.trim());
        recepient.deposit(str_amount.trim());
        return new Transaction(username, accountID, doubleFormat(str_amount.trim()), recepient.username, recepient.accountID) ;
    } 

    public void deposit(String amount){
        double b = Double.parseDouble(balance) + Double.parseDouble(amount) ;
        balance = ((Double)b).toString() ;
        balance = doubleFormat(balance) ;
        return ;
    }

    public void withdraw(String amount){
        double b = Double.parseDouble(balance) - Double.parseDouble(amount) ;
        balance = ((Double)b).toString() ;
        balance = doubleFormat(balance) ;
        return ;
    }

    public static String generateAccountID() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

        return generatedString ;
    }
    String doubleFormat(String num){
        num = String.format("%.2f" , Double.parseDouble(num)) ;
        return num ;
    }
    public static String convert(String str){
        StringBuffer newstr = new StringBuffer(str) ;
        for(int i = str.length()-6 ; i > 0 ; i-=3)
            newstr.insert(i , ",") ;
        return newstr.toString() ;
    }
}
