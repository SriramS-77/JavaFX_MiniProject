package pkg ;

import java.io.Serializable;

public class AccountHolder implements Serializable{

    private static final long serialVersionUID = -9038111913870812103L;

    public String name ;
    public long mobNo ;
    public String username ;
    public String password ;
    public AccountHolder(){}
    public AccountHolder(String name , String mobNo , String username , String password){
        this.name=name ;
        this.mobNo = Long.parseLong(mobNo) ;
        this.username=username ;
        this.password=password ;
    }
}
