package nl.m4jit.tradetoday.dataaccess.loanservice;

import java.util.List;
import nl.m4jit.framework.sqlaccess.*;

public class LettingruleTable extends AbstractTable<LettingruleGateway>{
    
    private final String SELECTSTRING = "SELECT lr FROM Lettingrule lr";
    private static LettingruleTable instance;
    
    private LettingruleTable(){}
    
    public List<LettingruleGateway> findByLetting(int lettingnumber){
        
        return executeQuery(SELECTSTRING, "WHERE lr.numberLetting = " + lettingnumber);
    }
    
    public static LettingruleTable getInstance(){
        
        if(instance == null){
            
            instance = new LettingruleTable();
        }
        
        return instance;
    }
}