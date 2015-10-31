package nl.m4jit.tradetoday.domainlogic.loanservice;

import nl.m4jit.framework.ValidationException;
import nl.m4jit.tradetoday.dataaccess.loanservice.*;

public class LoanItemModule {

    private final LoanItemTable table;
    private static LoanItemModule instance;

    private LoanItemModule() {

        table = LoanItemTable.getInstance();
    }

    public LoanItemGateway create(LoanItemTypeGateway loantype, String description){
        
        if (loantype == null) {

            throw new ValidationException("no type");
        } else {
            
            LoanItemGateway loanItem = new LoanItemGateway();
            loanItem.setType(loantype);
            loanItem.setDescription(description);
            table.insert(loanItem);
            
            return loanItem;
        }
    }
    
    public void update(LoanItemGateway loanItem, LoanItemTypeGateway loantype, String description){
        
        if (loantype == null) {

            throw new ValidationException("no type");
        } else {
            
            loanItem.setType(loantype);
            loanItem.setDescription(description);
            table.update(loanItem);
        }
    }

    public static LoanItemModule getInstance() {

        if (instance == null) {

            instance = new LoanItemModule();
        }

        return instance;
    }
}
