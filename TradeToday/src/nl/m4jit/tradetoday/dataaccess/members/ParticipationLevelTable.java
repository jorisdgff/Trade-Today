package nl.m4jit.tradetoday.dataaccess.members;

import java.util.List;
import nl.m4jit.framework.sqlaccess.AbstractTable;

public class ParticipationLevelTable extends AbstractTable<ParticipationLevelGateway>{
    
    private final String SELECTSTRING = "SELECT pl FROM ParticipationLevel pl";
    private static ParticipationLevelTable instance;
    
    private ParticipationLevelTable(){}
    
    public ParticipationLevelGateway retreive(int number){
        
        return (ParticipationLevelGateway)executeQuery(SELECTSTRING, "WHERE pl.number = " + number).get(0);
    }
    
    public List<ParticipationLevelGateway> findAll(){
        
        return executeQuery(SELECTSTRING, "ORDER BY pl.indexnumber");
    }
    
    public static ParticipationLevelTable getInstance(){
        
        if(instance == null){
            
            instance = new ParticipationLevelTable();
        }
        
        return instance;
    }
}
