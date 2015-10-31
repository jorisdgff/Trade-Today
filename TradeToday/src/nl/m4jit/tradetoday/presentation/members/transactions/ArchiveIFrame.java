package nl.m4jit.tradetoday.presentation.members.transactions;

import nl.m4jit.framework.presentation.swing.abstractdialogs.*;
import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.dataaccess.transactions.*;
import nl.m4jit.tradetoday.domainlogic.transactions.*;

public class ArchiveIFrame extends TableDialog<TransactionruleGatewayArch> {
 
    public ArchiveIFrame(MemberGateway member){
        
        super(new ArchiveModel(member));
        setUI();
    }

    @Override
    public String getScreenTitle() {
        
        return "Archief";
    }
}