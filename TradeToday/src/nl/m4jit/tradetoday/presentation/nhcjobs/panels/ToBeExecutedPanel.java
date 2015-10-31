package nl.m4jit.tradetoday.presentation.nhcjobs.panels;

import javax.swing.JOptionPane;
import nl.m4jit.framework.presentation.swing.abstractdialogs.*;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.*;
import nl.m4jit.tradetoday.domainlogic.nhcjobs.*;
import nl.m4jit.tradetoday.presentation.nhcjobs.iframes.SettleNHCJobFrame;

/**
 *
 * @author joris
 */
public class ToBeExecutedPanel extends TableDialog<NHCJobGateway>{

    private TableDialog nextdialog;
    
    public ToBeExecutedPanel(TableDialog nextdialog){

        super(new ToBeExecutedModel(), true);
        this.nextdialog = nextdialog;
        addButton("Afrekenen", "settle", RIGHT);
    }
    
    @Override
    public String getScreenTitle() {

        return "Ingepland";
    }

    @Override
    protected void processUnknowActionCommand(String ac) {
        
        if(ac.equals("settle")){
            
            NHCJobGateway selectedJob = getSelectedRecord();
            new SettleNHCJobFrame(selectedJob, this, nextdialog);
        }
    }
}