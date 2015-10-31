package nl.m4jit.tradetoday.dataaccess.members;

import java.util.List;
import nl.m4jit.framework.sqlaccess.AbstractTable;

public class IncomeTypeTable extends AbstractTable<IncomeTypeGateway>{
    
    private final String SELECTSTRING = "SELECT i FROM IncomeType i";
    private static IncomeTypeTable instance;
    
    private IncomeTypeTable(){}
    
    public IncomeTypeGateway retreive(int number){
        
        return (IncomeTypeGateway) executeQuery(SELECTSTRING, "where i.number = " + number).get(0);
    }
    
    public List<IncomeTypeGateway> findAll(){
        
        return executeQuery(SELECTSTRING, "");
    }
    
    public static IncomeTypeTable getInstance(){
        
        if(instance == null){
            
            instance = new IncomeTypeTable();
        }
        
        return instance;
    }
}
