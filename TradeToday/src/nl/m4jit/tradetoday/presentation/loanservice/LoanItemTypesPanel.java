package nl.m4jit.tradetoday.presentation.loanservice;

import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.loanservice.LoanItemTypeGateway;
import nl.m4jit.tradetoday.domainlogic.loanservice.LoanItemTypesModel;

public class LoanItemTypesPanel extends TableDialog<LoanItemTypeGateway>{

    public LoanItemTypesPanel(){

        super(new LoanItemTypesModel(), true); 
        
        enableNew();
        enableChange();
    }
    
    @Override
    public String getScreenTitle() {

        return "Typen";
    }

    @Override
    public void newRecord() {

        new LoanItemTypeIFrame(this);
    }

    @Override
    protected void changeRecord(LoanItemTypeGateway record) {

        new LoanItemTypeIFrame(record, this);
    }
}