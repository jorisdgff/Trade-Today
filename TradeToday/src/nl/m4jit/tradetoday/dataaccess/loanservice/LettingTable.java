package nl.m4jit.tradetoday.dataaccess.loanservice;

import java.util.List;
import nl.m4jit.framework.sqlaccess.*;

public class LettingTable extends AbstractTable<LettingGateway>{
    
    private final String SELECTSTRING = "SELECT l FROM Letting l";
    private static LettingTable instance;
    
    private LettingTable(){}
    
    public List<LettingGateway> findRunning(){
        
        return executeQuery(SELECTSTRING, "WHERE l.checkedIn = 0 ORDER BY l.submissionDate");
    }
    
    public static LettingTable getInstance(){
        
        if(instance == null){
            
            instance = new LettingTable();
        }
        
        return instance;
    }
}