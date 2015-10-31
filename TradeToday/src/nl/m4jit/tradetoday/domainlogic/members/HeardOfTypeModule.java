package nl.m4jit.tradetoday.domainlogic.members;

import nl.m4jit.framework.ValidationException;
import nl.m4jit.tradetoday.dataaccess.members.*;

public class HeardOfTypeModule  {

    private final HeardOfTypeTable table;
    private static HeardOfTypeModule instance;
    
    private HeardOfTypeModule(){
    
        table = HeardOfTypeTable.getInstance();
    }
    
    public HeardOfTypeGateway create(String name, boolean refferinginstanceneeded){
        
        if (name.isEmpty()) {
            
            throw new ValidationException("no name");
        } else {
            
            HeardOfTypeGateway heardOfType = new HeardOfTypeGateway();
            heardOfType.setName(name);
            heardOfType.setRefferingInstanceNeeded(refferinginstanceneeded);
            table.insert(heardOfType);
            
            return heardOfType;
        }
    }
    
    public void update(HeardOfTypeGateway heardOfType, String name, boolean refferinginstanceneeded){
        
        if (name.isEmpty()) {
            
            throw new ValidationException("no name");
        } else {
            
            heardOfType.setName(name);
            heardOfType.setRefferingInstanceNeeded(refferinginstanceneeded);
            table.update(heardOfType);
        }
    }
    
    public static HeardOfTypeModule getInstance(){
        
        if(instance == null){
            
            instance = new HeardOfTypeModule();
        }
        
        return instance;
    }
}
