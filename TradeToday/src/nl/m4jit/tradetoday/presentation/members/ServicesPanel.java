package nl.m4jit.tradetoday.presentation.members;

import nl.m4jit.framework.presentation.swing.abstractdialogs.TableDialog;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionruleGateway;
import nl.m4jit.tradetoday.domainlogic.transactions.ServicesModel;

public class ServicesPanel extends TableDialog<TransactionruleGateway>{
    
    public ServicesPanel(MemberGateway member){
        
        super(new ServicesModel(member), true);
        
        enablePrint();
        addButton("Archief", "archive", RIGHT);
    }

    @Override
    public String getScreenTitle() {
        
       return "Ontvangen diensten";
    }
}
