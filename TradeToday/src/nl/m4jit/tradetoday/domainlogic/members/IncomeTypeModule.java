package nl.m4jit.tradetoday.domainlogic.members;

import nl.m4jit.framework.*;
import nl.m4jit.tradetoday.dataaccess.members.*;

public class IncomeTypeModule  {

    private final IncomeTypeTable table;
    private static IncomeTypeModule instance;
    
    private IncomeTypeModule(){
    
        table = IncomeTypeTable.getInstance();
    }
    
    public IncomeTypeGateway create(String name){
        
        if (name.isEmpty()) {

            throw new ValidationException("no name");
        }else{
            
            IncomeTypeGateway incometype = new IncomeTypeGateway();
            incometype.setName(name);
            table.insert(incometype);
            
            return incometype;
        }
    }
    
    public void update(IncomeTypeGateway incometype, String name){
     
        if (name.isEmpty()) {

            throw new ValidationException("no name");
        }else{
            
            incometype.setName(name);
            table.update(incometype);
        }
    }
    
    public static IncomeTypeModule getInstance(){
        
        if(instance == null){
            
            instance = new IncomeTypeModule();
        }
        
        return instance;
    }
}
