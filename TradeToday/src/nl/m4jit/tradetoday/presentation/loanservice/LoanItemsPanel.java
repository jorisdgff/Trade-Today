package nl.m4jit.tradetoday.presentation.loanservice;

import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.loanservice.LoanItemGateway;
import nl.m4jit.tradetoday.domainlogic.loanservice.LoanItemsModel;

public class LoanItemsPanel extends TableDialog<LoanItemGateway>{

    public LoanItemsPanel(){

        super(new LoanItemsModel(), true);
        
        enableNew();
        enableChange();
    }
    
    @Override
    public String getScreenTitle() {

        return "Gereedschappen";
    }

    @Override
    protected void newRecord() {

        new LoanItemIFrame(this);
    }

    @Override
    protected void changeRecord(LoanItemGateway record) {
        
        new LoanItemIFrame(record, this);
    }
}