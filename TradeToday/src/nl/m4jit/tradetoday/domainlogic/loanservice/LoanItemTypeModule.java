package nl.m4jit.tradetoday.domainlogic.loanservice;

import nl.m4jit.framework.*;
import nl.m4jit.tradetoday.dataaccess.loanservice.*;

public class LoanItemTypeModule  {

    private final LoanItemTypeTable table;
    private static LoanItemTypeModule instance;
    
    private LoanItemTypeModule(){
    
        table = LoanItemTypeTable.getInstance();
    }
    
    public LoanItemTypeGateway create(String name, String description, int points){
        
        if (name.isEmpty()) {

            throw new ValidationException("no name");
        } else {
         
            LoanItemTypeGateway loanItemType = new LoanItemTypeGateway();
            loanItemType.setName(name);
            loanItemType.setDescription(description);
            loanItemType.setPoints(points);
            table.insert(loanItemType);
            
            return loanItemType;
        }
    }
    
    public void update(LoanItemTypeGateway loanItemType, String name, String description, int points){
        
        if (name.isEmpty()) {

            throw new ValidationException("no name");
        } else {
            
            loanItemType.setName(name);
            loanItemType.setDescription(description);
            loanItemType.setPoints(points);
            table.update(loanItemType);
        }
    }
    
    public static LoanItemTypeModule getInstance(){
        
        if(instance == null){
            
            instance = new LoanItemTypeModule();
        }
        
        return instance;
    }
}