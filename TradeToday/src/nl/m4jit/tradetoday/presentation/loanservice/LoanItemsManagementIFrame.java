package nl.m4jit.tradetoday.presentation.loanservice;

import nl.m4jit.framework.presentation.swing.abstractdialogs.TabbedDialog;

public class LoanItemsManagementIFrame extends TabbedDialog{

    public LoanItemsManagementIFrame() {
        
        addPanel(new LoanItemTypesPanel());
        addPanel(new LoanItemsPanel());
    }
    
    @Override
    public String getScreenTitle() {

        return "Gereedschap beheer";
    }
}
