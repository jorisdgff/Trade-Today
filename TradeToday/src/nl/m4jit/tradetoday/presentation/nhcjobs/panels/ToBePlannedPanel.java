package nl.m4jit.tradetoday.presentation.nhcjobs.panels;

import javax.swing.JOptionPane;
import nl.m4jit.framework.presentation.swing.abstractdialogs.*;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.*;
import nl.m4jit.tradetoday.domainlogic.nhcjobs.*;
import nl.m4jit.tradetoday.presentation.nhcjobs.iframes.PlanNHCJobFrame;

/**
 *
 * @author joris
 */
public class ToBePlannedPanel extends TableDialog<NHCJobGateway>{

    private TableDialog nextdialog;
    
    public ToBePlannedPanel(TableDialog nextdialog){

        super(new ToBePlannedModel(), true);
        this.nextdialog = nextdialog;
        
        enablePrint();
        addButton("Inplannen", "plan", RIGHT);
    }
    
    @Override
    public String getScreenTitle() {

        return "Wachtlijst";
    }

    @Override
    protected void processUnknowActionCommand(String ac) {

        if(ac.equals("plan")){
            
            NHCJobGateway selectedJob = getSelectedRecord();
            new PlanNHCJobFrame(selectedJob, this, nextdialog);
        }
    }
}