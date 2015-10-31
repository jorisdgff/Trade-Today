package nl.m4jit.tradetoday.dataaccess.members;

import java.util.*;
import nl.m4jit.framework.sqlaccess.*;

public class HeardOfTypeTable extends AbstractTable<HeardOfTypeGateway>{
 
    private final String SELECTSTRING = "SELECT hot FROM HeardOfType hot";
    private static HeardOfTypeTable instance;
    
    private HeardOfTypeTable(){}
    
    public HeardOfTypeGateway retreive(int number) {
        
        return (HeardOfTypeGateway) executeQuery(SELECTSTRING, "where hot.number = " + number).get(0);
    }
    
    public List<HeardOfTypeGateway> findAll() {
        
        return executeQuery(SELECTSTRING, "");
    }
    
    public static HeardOfTypeTable getInstance(){
        
        if(instance == null){
            
            instance = new HeardOfTypeTable();
        }
        
        return instance;
    }
}