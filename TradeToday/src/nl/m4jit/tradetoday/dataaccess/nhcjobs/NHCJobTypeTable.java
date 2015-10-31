package nl.m4jit.tradetoday.dataaccess.nhcjobs;

import java.util.List;
import nl.m4jit.framework.sqlaccess.*;

public class NHCJobTypeTable extends AbstractTable<NHCJobTypeGateway>{
    
    private final String SELECTSTRING = "SELECT njt FROM NHCJobType njt";
    private static NHCJobTypeTable instance;
    
    private NHCJobTypeTable(){}
    
    public List<NHCJobTypeGateway> findAll(){
        
        return executeQuery(SELECTSTRING, "");
    }
    
    public NHCJobTypeGateway findByNumber(int number){
        
        return (NHCJobTypeGateway) executeQuery(SELECTSTRING, "where njt.number = " + number).get(0);
    }
    
    public static NHCJobTypeTable getInstance(){
        
        if(instance == null){
            
            instance = new NHCJobTypeTable();
        }
        
        return instance;
    }
}