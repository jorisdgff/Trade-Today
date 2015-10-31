package nl.m4jit.tradetoday.dataaccess.users;

import nl.m4jit.tradetoday.dataaccess.users.UseraccountGateway;
import java.util.*;
import nl.m4jit.framework.sqlaccess.*;

public class UseraccountTable extends AbstractTable<UseraccountGateway>{

    private final String SELECTSTRING = "SELECT ua FROM Useraccount ua";
    private static UseraccountTable instance;
    
    private UseraccountTable(){}

    public UseraccountGateway find(int number) {

        return (UseraccountGateway) executeQuery(SELECTSTRING, "where ua.number = " + number).get(0);
    }
    
    public List<UseraccountGateway> findAll() {

        return executeQuery(SELECTSTRING, "");
    }

    public UseraccountGateway findByUsernameAndPassword(String username, String md5password) {

        try{
            
            return (UseraccountGateway) executeQuery(SELECTSTRING, "where ua.username = '"+ username +"' and ua.password = '"+ md5password +"'").get(0);
        }catch(ArrayIndexOutOfBoundsException ex){

            return null;
        }
    }
    
    public static UseraccountTable getIntance(){
        
        if(instance == null){
            
            instance = new UseraccountTable();
        }
        
        return instance;
    }
}
