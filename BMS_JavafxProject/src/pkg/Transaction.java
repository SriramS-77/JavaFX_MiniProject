package pkg ;

import java.io.Serializable;
import java.util.Random;

public class Transaction implements Serializable {
    public String username ;
    public String accountID ;
    public String transactionID ;
    public String amount ;
    public String recepient_username ;
    public String recepient_accountID ;
    public Transaction(String username , String accountID , String amount , String recepient_username , String recepient_accountID){
        this.username=username ;
        this.accountID=accountID ;
        this.amount=amount ;
        this.recepient_username=recepient_username ;
        this.recepient_accountID=recepient_accountID ;
        this.transactionID=generateTransactionID() ;
    }

    public static String generateTransactionID() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 15;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

        return generatedString ;
    }
    double doubleFormat(String num){
        num = String.format("%.2f" , Double.parseDouble(num)) ;
        return Double.parseDouble(num) ;
    }
}
