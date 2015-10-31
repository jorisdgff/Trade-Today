package nl.m4jit.tradetoday.presentation.nhcjobs.panels;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import nl.m4jit.framework.presentation.swing.abstractdialogs.*;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.*;
import nl.m4jit.tradetoday.domainlogic.nhcjobs.*;
import nl.m4jit.tradetoday.presentation.nhcjobs.iframes.ApproveNHCJobIFrame;
import nl.m4jit.tradetoday.presentation.nhcjobs.iframes.RejectNHCJobFrame;

/**
 *
 * @author joris
 */
public class ToBeApprovedPanel extends TableDialog<NHCJobGateway>{

    private TableDialog nextdialog;
    
    public ToBeApprovedPanel(TableDialog nextdialog){

        super(new ToBeApprovedModel(), true);
        this.nextdialog = nextdialog;
        
        addButton("Goedkeuren", "approve", RIGHT);
        addButton("Afkeuren", "reject", RIGHT);
        
        setPreferredSize(new Dimension(500, 500));
    }
    
    @Override
    public String getScreenTitle() {

        return "Ter goedkeuring";
    }

    @Override
    protected void processUnknowActionCommand(String ac) {

        NHCJobGateway selectedJob = getSelectedRecord();
        
        if(ac.equals("approve")){
            
            new ApproveNHCJobIFrame(selectedJob, this, nextdialog);
        }else if(ac.equals("reject")){
            
            new RejectNHCJobFrame(selectedJob, this, nextdialog);
        }
    }
}