package nl.m4jit.tradetoday.domainlogic.nhcjobs;

import java.util.*;
import nl.m4jit.framework.IATableModel;
import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.*;

public class HelpSignalsModel extends IATableModel<NHCJobGateway> {

    private MemberGateway member;
    
    public HelpSignalsModel(MemberGateway member) {

        super(new String[]{"Hulpsignalen", "Acties"}, new Class[]{String.class, String.class}, false);
        this.member = member;
        retreiveData();
    }

    @Override
    public Object getColValue(NHCJobGateway item, int col) {

        switch (col) {

            case 0:
                return item.getHelpSignals();
                
            case 1:
                return item.getHelpSignalsAction();
                
            default:
                return null;
        }
    }

    @Override
    protected List<NHCJobGateway> getItems(Object filter) {
        
        return NHCJobTable.getInstance().findWithHelpSignalsByMember(member.getNumber());
    }
}
