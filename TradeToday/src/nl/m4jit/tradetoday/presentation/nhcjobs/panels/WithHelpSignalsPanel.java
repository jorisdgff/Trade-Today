package nl.m4jit.tradetoday.presentation.nhcjobs.panels;

import javax.swing.JOptionPane;
import nl.m4jit.framework.presentation.swing.abstractdialogs.*;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.*;
import nl.m4jit.tradetoday.domainlogic.nhcjobs.*;

public class WithHelpSignalsPanel extends TableDialog<NHCJobGateway> {

    public WithHelpSignalsPanel() {

        super(new WithHelpSignalsModel(), true);
        addButton("Doorgegeven", "passed", RIGHT);
    }

    @Override
    public String getScreenTitle() {

        return "Hulpsignalen";
    }

    private void passed() {

        String action = JOptionPane.showInputDialog(this, "Uitgevoerde actie", "Hulpsignaal doorgegeven", JOptionPane.PLAIN_MESSAGE);

        if (!action.equals("")) {

            NHCJobGateway job = getSelectedRecord();
            NHCJobModule.getInstance().pass(job, action);
            removeRecord(job);
        } else {
            
            JOptionPane.showMessageDialog(this, "Geef een actie op");
            passed();
        }
    }

    @Override
    protected void processUnknowActionCommand(String ac) {

        if (ac.equals("passed")) {

            passed();
        }
    }

}
