package nl.m4jit.tradetoday.dataaccess.members;

import java.util.List;
import nl.m4jit.framework.sqlaccess.AbstractTable;

public class RefferingInstanceTable extends AbstractTable<RefferingInstanceGateway>{
 
    private final String SELECTSTRING = "SELECT ri FROM RefferingInstance ri";
    private static RefferingInstanceTable instance;
    
    private RefferingInstanceTable(){}
    
    public RefferingInstanceGateway retreive(int number) {
        
        return (RefferingInstanceGateway) executeQuery(SELECTSTRING, "where ri.number = " + number).get(0);
    }
    
    public List<RefferingInstanceGateway> findAll() {
        
        return executeQuery(SELECTSTRING, "ORDER BY ri.ordering");
    }
    
    public static RefferingInstanceTable getInstance(){
        
        if(instance == null){
            
            instance = new RefferingInstanceTable();
        }
        
        return instance;
    }
}