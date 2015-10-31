package nl.m4jit.tradetoday.presentation.members;

import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.NHCJobGateway;
import nl.m4jit.tradetoday.domainlogic.nhcjobs.HelpSignalsModel;

public class HelpSignalsPanel extends TableDialog<NHCJobGateway>{
    
    public HelpSignalsPanel(MemberGateway member){
        
        super(new HelpSignalsModel(member), true);
    }

    @Override
    public String getScreenTitle() {
        
       return "Hulpsignalen";
    }
}
