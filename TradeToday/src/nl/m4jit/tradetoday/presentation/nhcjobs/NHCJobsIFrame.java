package nl.m4jit.tradetoday.presentation.nhcjobs;

import nl.m4jit.framework.presentation.swing.abstractdialogs.TabbedDialog;
import nl.m4jit.tradetoday.presentation.nhcjobs.panels.ToBeApprovedPanel;
import nl.m4jit.tradetoday.presentation.nhcjobs.panels.ToBeExecutedPanel;
import nl.m4jit.tradetoday.presentation.nhcjobs.panels.ToBePlannedPanel;
import nl.m4jit.tradetoday.presentation.nhcjobs.panels.WithHelpSignalsPanel;

public class NHCJobsIFrame extends TabbedDialog{

    public NHCJobsIFrame() {
    
        WithHelpSignalsPanel withHelpSignalsPanel = new WithHelpSignalsPanel();
        ToBeExecutedPanel toBeExecutedPanel = new ToBeExecutedPanel(withHelpSignalsPanel);
        ToBePlannedPanel toBePlannedPanel = new ToBePlannedPanel(toBeExecutedPanel);
        ToBeApprovedPanel toBeApprovedPanel = new ToBeApprovedPanel(toBePlannedPanel);
        
        addPanel(toBeApprovedPanel);
        addPanel(toBePlannedPanel);
        addPanel(toBeExecutedPanel);
        addPanel(withHelpSignalsPanel);
        
        setUI();
    }
    
    @Override
    public String getScreenTitle() {
        
        return "Overzicht klussen";
    }
}