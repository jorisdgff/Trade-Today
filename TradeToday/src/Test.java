import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.domainlogic.transactions.TransactionModule;

public class Test {
 
    public static void main(String[] args){
        
        MemberGateway member = MemberTable.getInstance().retreive(563);
        //TransactionModule.getInstance().findTransactionRulesByMember(member);
        
    }
}