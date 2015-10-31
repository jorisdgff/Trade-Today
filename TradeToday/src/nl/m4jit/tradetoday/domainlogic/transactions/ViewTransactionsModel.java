package nl.m4jit.tradetoday.domainlogic.transactions;

import java.util.*;
import nl.m4jit.framework.*;
import nl.m4jit.tradetoday.dataaccess.members.*;
import nl.m4jit.tradetoday.dataaccess.transactions.*;

public class ViewTransactionsModel extends IATableModel<TransactionruleGateway> {

    private MemberGateway member;
    private boolean intake;
    
    public ViewTransactionsModel(MemberGateway member, boolean intake, int[] columnwidth) {
        //
        super(new String[]{"Datum", "Omschrijving", "Punten"}, new Class[]{Date.class, String.class, Integer.class}, null, columnwidth, false);
        this.member = member;
        this.intake = intake;
        retreiveData();
    }

    @Override
    public Object getColValue(TransactionruleGateway item, int col) {

        switch (col) {

            case 0:

                TransactionGateway transaction = item.getTransaction();
                
                if (transaction != null) {

                    return transaction.getDate();
                } else {

                    return null;
                }

            case 1:
                return item.getDescription();

            case 2:
                return item.getViewpoints();

            default:
                return null;
        }
    }

    @Override
    protected List getFilterItems() {
        
        return TransactionMonth.findByMember(member.getNumber());
    }

    @Override
    protected List<TransactionruleGateway> getItems(Object filter) {
        
        if(intake){
            
            Date today = new Date();
            TransactionMonth tMonth = new TransactionMonth(today.getMonth() + 1, today.getYear() + 1900);
            
            List<TransactionruleGateway> result = TransactionModule.getInstance().findTransactionRulesByMemberAndMonth(member, tMonth.getMonth(), tMonth.getYear(), true);
            
            TransactionMonth tMonth2 = null;
            
            if(tMonth.getMonth() == 1){
                
                tMonth2 = new TransactionMonth(12, tMonth.getYear() - 1);
            }else{
                
                tMonth2 = new TransactionMonth(tMonth.getMonth() - 1, tMonth.getYear());
            }
            
            List<TransactionruleGateway> result2 = TransactionModule.getInstance().findTransactionRulesByMemberAndMonth(member, tMonth2.getMonth(), tMonth2.getYear(), true);
            
            result.addAll(result2);
            return result;
        }else{
        
            TransactionMonth selectedMonth = (TransactionMonth) filter;
            return TransactionModule.getInstance().findTransactionRulesByMemberAndMonth(member, selectedMonth.getMonth(), selectedMonth.getYear(), false);
        }
    }
    
    
}