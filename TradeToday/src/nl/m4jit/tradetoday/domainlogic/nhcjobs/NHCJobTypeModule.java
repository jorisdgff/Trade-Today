package nl.m4jit.tradetoday.domainlogic.nhcjobs;

import nl.m4jit.framework.ValidationException;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.*;

public class NHCJobTypeModule  {

    private final NHCJobTypeTable table;
    private static NHCJobTypeModule instance;
    
    private NHCJobTypeModule(){
    
        table = NHCJobTypeTable.getInstance();
    }
    
    public NHCJobTypeGateway create(String name){
        
        if (name.isEmpty()) {

            throw new ValidationException("no name");
        }else{
            
            NHCJobTypeGateway type = new NHCJobTypeGateway();
            type.setName(name);
            table.insert(type);
            
            return type;
        }
    }
    
    public void update(NHCJobTypeGateway type, String name){
        
        if (name.isEmpty()) {

            throw new ValidationException("no name");
        }else{
            
            type.setName(name);
            table.update(type);
        }
    }
    
    public static NHCJobTypeModule getInstance(){
        
        if(instance == null){
            
            instance = new NHCJobTypeModule();
        }
        
        return instance;
    }
}
