package BMS;
import pkg.* ;

public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton() ;
    private AccountHolder accountHolder ;
    private Account account ;

    public static DataSingleton getInstance(){
        return instance ;
    }

    public Account getAccount(){
        return account ;
    }
    public void setAccount(Account account){
        this.account = account ;
    }

    public AccountHolder getUser(){
        return accountHolder ;
    }
    public void setUser(AccountHolder accountHolder){
        this.accountHolder = accountHolder ;
    }
}
